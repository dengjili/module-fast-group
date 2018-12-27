package priv.module.business.person.config;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;

import priv.module.config.bean.PageParams;

@Intercepts({ @Signature(
		type = StatementHandler.class, 
		method = "prepare", 
		args = { Connection.class, Integer.class }) })
public class PagePlugin implements Interceptor{
	
	// private static final Logger logger = LoggerFactory.getLogger(MyPlugin.class);
	
	// 当前页码
	private Integer defaultPage;
	// 每页数量
	private Integer defaultPageSize;

	// 是否启动插件（开关）
	private Boolean defaultUseFlag;
	// 是否检测页码下标的有效性
	private Boolean defaultCheckFlag;
	// 是否清楚order by后面的语句
	private Boolean defaultCleanOrderBy;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler =  getNonProxyObject(invocation);
		MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
		
		// 获取StatementHandler中的变量
		BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
		String sql = boundSql.getSql();
		
		// 判断是否为select语句, 如果不是，结束本次处理
		if (!isSelect(sql)) {
			invocation.proceed();
		}
		
		// 获取StatementHandler中的变量
		// MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
		Object parameterObject = boundSql.getParameterObject();
		PageParams pageParam = getPageParamsByObject(parameterObject);
		
		// 判断是否入参中是否包含分页信息, 如果没有，结束本次处理
		if (pageParam == null) {
			return invocation.proceed();
		}
		
		// 判断是否启用分页
		boolean isUse = pageParam.getUseFlag() == null ? defaultUseFlag : pageParam.getUseFlag();
		if (!isUse) {
			return invocation.proceed();
		}
		
		// 分页参数相关
		Integer page = pageParam.getPage() == null ? defaultPage : pageParam.getPage();
		Integer pageSize = pageParam.getPageSize() == null ? defaultPageSize : pageParam.getPageSize();
		boolean checkFlag = pageParam.getCheckFlag() == null ? defaultCheckFlag : pageParam.getCheckFlag();
		boolean cleanOrderBy = pageParam.getCleanOrderBy() == null ? defaultCleanOrderBy : pageParam.getCleanOrderBy();
		
		// 总条数
		int total = getTotal(invocation, metaStatementHandler, boundSql, cleanOrderBy);
		// 总页数
		int pageTotal = (total % pageSize == 0) ? (total / pageSize) : (total / pageSize) + 1;
		pageParam.setTotal(total);
		pageParam.setPageTotal(pageTotal);
		
		// 检查当前页码
		checkCurrentPage(checkFlag, page, pageTotal);
		
		return preparedSQL(invocation, metaStatementHandler, boundSql, page, pageSize);
	}

	private Object preparedSQL(Invocation invocation, MetaObject metaStatementHandler, BoundSql boundSql, Integer page,
			Integer pageSize) throws InvocationTargetException, IllegalAccessException, SQLException {
		String sql = boundSql.getSql();
		
		// 重置分页sql
		String pageSql = String.format("select * from (%s) temp_table limit ?, ?", sql);
		metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
		
		Object proceed = invocation.proceed();
		// 设置分页参数
		preparePageDataParams((PreparedStatement) proceed, page, pageSize);
		return proceed;
	}

	// 最后两位
	private void preparePageDataParams(PreparedStatement ps, Integer page, Integer pageSize) throws SQLException {
		int count = ps.getParameterMetaData().getParameterCount();
		ps.setInt(count - 1, (page - 1) * pageSize);
		ps.setInt(count, pageSize);
	}

	// 校验合法性，不然查询不出信息
	private void checkCurrentPage(boolean checkFlag, Integer page, int pageTotal) throws Exception {
		if (!checkFlag) {
			return ;
		}
		
		if (page > pageTotal) {
			throw new Exception(String.format("分页失败，当前页码【%s】，大于总页码【%s】", page, pageTotal));
		}
	}

	private int getTotal(Invocation invocation, MetaObject metaStatementHandler, BoundSql boundSql,
			boolean cleanOrderBy) throws SQLException {
		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
		Configuration configuration = mappedStatement.getConfiguration();
		String sql = boundSql.getSql();
		
		if (cleanOrderBy) {
			sql = cleanOrderByForSql(sql);
		}
		
		// 查询count
		String countSql = String.format("select count(*) as total from (%s) temp_total", sql);
		Connection connection = (Connection) invocation.getArgs()[0];
		
		PreparedStatement pst = null;
		int total = 0;
		try {
			pst = connection.prepareStatement(countSql);
			
			// 构建新的 BoundSql
			BoundSql countBoundSql = new BoundSql(configuration, countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
			// 构造参数到prepareStatement对象的映射
			ParameterHandler handler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), countBoundSql);
			handler.setParameters(pst);
			// 执行sql
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				total = rs.getInt("total");
			}
			
		} finally {
			if (pst != null) {
				pst.close();
			}
		}
		
		return total;
	}

	private String cleanOrderByForSql(String sql) {
		String lowerCase = sql.trim().toLowerCase();
		// 判断是否还有order by 关键字
		int index = lowerCase.lastIndexOf("order");
		if (index == -1) {
			return sql;
		}
		return sql.substring(0, index);
	}

	private PageParams getPageParamsByObject(Object parameterObject) throws Exception {
		if (parameterObject == null) {
			return null;
		}

		// 多个入参，未使用@Param注解，系统封装成一个map
		if (parameterObject instanceof Map) {
			Map<String, Object> paramMap = (Map<String, Object>) parameterObject;
			Collection<Object> values = paramMap.values();
			for (Object object : values) {
				if (object instanceof PageParams) {
					return (PageParams) object;
				}
 			}
		} else if (parameterObject instanceof PageParams) { // 使用@Param注解
			return (PageParams) parameterObject;
		} else { // 为bean的属性字段
			Field[] fields = parameterObject.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (field.getType() == PageParams.class) {
					PropertyDescriptor pd = new PropertyDescriptor(field.getName(), parameterObject.getClass());
					Method readMethod = pd.getReadMethod();
					PageParams invoke = (PageParams) readMethod.invoke(parameterObject);
					return invoke;
				}
			}
			return null;
		}
		return null;
	}

	private boolean isSelect(String sql) {
		String lowerCase = sql.trim().toLowerCase();
		return lowerCase.contains("select");
	}

	private StatementHandler getNonProxyObject(Invocation invocation) {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
		
		// 剥夺责任链的包装
		Object value = null;
		while (metaStatementHandler.hasGetter("h")) {
			value = metaStatementHandler.getValue("h");
			metaStatementHandler = SystemMetaObject.forObject(value);
		}
		
		if (value != null) {
			statementHandler = (StatementHandler) value;
		}
		
		return statementHandler;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		defaultPage = Integer.parseInt(properties.getProperty("page", "1"));  //默认第一页开头
		defaultPageSize = Integer.parseInt(properties.getProperty("pageSize", "50"));  //默认每页条数50
		defaultUseFlag = Boolean.parseBoolean(properties.getProperty("useFlag", "false"));  //默认每页条数50
		defaultCheckFlag = Boolean.parseBoolean(properties.getProperty("checkFlag", "false"));  //默认每页条数50
		defaultCleanOrderBy = Boolean.parseBoolean(properties.getProperty("cleanOrderBy", "false"));  //默认每页条数50
	}

}
