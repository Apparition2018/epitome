<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Captcha|算术验证码</title>
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
    <form action="valid.do" method="post">
        <label for="inCode">验证码：</label>
        <input type="text" id="inCode" name="inCode"/>
        <img src="captchaArithmetic.do" alt="captcha" id="imgCode" onclick="changeCode()" style="vertical-align:middle; cursor:pointer;"/><br/>
        <input type="submit" value="登录">
    </form>
    <div style="color:red">${err}</div>
    <script>
        function changeCode() {
            const imgCode = document.getElementById("imgCode");
            imgCode.src = "captchaArithmetic.do?" + Math.random();
        }
    </script>
</section>

<section>
    <form action="index.jsp" method="post">
        <label for="inCode2">验证码：</label>
        <input type="text" id="inCode2" name="inCode"/>
        <canvas id="cvs" onclick="changeCode2()" style="vertical-align:middle; cursor:pointer;"></canvas>
        <br/>
        <input type="submit" value="登录" onclick="return valid()">
    </form>
    <div style="color:red" id="err"></div>
    <script>
        let validCode;

        function changeCode2() {
            const cvs = document.getElementById("cvs");
            validCode = drawCode(cvs);
        }

        function valid() {
            const code = document.getElementById("inCode2").value;
            if (code.toString() === validCode.toString()) {
                return true;
            } else {
                document.getElementById("err").innerHTML = "验证码输入错误，请重新输入！";
                changeCode2();
                return false;
            }
        }

        let w = 80;
        const h = 24;
        const fontSize = h - 6;
        const operators = "+-";

        // 随机生成最大值不超过 max 的整数
        function ranInt(max) {
            return Math.floor(Math.random() * 100000 % max);
        }

        // 生成随机算术表达式
        function ranCode() {
            const one = ranInt(100);
            const two = ranInt(100);
            const operator = operators.charAt(ranInt(operators.length));
            return "" + one + operator + two + "=?";
        }

        // 生成随机颜色
        function ranColor() {
            const r = ranInt(256);
            const g = ranInt(256);
            const b = ranInt(256);
            return "rgb(" + r + "," + g + "," + b + ")";
        }

        // 绘制图片
        function drawCode(canvas) {
            let validCode = ranCode();
            w = 5 + fontSize * validCode.length;
            if (canvas != null && canvas.getContext && canvas.getContext("2d")) {
                // 设置显式区域大小
                canvas.style.width = w;
                // 设置画板高度
                canvas.setAttribute("width", w);
                canvas.setAttribute("height", h);
                // 得到画笔
                const pen = canvas.getContext("2d");
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
                validCode = validCode. substr(0, validCode.length - 2);
                // eval(): 把字符串当表达式处理 
                return eval(validCode);
            }
        }

        window.onload = changeCode2;
    </script>
</section>
</body>
</html>
