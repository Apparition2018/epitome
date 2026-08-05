<%--
  JSTL 核心标签
  User: ljh
  Date: 2023/6/8
  Time: 8:32
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JSTL 核心标签</title>
</head>
<body>
<p><b><c:out value="<c:forEach>"/></b></p>
<ul>
    <c:forEach items="${list}" var="i" begin="0" step="2">
        <li>${i}</li>
    </c:forEach>
</ul>
<hr/>

<p><b><c:out value="<c:import>："/></b><i><c:out value="提供所有 <jsp:include> 行为标签所具有的功能，同时也允许包含绝对 URL"/></i></p>
<%-- url        必要，待导入资源的 URL，可以是相对路径和绝对路径，并且可以导入其他主机资源
     var        可选，存储引入的文本的变量 --%>
<c:import url="http://localhost:3333/"/>
<c:import var="data" url="http://localhost:3333/"/>
<c:out value="${data}"/>
<hr/>

<p><b><c:out value="<c:url>："/></b><i><c:out value="使用可选的查询参数来创造一个 URL；用于调用 response.encodeURL() 的一种可选的方法；优势在于提供了合适的 URL 编码和 <c:param>"/></i></p>
<%-- value      必要，基础 URL
     var        可选，格式化后的 URL --%>
<a href="<c:url value="http://localhost:3333/data-binding/bmi1">
            <c:param name="height" value="180"/>
            <c:param name="weight" value="180"/>
         </c:url>" target="_blank">bmi</a>
<hr/>

<p><b><c:out value="<c:redirect>："/></b><i>重定向</i></p>
<%-- url        必要，目标 URL --%>
<%--<c:redirect url="https://www.baidu.com"/>--%>
<hr/>
</body>
</html>
