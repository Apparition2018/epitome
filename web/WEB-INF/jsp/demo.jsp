<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>demo</title>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
<script>
    $(function () {
        const personArr = [];
        <c:forEach items="${personList}" var="person">
        personArr.push({
            id: ${person.id},
            name: '${person.name}'
        });
        </c:forEach>
        console.log(personArr);
    });
</script>
</body>
</html>
