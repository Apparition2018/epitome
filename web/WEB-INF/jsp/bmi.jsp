<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <title>bmi</title>
</head>
<body style="font-size: 30px;">
<%-- action="bim1~7" --%>
<form action="bmi5" method="post">
    <fieldset>
        <legend>计算bmi质数</legend>
        <label for="height">身高（米）：</label><input name="height" id="height"/><br/>
        <label for="weight">体重（公斤）：</label><input name="weight" id="weight"/><br/>
        <input type="submit" value="确定"/>
    </fieldset>
</form>
</body>
</html>
