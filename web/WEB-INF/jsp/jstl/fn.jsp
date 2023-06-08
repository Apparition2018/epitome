<%--
  JSTL 函数
  User: ljh
  Date: 2023/6/8
  Time: 11:46
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>JSTL 函数</title>
</head>
<body>
<c:set var="fruit" value="watermelon"/>
<c:set var="water" value="water"/>
<c:set var="melon" value="melon"/>

<p><b>fn:length()：</b><i>返回字符串长度</i></p>
${fn:length(fruit)}
<hr/>

<p><b>fn:toLowerCase()：</b><i>将字符串中的字符转为小写</i></p>
<p><b>fn:toUpperCase()：</b><i>将字符串中的字符转为大写</i></p>
${fn:toUpperCase(fruit)}
${fn:toLowerCase(fruit)}
<hr/>

<p><b>fn:contains()：</b><i>测试输入的字符串是否包含指定的子串</i></p>
<p><b>fn:containsIgnoreCase()：</b><i>测试输入的字符串是否包含指定的子串，大小写不敏感</i></p>
<c:if test="${fn:containsIgnoreCase(fruit, water)}">包含水</c:if>
<hr/>

<p><b>fn:startsWith()：</b><i>测试输入字符串是否以指定的前缀开始</i></p>
<p><b>fn:startsWith()：</b><i>测试输入字符串是否以指定的前缀开始</i></p>
<c:if test="${fn:startsWith(fruit, water)}">水开头</c:if>
<c:if test="${fn:endsWith(fruit, melon)}">瓜结尾</c:if>
<hr/>

<p><b>fn:indexOf()：</b><i>返回指定字符串在输入字符串中出现的位置</i></p>
${fn:indexOf(fruit, melon)}
<hr/>

<p><b>fn:replace()：</b><i>将输入字符串中指定的位置替换为指定的字符串然后返回</i></p>
${fn:replace(fruit, water, 'musk')}
<hr/>

<p><b>fn:substring()：</b><i>返回字符串的子集</i></p>
<p><b>fn:substringAfter()：</b><i>返回字符串在指定子串之后的子集</i></p>
<p><b>fn:substringBefore()：</b><i>返回字符串在指定子串之前的子集</i></p>
${fn:substring(fruit, 0, 5)}
${fn:substringAfter(fruit, water)}
${fn:substringBefore(fruit, melon)}
<hr/>

<p><b>fn:split()：</b><i>将字符串用指定的分隔符分隔然后组成一个子字符串数组并返回</i></p>
<p><b>fn:join()：</b><i>将数组中的元素合成一个字符串然后输出</i></p>
<c:set var="arr" value="${fn:split('a b c', ' ')}"/>
${fn:join(arr, ',')}
<hr/>

<p><b>fn:trim()：</b><i>移除首尾的空白符</i></p>
${fn:trim(' abc  ')}
<hr/>

<p><b>fn:escapeXml()：</b><i>跳过可以作为 XML 标记的字符</i></p>
${fn:escapeXml('<abc></abc>')}
<hr/>

</body>
</html>
