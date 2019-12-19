<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
Created by IntelliJ IDEA.
User: 22943
Date: 2019/12/9
Time: 8:00
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zxx">

<head>
    <title>Home</title>
    <!-- Meta tag Keywords -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8"/>
    <meta name="keywords" content=""
    />
    <script>
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
    <!-- Meta tag Keywords -->
    <!-- css files -->
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <!-- Style-CSS -->
    <link rel="stylesheet" href="css/fontawesome-all.css">
    <!-- Font-Awesome-Icons-CSS -->
    <!-- //css files -->
    <!-- web-fonts -->
    <!-- //web-fonts -->
</head>

<body>
<!-- bg effect -->
<div id="bg">
    <canvas></canvas>
    <canvas></canvas>
    <canvas></canvas>
</div>
<!-- //bg effect -->
<!-- title -->
<h1>Register Account</h1>
<!-- //title -->
<!-- content -->
<div class="sub-main-w3">
    <form name="RegisterForm" action="RegisterServlet" method="post" onsubmit="return checking()">
        <h2>Register Now
            <i class="fas fa-level-down-alt"></i>
        </h2>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                id
            </label>
            <input placeholder="英文、数字长度为1-10个字符" id="UserId" name="UserId" type="text" required
                   pattern="[a-zA-Z0-9]{1,10}">
            <span id="idExitSpan" style="color:red;display:none" class="error">用户已存在</span>
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                Username
            </label>
            <input placeholder="昵称" name="RegisterUserName" type="text" required
                   pattern=".{1,10}">
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                RealName
            </label>
            <input placeholder="真实姓名" name="RegisterRealName" type="text" required
                   pattern=".{1,10}">
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-unlock-alt"></i>
                Password
            </label>
            <input placeholder="长度为6-16个字符" name="RegisterPassword" id="RegisterPassword" type="password" required
                   pattern="[a-zA-Z0-9]{6,16}">
        </div>
        <%--需前端验证两次密码是否相同--%>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-unlock-alt"></i>
                Confirm the password
            </label>
            <input placeholder="确认密码" name="confirmPassword" id="confirmPassword" type="password" required
                   pattern="[a-zA-Z0-9]{6,16}">
        </div>
        <%--需前端验证两次密码是否相同--%>
        <input type="submit" value="Register" name="RegisterSubmit">
    </form>
</div>
<!-- //content -->

<!-- copyright -->
<div class="footer">
    <p>Copyright &copy; 2018.Company name All rights reserved.YJM</p>
</div>
<!-- //copyright -->

<!-- Jquery -->
<script src="js/jquery-3.3.1.min.js"></script>
<!-- //Jquery -->
<script src="js/ajaxQuerry.js"></script>
<!-- effect js -->
<script src="js/canva_moving_effect.js"></script>
<!-- //effect js -->

<script src="js/confirm.js"></script>
</body>

</html>