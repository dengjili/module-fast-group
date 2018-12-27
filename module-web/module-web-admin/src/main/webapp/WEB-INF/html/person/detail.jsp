<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页测试</title>
</head>
<body>

	<h2>用户明细</h2>
	<a href=list>返回</a>
	<table>
		<tr>
			<td>用户ID</td>
			<td><input type="text" name="id" value="${person.id}"></td>
		</tr>
		<tr>
			<td>用户名称</td>
			<td><input type="text" name="display" value="${person.display}"></td>
		</tr>
		<tr>
			<td>用户姓名</td>
			<td><input type="text" name="name" value="${person.name}"></td>
		</tr>
		<tr>
			<td>用户电话</td>
			<td><input type="text" name="phone" value="${person.phone}"></td>
		</tr>
		<tr>
			<td>用户地址</td>
			<td><input type="text" name="address" value="${person.address}"></td>
		</tr>
		<tr>
			<td>用户生日</td>
			<td><input type="date" name="birthdate"
				value="${person.birthdate}"></td>
		</tr>
		<tr>
			<td>备注</td>
			<td><input type="text" name="remark" value="${person.remark}"></td>
		</tr>
	</table>
</body>
</html>