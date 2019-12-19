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
<h1>Update Account</h1>
<!-- //title -->
<!-- content -->
<div class="sub-main-w3">
    <form name="UpdateForm" action="inner/UpdateUserServlet" method="post">
        <h2>Update Now
            <i class="fas fa-level-down-alt"></i>
        </h2>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                Username
            </label>
            <input placeholder="昵称" name="UpdateUserName" type="text"
                   value="${sessionScope.user.userName}" required pattern=".{1,10}">
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                RealName
            </label>
            <input placeholder="真实姓名" name="UpdateRealName" type="text" required
                   pattern=".{1,10}" value="${sessionScope.user.realName}">
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-unlock-alt"></i>
                New Password
            </label>
            <input placeholder="新密码" name="UpdatePassword" type="password" required
                   pattern="[a-zA-Z0-9]{6,16}">
        </div>
        <input type="submit" value="Register" name="修改">
        <c:if test="${sessionScope.UpdateUserState==false}">
            <p style="color: red">用户名已被使用</p>
            <%
                request.getSession().setAttribute("UpdateUserState", true);
            %>
        </c:if>
    </form>
    <div style="position: fixed;top: 5%;left: 90%;">
        <button class="cancelButton" style="opacity: 0.8;border-radius: 10%;background:salmon">
            <a href="personECV.jsp">返回</a>
        </button>
    </div>
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

<!-- effect js -->
<script src="js/canva_moving_effect.js"></script>
<!-- //effect js -->

<script src="js/confirm.js"></script>
</body>

</html>