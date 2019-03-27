<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<#list emps as emp>
<#--循环变量仅仅只是在循环中有效，出了循环马上失效，不会影响模板变量-->
    <h3>${emp.name}</h3>
</#list>
</body>
</html>