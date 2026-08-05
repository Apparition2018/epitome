<%--
  XML 标签
  User: ljh
  Date: 2023/6/8
  Time: 11:07
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<html>
<head>
    <title>JSTL XML 标签</title>
</head>
<body>
<p><b><c:out value="<x:parse>："/></b><i>解析属性中或标签主体中的 XML 数据</i></p>
<p><b><c:out value="<x:out>："/></b><i>输出 XPath 表达式的值</i></p>
<p><b><c:out value="<x:set>："/></b><i>设置 XPath 表达式</i></p>
<p><b><c:out value="<x:if>："/></b><i>判断一个 XPath 表达式的值</i></p>
<p><b><c:out value="<x:transform>："/></b><i>将 XSL 转换应用在 XML 文档中</i></p>
<p><b><c:out value="<x:param>："/></b><i><c:out value="与 <x:transform> 共同使用，用于设置 XSL 样式表"/></i></p>
<c:import var="demoInfo" url="http://localhost:3333/demo.xml"/>
<%-- xml                可选，需要解析的文档的文本内容 (String or Reader)
     var                可选，已解析 XML 数据的变量 --%>
<x:parse xml="${demoInfo}" var="output"/>
<%-- select             必要，需要计算的 XPath 表达式
     var                可选，存储条件结果的变量 --%>
<x:if select="$output/school">
    <%-- 具有 <c:forEach> 相似的属性，此外还有
         select         必要，需要计算的 XPath 表达式 --%>
    <x:forEach select="$output/school/student" var="student">
        <%-- select     必要，需要计算的 XPath 表达式 --%>
        <x:out select="$output/school/student[1]/name"/>：<x:out select="$student/age"/>岁
    </x:forEach>
</x:if>
<%-- var                必要，XPath 表达式值的变量
     select             可选，需要计算的 XPath 表达式 --%>
<x:set select="$output/school/student[2]/name" var="studentName2"/>
</body>
</html>
