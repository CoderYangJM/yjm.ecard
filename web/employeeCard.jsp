<%--
  Created by IntelliJ IDEA.
  User: 22943
  Date: 2019/12/15
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>card</title>
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
    <!-- Style-CSS -->
    <link rel="stylesheet" href="css/manageCss.css">
    <!-- Font-Awesome-Icons-CSS -->
    <link rel="stylesheet" href="css/fontawesome-all.css">
    <!-- //css files -->
    <!-- web-fonts -->
    <!-- //web-fonts -->
</head>
<body>
<div id="bg">
    <canvas></canvas>
    <canvas></canvas>
    <canvas></canvas>
</div>
<%@ include file="navBar.jsp" %>
<div class="cardFrame">
    <%--    改进点：先判断是否存在某条信息（如地址）若不存在则不打印--%>
    <c:forEach items="${sessionScope.EmployeeCards}" var="Cards" step="1">
        <div class="card">
            <div class="cardTop">
                <div style="padding-top: 20px;">
                    <font size="5">${Cards.name}</font>
                </div>
                <button class="rightButton"><a href="inner/CollectionServlet?op=collection&cardNum=${Cards.cardNum}">收藏</a></button>
                <div style="clear: both; width: 0; height: 0;"></div>
            </div>
            <div class="cardBottom">
                <div class="information">
                    名字：<font size="3">${Cards.name}</font>
                    <input type="hidden" name="HiddenName" value="${Cards.name}">
                </div>
                <div class="information">
                    性别：<font size="3">${Cards.sex}</font>
                    <input type="hidden" name="HiddenSex" value="${Cards.sex}">
                </div>
                <div class="information">
                    公司：<font size="3">${Cards.company}</font>
                    <input type="hidden" name="HiddenCompany" value="${Cards.company}">
                </div>
                <div class="information">
                    职位：<font size="3">${Cards.title}</font>
                    <input type="hidden" name="HiddenTitle" value="${Cards.title}">
                </div>
                <div class="information">
                    联系电话：
                    <font>${Cards.phoneNum}</font>
                    <input type="hidden" name="HiddenPhoneNum" value="${Cards.phoneNum}">
                </div>
                <div class="information">
                    地址：
                    <font>${Cards.address}</font>
                    <input type="hidden" name="HiddenAddress" value="${Cards.address}">
                </div>
                <div class="information" style="float: left">
                    分享：
                    <font>${Cards.shareNum}</font>
                </div>
                <div class="information" style="float: right">
                    收藏：
                    <font>${Cards.collectionNum}</font>
                </div>
            </div>
        </div>
    </c:forEach>
    <div class="clear"></div>
</div>
<div style="position: fixed;top: 90%;margin-left: 35%">
    <p>Copyright &copy; 2018.Company name All rights reserved.YJM</p>
</div>
<script src="js/jquery-3.3.1.min.js"></script>
<!-- //Jquery -->
<!-- effect js -->
<script src="js/canva_moving_effect.js"></script>
</body>
</html>
