<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>hello:${emp.name}---${emp.id}----${emp.age}</h1>
	我是jsp <#if emp.age lt 18> ${emp.name}我小于18岁 <#elseif emp.age gt 60>
	${emp.name}我大于60岁† <#else> ${emp.name}我会好好工作!œ </#if> <#list emps as
	emp> ${emp.id}-----${emp.name}---${emp.age}
	<br /> </#list>
</body>
</html>