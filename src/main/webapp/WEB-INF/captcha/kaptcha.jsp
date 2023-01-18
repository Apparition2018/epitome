<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Captcha|Kaptcha</title>
    <style>
        section {
            height: 100px;
            margin-bottom: 10px;
            padding: 8px;
            border-top: 1px solid #e6e6e6;
            border-bottom: 1px solid #e6e6e6;
        }
    </style>
</head>
<body>
<section>
    <form action="validKaptcha.do" method="post">
        <label for="inCode">验证码：</label>
        <input type="text" id="inCode" name="inCode"/>
        <img src="kaptcha.do" alt="captcha" id="imgCode" onclick="changeCode()" style="vertical-align:middle; cursor:pointer;"/><br/>
        <input type="submit" value="登录">
    </form>
    <div style="color:red">${err}</div>
    <script>
        function changeCode() {
            let imgCode = document.getElementById("imgCode");
            imgCode.src = "kaptcha.do?" + Math.random();
        }
    </script>
</section>

<section>
    <form action="validKaptcha2.do" method="post">
        <label for="inCode2">验证码：</label>
        <input type="text" id="inCode2" name="inCode2"/>
        <img src="kaptcha2.do" alt="captcha" id="imgCode2" onclick="changeCode2()"
             style="vertical-align:middle; cursor:pointer;"/><br/>
        <input type="submit" value="登录">
    </form>
    <div style="color:red">${err2}</div>
    <script>
        function changeCode2() {
            let imgCode = document.getElementById("imgCode2");
            imgCode.src = "kaptcha2.do?" + Math.random();
        }
    </script>
</section>
</body>
</html>
