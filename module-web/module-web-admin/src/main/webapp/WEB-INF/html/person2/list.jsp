<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页测试</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static-resource/layui/css/layui.css" media="all">
</head>
<body>

	<a href=addPage>添加</a>
	<table border="1" cellpadding="0" cellspacing="0">
		<tr>
			<th>用户ID</th>
			<th>用户名称</th>
			<th>用户姓名</th>
			<th>用户电话</th>
			<th>用户地址</th>
			<th>用户生日</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
		<c:forEach var="person" items="${persons}">
			<tr>
				<td>${person.id}</td>
				<td>${person.display}</td>
				<td>${person.name}</td>
				<td>${person.phone}</td>
				<td>${person.address}</td>
				<td>${person.birthdate}</td>
				<td>${person.remark}</td>
				<td><a href="updatePage/${person.id}">修改</a> <a
					href="delete/${person.id}">删除</a> <a href="get/${person.id}">详细信息</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<!-- 1.方法渲染 -->
	<table id="table_id" lay-filter="test"></table>
	
	<!-- 2.自动渲染 -->
	<h4>自动渲染</h4>
	<table class="layui-table" lay-data="{url:'${pageContext.request.contextPath }/person2/listjson', page:true, id:'table_id2'}" lay-filter="test">
	  <thead>
	    <tr>
	      <th lay-data="{field:'id', width:80, sort: true}">用户ID</th>
	      <th lay-data="{field:'display', width:80}">用户名称</th>
	      <th lay-data="{field:'name', width:80, sort: true}">用户姓名</th>
	      <th lay-data="{field:'phone'}">用户电话</th>
	      <th lay-data="{field:'address'}">用户地址</th>
	      <th lay-data="{field:'birthdate', sort: true}">用户生日</th>
	      <th lay-data="{field:'remark', sort: true}">备注</th>
	    </tr>
	  </thead>
	</table>
<script src="${pageContext.request.contextPath }/static-resource/layui/layui.js"></script>
<script>
layui.use('table', function(){
  var table = layui.table;
  
  // 方法渲染
  table.render({
    elem: '#table_id'
    //,height: 312
    ,url: '${pageContext.request.contextPath }/person2/listjson' //数据接口
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'id', title: '用户ID', width:80, sort: true, fixed: 'left'}
      ,{field: 'display', title: '用户名称', width:80}
      ,{field: 'name', title: '用户姓名', width:80, sort: true}
      ,{field: 'phone', title: '用户电话', width:180} 
      ,{field: 'address', title: '用户地址', width: 250}
      ,{field: 'birthdate', title: '用户生日', width: 180, sort: true}
      ,{field: 'remark', title: '备注', sort: true}
    ]]
  });
  
});

</script>

</body>
</html>