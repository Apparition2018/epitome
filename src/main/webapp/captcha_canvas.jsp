<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Captcha|随机验证码(canvas)</title>
    <style>
        #cvs {
            vertical-align: middle;
            cursor: pointer;
        }
    </style>
</head>
<body>
<form action="index.jsp" method="post">
    <label for="inCode">验证码：</label>
    <input type="text" id="inCode" name="inCode"/>
    <canvas id="cvs" onclick="changeCode()"></canvas>
    <br/>
    <input type="submit" value="登录" onclick="return valid()">
</form>
<div style="color:red" id="err">${err}</div>
<script>
    let validCode;

    function changeCode() {
        let cvs = document.getElementById("cvs");
        validCode = drawCode(cvs);
    }

    function valid() {
        let code = document.getElementById("inCode").value;
        if (code.toLowerCase() === validCode.toLowerCase()) {
            return true;
        } else {
            document.getElementById("err").innerHTML = "输入验证码错误！";
            changeCode();
            return false;
        }
    }

    let w = 80;
    let h = 24;
    let fontSize = h - 6;
    let str = "0123456789abcdefABCDEF";

    // 随机生成最大值不超过 max 的整数
    function ranInt(max) {
        return Math.floor(Math.random() * 100000 % max);
    }

    // 生成随机长度字符串验证码
    function ranCode(len) {
        if (len < 4) {
            len = 4;
        }
        let code = "";
        for (let i = 0; i < len; i++) {
            code += str.charAt(ranInt(str.length));
        }
        return code;
    }

    // 生成随机颜色
    function ranColor() {
        let r = ranInt(256);
        let g = ranInt(256);
        let b = ranInt(256);
        return "rgb(" + r + "," + g + "," + b + ")";
    }

    // 绘制图片
    function drawCode(canvas) {
        let validCode = ranCode(4);
        w = 5 + fontSize * validCode.length;
        if (canvas != null && canvas.getContext && canvas.getContext("2d")) {
            // 设置显式区域大小
            canvas.style.width = w;
            // 设置画板高度
            canvas.setAttribute("width", w);
            canvas.setAttribute("height", h);
            // 得到画笔
            let pen = canvas.getContext("2d");
            // 绘制背景
            pen.fillStyle = "rgb(255, 255, 255)";
            pen.fillRect(0, 0, w, h);
            // 设置水平线位置
            pen.textBaseline = "top"; // middle, bottom
            // 绘制内容
            for (let i = 0; i < validCode.length; i++) {
                pen.fillStyle = ranColor();
                pen.font = "bold " + (fontSize + ranInt(3)) + "px 微软雅黑";
                pen.fillText(validCode.charAt(i), 5 + fontSize * i, ranInt(5));
            }
            // 绘制噪音线
            for (let i = 0; i < 2; i++) {
                pen.moveTo(ranInt(w) / 2, ranInt(h));   // 设置起点
                pen.lineTo(ranInt(w), ranInt(h));       // 设置终点
                pen.strokeStyle = ranColor();
                pen.lineWidth = 2;
                pen.stroke();
            }
            return validCode;
        }
    }

    window.onload = changeCode;
</script>
</body>
</html>
