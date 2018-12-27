<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页测试</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static-resource/layui/css/layui.css" media="all">
</head>
<body>

	<h2>用户添加</h2>
	<form id="personAdd">
		<table>
			<tr>
				<td>用户ID</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td>用户名称</td>
				<td><input type="text" name="display"></td>
			</tr>
			<tr>
				<td>用户姓名</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>用户电话</td>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<td>用户地址</td>
				<td><input type="text" name="address"></td>
			</tr>
			<tr>
				<td>用户生日</td>
				<td><input type="date" name="birthdate"></td>
			</tr>
			<tr>
				<td>备注</td>
				<td><input type="text" name="remark"></td>
			</tr>
			<tr>
				<td colspan="2"><button class="layui-btn layui-btn-radius layui-btn-sm" id="isSubmit">提交</button></td>
			</tr>
		</table>
	</form>
	
	<div class="layui-form" id="output2">
			<table class="layui-table">
				<tr class="layui-bg-cyan">
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
						<td>
							<a class="layui-btn layui-btn-radius layui-btn-xs" lay-event="edit" href="updatePage/${person.id}">修改</a>
							<a class="layui-btn layui-btn-radius layui-btn-danger layui-btn-xs" lay-event="del" href="delete/${person.id}">删除</a>
							<a class="layui-btn layui-btn-radius layui-btn-normal layui-btn-xs" lay-event="detail" href="get/${person.id}">详细信息</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
<script src="${pageContext.request.contextPath }/static-resource/js/jquery-1.11.3.min.js"></script> 
<script src="${pageContext.request.contextPath }/static-resource/js/jquery.extends.js"></script> 
<script src="${pageContext.request.contextPath }/static-resource/js/jquery.form.js"></script>
<script> 
	$(document).ready(function() {

		$("#isSubmit").on("click", function(){
			$.sysext.ajaxSubmit('personAdd', 'add', '', 'output2');
		});
	});
	

	
</script>

</body>
</html>