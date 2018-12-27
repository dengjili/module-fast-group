<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页测试</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static-resource/layui/css/layui.css" media="all">
<link rel="stylesheet" href="${pageContext.request.contextPath }/static-resource/css/extend.css" media="all">
</head>
<body>
<div class="main-container">
	<div class="layui-btn-container">
	    <button class="layui-btn layui-btn-sm" id="redoquery">添加</button>
	    <button class="layui-btn layui-btn-normal layui-btn-sm" id="redoquery2">按条件查询</button>
	 </div>
	 
	<div class="layui-form" id="outputpart">
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
	
	<div id="test1"></div>
</div>

<div id="query" class="popup-none">
	<form id="formByCond" action="queryByCond" method="post" class="layui-form">
	  <div class="layui-form-item">
	    <label class="layui-form-label">用户ID</label>
	    <div class="layui-input-block">
	      <input type="text" id="query_id" name="query_id" autocomplete="off" placeholder="请输入用户ID" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">用户姓名</label>
	    <div class="layui-input-block">
	      <input type="text" id="query_name" name="query_name" placeholder="用户姓名" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	   <div class="layui-form-item">
		    <div class="layui-input-block">
			    <button class="layui-btn layui-btn-lg layui-btn-radius layui-btn-danger" type="reset" >重置</button>
			    <button class="layui-btn layui-btn-lg layui-btn-radius layui-btn-normal" id="queryfunc">提交</button>
			</div>
	 </div>
	</form>
</div>
<div id="query2" class="popup-none">
	<form id="formByCond" action="queryByCond" method="post" class="layui-form">
	  <div class="layui-form-item">
	    <label class="layui-form-label">用户ID</label>
	    <div class="layui-input-block">
	      <input type="text" id="query_id" name="query_id" autocomplete="off" placeholder="请输入用户ID" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">用户姓名</label>
	    <div class="layui-input-block">
	      <input type="text" id="query_name" name="query_name" placeholder="用户姓名" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	   <div class="layui-form-item">
		    <div class="layui-input-block">
			    <button class="layui-btn layui-btn-lg layui-btn-radius layui-btn-danger" type="reset" >重置</button>
			    <button class="layui-btn layui-btn-lg layui-btn-radius layui-btn-normal" id="queryfunc">提交</button>
			</div>
	 </div>
	</form>
</div>
	
<script src="${pageContext.request.contextPath }/static-resource/js/jquery-1.11.3.min.js"></script> 
<script src="${pageContext.request.contextPath }/static-resource/js/jquery.extends.js"></script> 
<script src="${pageContext.request.contextPath }/static-resource/layui/layui.all.js"></script>
<script>
$(document).ready(function(){
	 $("#redoquery").bind("click", function(){
		 $.popup.show("query", "页面查询");
	 });
	 $("#redoquery2").bind("click", function(){
		 $.popup.show("query2", "页面查询2");
	 });
	 
	 //执行一个laypage实例
	 var laypage = layui.laypage;
	 var layer = layui.layer;
	//完整功能
	  laypage.render({
	    elem: 'test1'
	    ,count: ${page.total}
	    ,limits:[5, 10, 20, 50, 100, 200]
	    ,layout: ['prev', 'page', 'next', 'skip', 'count', 'limit']
	    ,theme: '#c00' 
	    ,skip: true //开启跳页
	        ,jump: function(obj, first){
	          if(!first){
	            layer.msg('第'+ obj.curr +'页, 页面为：' + obj.limit);
	          }
	        }
	  });
});
</script> 

</body>
</html>