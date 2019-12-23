<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Captcha|随机验证码</title>
    <style>
        #imgCode {
            vertical-align: middle;
            cursor: pointer;
        }
    </style>
</head>
<body>
<form action="valid.do" method="post">
    <label for="inCode">验证码：</label>
    <input type="text" id="inCode" name="inCode"/>
    <img src="captcha.do" alt="captcha" id="imgCode" onclick="changeCode()"/><br/>
    <input type="submit" value="登录">
</form>
<div style="color:red">${err}</div>
<script>
    function changeCode() {
        let imgCode = document.getElementById("imgCode");
        imgCode.src = "captcha.do?" + Math.random();
    }
</script>
</body>
</html>
