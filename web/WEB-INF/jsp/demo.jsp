<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>demo</title>
</head>
<body>
<c:if test="${param.name == 'javascriptReceiveCollection'}">
    <script>
        (function () {
            /* 1.接收后台 List */
            const personArr = [];
            <c:forEach items="${personList}" var="person">
            personArr.push({
                id: ${person.id},
                name: '${person.name}'
            });
            </c:forEach>
            console.log(personArr);

            /* 2.接收后台 JSON 数组*/
            console.log(${personJsonArray})
        })();
    </script>
</c:if>
</body>
</html>
