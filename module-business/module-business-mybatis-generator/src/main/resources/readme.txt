### table标签说明

<!-- optional.插入时,是否生成selectKey元素 -->  
<!--<generatedKey column="id" sqlStatement="SELECT LAST_INSERT_ID()" identity="true" type="post"/>-->  
  
<!-- optional.忽略某一列 -->  
<!-- <ignoreColumn column="gmt_create"  delimitedColumnName="true" />   -->
  
<!--optional.覆盖Model的生成规则,主要是设置javaType -->  
<!-- <columnOverride column="id" javaType="java.lang.Long"    jdbcType="INTEGER"  typeHandler=""  delimitedColumnName="" />   -->


### 输入命令
mybatis-generator:generate