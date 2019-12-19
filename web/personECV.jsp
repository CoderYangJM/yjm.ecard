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
    <!-- Style-CSS -->
    <link rel="stylesheet" href="css/cardCSS.css">
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

<!-- content -->
<jsp:include page="navBar.jsp">
    <jsp:param name="focus" value="3"/>
</jsp:include>

<div class="cardFrame">
    <%--    改进点：先判断是否存在某条信息（如地址）若不存在则不打印--%>
    <c:forEach items="${sessionScope.Cards}" var="Cards" step="1" varStatus="status">

        <div class="card">
            <div class="cardTop">
                <div style="padding-top: 20px;">
                    <font size="5">${Cards.name}</font>
                </div>
                <a href="inner/CardServlet?cardNum=${Cards.cardNum}&op=delete">
                    <button class="leftButton" onclick="javascript:return del();">删除</button>
                </a>
                <button class="midButton" onclick="openUpdatePop('${Cards.cardNum}','${status.index}')">修改</button>
                <button class="rightButton"
                        onclick="openSharePop(${Cards.cardNum})">分享
                </button>
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
    <!-- 添加卡片 -->
    <div class="card">
        <div class="cardTop">
            <div style="padding-top: 20px;">
                <font size="5">添加卡片</font>
            </div>
        </div>
        <div class="cardBottom">
            <div style="width: 100%;height: 100%; margin: 0;padding: 0;">
                <div class="information">
                    <button style="margin-left: 65px;margin-top: 20px; border-radius: 50%;" onclick="openPop()">
                        <img src="https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1704929798,2496931785&fm=26&gp=0.jpg"
                             style="width: 85px;border-radius: 50%;"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
<!-- 卡片结束 -->
<!-- 弹出添加框 -->
<div class="popFrame" id="addCardFrame">
    <form action="" method="post" id="addCardForm">
        <input name="cardNum" type="hidden" value="">
        <input name="id" type="hidden" value="${sessionScope.user.id}">
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                名字
            </label>
            <input placeholder="名字" name="name" type="text" required pattern=".{1,10}">
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                性别
            </label>
            <input type="radio" value="男"  name="sex" id="manRadio">男
            <input type="radio" value="女" name="sex" id="womanRadio">女
        </div>
        <div style="width: 100%;height: 10px;background: white"></div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                地址
            </label>
            <input placeholder="地址" name="address" type="text" required pattern=".{1,10}">
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                电话
            </label>
            <input placeholder="电话" name="phoneNum" type="text" required pattern=".{1,11}">
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                公司
            </label>
            <input placeholder="公司" name="company" type="text" required pattern=".{1,10}">
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                职位
            </label>
            <input placeholder="职位" name="title" type="text" required pattern=".{1,10}">
        </div>
        <input type="submit" value="保存">
        <input type="button" value="取消" onclick="closePop()">
    </form>
</div>
<!-- 弹出添加框 -->
<%--弹出分享框--%>
<div class="popFrame" id="shareFrame">
    <form action="cardServlet?op=share" method="post" enctype="application/x-www-form-urlencoded"
          accept-charset="UTF-8" id="shareForm">
        <input type="hidden" name="cardNum" value="">
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                ID：
            </label>
            <input placeholder="请输入对方ID" name="id" type="text" required pattern="[a-zA-Z0-9]{1,10}">
        </div>
        <input type="submit" value="确定" name="RegisterSubmit">
        <input type="button" value="取消" name="cancel" onclick="closeSharePop()">
    </form>
</div>
<%--弹出分享框--%>
<!-- //content -->

</p>
<!-- copyright -->
<div style="position: fixed;top: 90%;margin-left: 35%">
    <p>Copyright &copy; 2018.Company name All rights reserved.YJM</p>
</div>
<!-- //copyright -->
<script src="js/jquery-3.3.1.min.js"></script>
<!-- //Jquery -->
<script src="js/cardJS.js"></script>
<script src="js/deletJS.js"></script>
<!-- effect js -->
<script src="js/canva_moving_effect.js"></script>
</body>

</html>