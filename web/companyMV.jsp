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

<!-- content -->
<jsp:include page="navBar.jsp">
    <jsp:param name="focus" value="1"/>
</jsp:include>
<div class="cardFrame">
    <c:forEach items="${sessionScope.Companies}" var="Company" step="1" varStatus="status">
        <div class="card">
            <div class="cardTop">
                <div style="padding-top: 20px;">
                    <font size="5">${Company.name}</font>
                    <input name="companyNameInput" type="hidden" value="${Company.name}">
                </div>
                <button class="updateButton" onclick="openUpdatePop(${status.index})">修改</button>
                <a href="inner/manager/company/DeleteCompany?companyNum=${Company.companyNum}">
                    <button class="deleteButton" onclick="javascript:return del();">删除</button>
                </a>
                <button class="addEmployee"
                        onclick="openAddPop('${Company.companyNum}')">任命HR
                </button>
                <div style="clear: both; width: 0; height: 0;"></div>
            </div>
                <%--            点击跳出部门管理面板--%>
            <div class="cardBottom" onclick="window.open('inner/manager/LoadDepartment?companyNum=${Company.companyNum}','_self')">
                <div class="information">
                    公司地址：<font size="3">${Company.location}</font>
                    <input name="locationInput" type="hidden" value="${Company.location}">
                </div>
                <div class="information">
                    前台电话：<font size="3">${Company.phoneNum}</font>
                    <input name="phoneNumInput" type="hidden" value="${Company.phoneNum}">
                </div>
                <div class="information">
                    公&nbsp;司&nbsp;号&nbsp;：<font size="3">${Company.companyNum}</font>
                    <input name="companyNumInput" type="hidden" value="${Company.companyNum}">
                </div>
                <div class="information" style="height: 100px">
                    公司简介：<font>${Company.introduce}</font>
                    <input name="introduceInput" type="hidden" value="${Company.introduce}">
                </div>
            </div>
        </div>
    </c:forEach>

    <!-- 添加卡片 -->
    <div class="card">
        <div class="cardTop">
            <div style="padding-top: 20px;">
                <font size="5">添加公司</font>
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
</div>
<!-- 卡片结束 -->
<!-- 弹出添加框 -->
<div class="popFrame" id="popFrame" style="display: none">
    <form action="" method="post" enctype="application/x-www-form-urlencoded" accept-charset="UTF-8" id="popForm" onsubmit="removeNumDisable()">
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                公司号
            </label>
            <input placeholder="英文、数字长度为1-10个字符" name="companyNum" type="text" required pattern="[a-zA-Z0-9]{1,10}">
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                公司名称
            </label>
            <input placeholder="公司名" name="companyName" type="text" required pattern=".{1,10}">
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                公司地址
            </label>
            <input placeholder="公司地址" name="companyLocation" type="text" required pattern=".{1,10}">
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                电话
            </label>
            <input placeholder="电话" name="companyPhoneNum" type="text" required pattern=".{1,10}">
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                简介
            </label>
            <input placeholder="简介" name="companyIntroduce" type="text" required pattern=".{1,10}">
        </div>
        <input type="submit" value="保存" name="RegisterSubmit">
        <input type="button" value="取消" name="cancel" onclick="closePop()">
    </form>
    <!-- 弹出添加框 -->
    <!-- copyright -->
</div>
<!-- 弹出添加HR框-->
<div class="addEmployeeFrame" id="addEmployeeFrame">
    <form action="inner/manager/EmployeeServlet?op=add" method="post" enctype="application/x-www-form-urlencoded" accept-charset="UTF-8" id="addEmployeeForm">
        <input type="hidden" id="companyNumInput" name="companyNum">
        <input type="hidden" name="departmentNum" value="RS">
        <input type="hidden" name="title" value="HR">
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                ID：
            </label>
            <input placeholder="请输入对方ID" name="id" id="UserId" type="text" required pattern="[a-zA-Z0-9]{1,10}">
            <span id="IdNotExitSpan" style="color:red;display:none" class="error">用户不存在</span>
            <span id="EmployeeExitSpan" style="color:red;display:none" class="error">此用户已经是员工</span>
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                薪资;
            </label>
            <input placeholder="薪资" name="wages" type="text" required pattern="[0-9]{1,10}.?[0-9]{1,2}">
        </div>
        <input type="submit" value="保存" name="RegisterSubmit">
        <input type="button" value="取消" name="cancel" onclick="closeAddPop()">
    </form>
</div>
<!-- 弹出添加人员框-->

<!-- //content -->

<!-- copyright -->
<div style="position: fixed;top: 90%;margin-left: 35%">
    <p>Copyright &copy; 2018.Company name All rights reserved.YJM</p>
</div>
<!-- //copyright -->
<script src="js/jquery-3.3.1.min.js"></script>
<!-- //Jquery -->
<script src="js/ajaxQuerry.js"></script>
<script src="js/managerJs.js"></script>
<script src="js/deletJS.js"></script>
<!-- effect js -->
<script src="js/canva_moving_effect.js"></script>

</body>

</html>