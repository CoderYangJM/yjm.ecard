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
    <jsp:param name="focus" value="2"/>
</jsp:include>
<div class="cardFrame">
    <c:forEach items="${sessionScope.Departments}" var="Department" step="1" varStatus="status">
        <div class="card">
            <div class="cardTop">
                <div style="padding-top: 20px;">
                    <font size="5">${Department.name}</font>
                    <input name="departmentNameInput" type="hidden" value="${Department.name}">
                </div>
                <button class="updateButton" onclick="openUpdatePop(${status.index})">修改</button>
                <c:if test="${sessionScope.role=='Manager'}">
                    <a href="inner/manager/DeleteDepartment?departmentNum=${Department.departmentNum}&companyNum=${sessionScope.companyNum}">
                        <button class="deleteButton" onclick="javascript:return del();">删除</button>
                    </a>
                </c:if>
                    <%--                跳出部门信息修改界面--%>
                <button class="addEmployee"
                        onclick="openAddPop('${sessionScope.companyNum}','${Department.departmentNum}')">添加人员
                </button>
                <div style="clear: both; width: 0; height: 0;"></div>
            </div>
                <%--            点击跳出员工管理面板传出公司号及部门号--%>
            <div class="cardBottom"
                 onclick="window.open('inner/manager/LoadEmployee?departmentNum=${Department.departmentNum}&companyNum=${sessionScope.companyNum}&employeeNum=${Department.employeeNum}','_self')">
                <div class="information">
                    部门名：<font size="3">${Department.name}</font>
                    <input name="departmentNameInput" type="hidden" value="${Department.name}">
                </div>
                <div class="information">
                    部&nbsp;门&nbsp;号&nbsp;：<font size="3">${Department.departmentNum}</font>
                    <input name="departmentNumInput" type="hidden" value="${Department.departmentNum}">
                </div>
                <div class="information">
                    部门人数&nbsp;：<font size="3">${Department.employeeNum}</font>
                </div>
                <div class="information" style="height: 100px">
                    部门简介：<font>${Department.introduce}</font>
                    <input name="introduceInput" type="hidden" value="${Department.introduce}">
                </div>
            </div>
        </div>
    </c:forEach>

    <!-- 添加卡片 -->
    <div class="card">
        <div class="cardTop">
            <div style="padding-top: 20px;">
                <font size="5">添加部门</font>
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
<div class="popFrame" id="popFrame" style="display: none">
    <form action="" method="post" enctype="application/x-www-form-urlencoded" accept-charset="UTF-8" id="popForm" onsubmit="removeDisable()">
        <input type="hidden" name="companyNum" value="${sessionScope.companyNum}">
        <%--    <input  type="" name="companyNum" value="1">--%>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                部门号
                <c:if test="${sessionScope.addDepartmentState==false}">
                    <font color="red">部门号已存在</font>
                    <%
                        request.getSession().setAttribute("addDepartmentState", true);
                    %>
                </c:if>
            </label>
            <input placeholder="英文、数字长度为1-10个字符" name="departmentNum" type="text" required pattern="[a-zA-Z0-9]{1,10}">
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                部门名称
            </label>
            <input placeholder="部门名" name="departmentName" type="text" required pattern=".{1,10}">
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                简介
            </label>
            <input placeholder="简介" name="departmentIntroduce" type="text" required pattern=".{1,10}">
        </div>
        <input type="submit" value="保存" name="RegisterSubmit">
        <input type="button" value="取消" name="cancel" onclick="closePop()">
    </form>
</div>
<!-- 弹出添加部门框 -->
<!-- 弹出添加人员框-->

<div class="addEmployeeFrame" id="addEmployeeFrame">
    <form action="inner/manager/EmployeeServlet?op=add" method="post" enctype="application/x-www-form-urlencoded" accept-charset="UTF-8" id="addEmployeeForm">
        <input type="hidden" id="companyNumInput" name="companyNum">
        <input type="hidden" id="departmentNumInput" name="departmentNum">
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
                职位：
            </label>
            <input placeholder="职位" name="title" type="text" required pattern=".{1,10}">
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                薪资：
            </label>
            <input placeholder="薪资" name="wages" type="text" required pattern="[0-9]{1,10}.?[0-9]{1,2}">
        </div>
        <input type="submit" value="保存" name="RegisterSubmit">
        <input type="button" value="取消" name="cancel" onclick="closeAddPop()">
    </form>
</div>
<!-- 弹出添加人员框-->
<!-- //content -->

</p>
<!-- copyright -->
<div style="position: fixed;top: 90%;margin-left: 35%">
    <p>Copyright &copy; 2018.Company name All rights reserved.YJM</p>
</div>
<!-- //copyright -->
<script src="js/jquery-3.3.1.min.js"></script>
<!-- //Jquery -->
<script src="js/departmentJs.js"></script>
<script src="js/ajaxQuerry.js"></script>
<script src="js/deletJS.js"></script>
<!-- effect js -->
<script src="js/canva_moving_effect.js"></script>
</body>

</html>