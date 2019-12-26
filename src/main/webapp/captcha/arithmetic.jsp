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
        <img src="captchaArithmetic.do" alt="captcha" id="imgCode" onclick="changeCode()"/><br/>
        <input type="submit" value="登录">
    </form>
    <div style="color:red">${err}</div>
    <style>
        #imgCode {
            vertical-align: middle;
            cursor: pointer;
        }
    </style>
    <script>
        function changeCode() {
            let imgCode = document.getElementById("imgCode");
            imgCode.src = "captchaArithmetic.do?" + Math.random();
        }
    </script>
</section>

<section>
    <form action="index.jsp" method="post">
        <label for="inCode2">验证码：</label>
        <input type="text" id="inCode2" name="inCode"/>
        <canvas id="cvs" onclick="changeCode2()"></canvas>
        <br/>
        <input type="submit" value="登录" onclick="return valid()">
    </form>
    <div style="color:red" id="err"></div>
    <style>
        #cvs {
            vertical-align: middle;
            cursor: pointer;
        }
    </style>
    <script>
        let validCode;

        function changeCode2() {
            let cvs = document.getElementById("cvs");
            validCode = drawCode(cvs);
        }

        function valid() {
            let code = document.getElementById("inCode2").value;
            if (code.toString() === validCode.toString()) {
                return true;
            } else {
                document.getElementById("err").innerHTML = "验证码输入错误，请重新输入！";
                changeCode2();
                return false;
            }
        }

        let w = 80;
        let h = 24;
        let fontSize = h - 6;
        let operators = "+-";

        // 随机生成最大值不超过 max 的整数
        function ranInt(max) {
            return Math.floor(Math.random() * 100000 % max);
        }

        // 生成随机算术表达式
        function ranCode() {
            let one = ranInt(100);
            let two = ranInt(100);
            let operator = operators.charAt(ranInt(operators.length));
            return "" + one + operator + two + "=?";
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
            let validCode = ranCode();
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
