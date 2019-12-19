<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 22943
  Date: 2019/12/18
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>统计</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8"/>
    <meta name="keywords" content=""/>
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
    <link rel="stylesheet" href="css/cardCSS.css">
    <!-- Font-Awesome-Icons-CSS -->
    <link rel="stylesheet" href="css/fontawesome-all.css">
    <!-- //css files -->
    <!-- web-fonts -->
    <!-- //web-fonts -->
</head>
<body>
<style>
    .statisticList{
        width: 30%;
        background: blanchedalmond;
        border: 2px solid darkseagreen;
        text-align: center;
        display: inline-table;
    }
</style>
<div id="bg">
    <canvas></canvas>
    <canvas></canvas>
    <canvas></canvas>
</div>

<!-- content -->
<jsp:include page="navBar.jsp">
    <jsp:param name="focus" value="5"/>
</jsp:include>
<div class="listFrame" style="margin-top: 100px;margin-left: 10%;">
    <table id="collectionList" class="statisticList">
        <tr>
            <th>名次</th><th>卡号</th><th>拥有者</th><th>被收藏数</th>
        </tr>
        <c:forEach items="${sessionScope.maxCollection}" var="card" varStatus="status">
            <tr>
                <td>第${status.count}名：</td>
                <td>${card.cardNum}</td>
                <td>${card.username}</td>
                <td>${card.collectionNum}</td>
            </tr>
        </c:forEach>
    </table>
    <table id="shareList" class="statisticList">
        <tr>
            <th>名次</th><th>卡号</th><th>拥有者</th><th>分享数</th>
        </tr>
        <c:forEach items="${sessionScope.maxShare}" var="card" varStatus="status">
            <tr>
                <td>第${status.count}名：</td>
                <td>${card.cardNum}</td>
                <td>${card.username}</td>
                <td>${card.collectionNum}</td>
            </tr>
        </c:forEach>
    </table>
    <table id="wageList" class="statisticList">
        <tr>
            <th>名次</th><th>薪资(元/月)</th><th>拥有者</th>
        </tr>
        <c:forEach items="${sessionScope.employeeWages}" var="employee" varStatus="status">
            <tr>
                <td>第${status.count}名：</td>
                <td>${employee.wages}</td>
                <td>${employee.username}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<div style="position: fixed;top: 90%;margin-left: 35%">
    <p>Copyright &copy; 2018.Company name All rights reserved.YJM</p>
</div>
<!-- //copyright -->
<script src="js/jquery-3.3.1.min.js"></script>
<!-- //Jquery -->
<script src="js/cardJS.js"></script>
<!-- effect js -->
<script src="js/canva_moving_effect.js"></script>
</body>
</html>
