<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <h1>FTL</h1>

    <#if emp.age lt 18>
        ${emp.name} 未成年
    <#elseif emp.age gt 60>
        ${emp.name} 已退休
    <#else>
        ${emp.name} 在职
    </#if>

    <#list empList as emp>
        ${emp.id} - ${emp.name} - ${emp.age}<br/>
    </#list>
</body>
</html>
