<%--
  JSTL 格式化标签
  User: ljh
  Date: 2023/6/8
  Time: 10:06
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>JSTL 格式化标签</title>
</head>
<body>
<p><b><c:out value="<fmt:formatNumber>："/></b><i>格式化数字、百分比、货币</i></p>
<%-- value              必要，要显示的数字
     type               可选，Number | Currency | Perencent，默认 Number --%>
<c:set var="balance" value="120000.2309"/>
<%-- maxIntegerDigits	可选，整型数最大的位数
     minIntegerDigits	可选，整型数最小的位数 --%>
<div><fmt:formatNumber type="number" value="${balance}" maxIntegerDigits="3"/></div>
<%-- maxFractionDigits	可选，小数点后最大的位数
     minFractionDigits	可选，小数点后最小的位数 --%>
<div><fmt:formatNumber type="number" value="${balance}" maxFractionDigits="3"/></div>
<%-- groupingUsed       可选，是否对数字分组，默认 true --%>
<div><fmt:formatNumber type="number" value="${balance}" groupingUsed="false"/></div>
<%-- pattern            可选，格式化模式 --%>
<div><fmt:formatNumber type="number" value="${balance}" pattern="###.###E0"/></div>
<div><fmt:formatNumber type="percent" value="${balance}" maxIntegerDigits="3"/></div>
<div><fmt:formatNumber type="percent" value="${balance}" minFractionDigits="10"/></div>
<div><fmt:formatNumber type="percent" value="${balance}" maxIntegerDigits="3"/></div>
<div><fmt:formatNumber type="currency" value="${balance}"/></div>
<div>美元：<fmt:setLocale value="en_US"/><fmt:formatNumber value="${balance}" type="currency"/></div>
<hr/>

<p><b><c:out value="<fmt:parseNumber>："/></b><i>解析数字、百分比、货币</i></p>
<%-- value              可选，要解析的数字
     type               可选，number | currency | percent，默认 number
     var                可选，存储待解析数字的变量
     pattern            可选，解析模式
     timeZone           可选，要显示的日期的时区 --%>
<c:set var="balance" value="120000.2309"/>
<div><fmt:parseNumber type="number" value="${balance}"/></div>
<%-- integerOnly	    可选，是否只解析整型数 --%>
<div><fmt:parseNumber type="number" value="${balance}" integerOnly="true"/></div>
<hr/>

<p><b><c:out value="<fmt:bundle> & <fmt:message>："/></b><i>国际化标签</i></p>
<hr/>
</html>
