<%--
  Created by IntelliJ IDEA.
  User: 22943
  Date: 2019/12/15
  Time: 8:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>employee</title>
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
<%--导航栏--%>
<%@ include file="navBar.jsp" %>

<table border="2" width="80%"
       style="margin-left: 10%;text-align: center;background: #eeeeee;opacity: 0.8;margin-top: 30px">
    <tr>
        <th>id</th>
        <th>姓名</th>
        <th>职位</th>
        <th>入职时间</th>
        <th>薪资</th>
        <th colspan="3">操作</th>
    </tr>
    <c:forEach items="${sessionScope.employeeList}" var="employee" step="1" varStatus="status">
        <tr>
            <td>${employee.id}</td>
            <input type="hidden" value="${employee.id}" name="idInput">
            <td>${employee.name}</td>
            <td>${employee.title}</td>
            <input type="hidden" value="${employee.title}" name="titleInput">
            <td>${employee.hireDate}</td>
            <input type="hidden" value="${employee.hireDate}" name="dateInput">
            <td>${employee.wages}</td>
            <input type="hidden" value="${employee.wages}" name="wagesInput">
            <td ><button
                    <c:if test="${employee.title=='HR' && sessionScope.role=='HR'}">
                        onclick="javascript:return canNotDel();"
                    </c:if>
                    <c:if test="${employee.title!='HR' || sessionScope.role!='HR'}">
                        onclick="openUpdatePop(${status.index})"
                    </c:if>
                    style="border: none;background: none">修改</button>
            </td>
            <td>
                <a aria-disabled="true"
                   href="inner/manager/EmployeeServlet?id=${employee.id}&companyNum=${employee.companyNum}&departmentNum=${employee.departmentNum}&op=delete"
<%--                        HR无权删除HR--%>
                        <c:if test="${employee.title=='HR' && sessionScope.role=='HR'}">
                            onclick="javascript:return canNotDel();"
                        </c:if>
                        <c:if test="${employee.title!='HR' || sessionScope.role!='HR'}">
                            onclick="javascript:return del();"
                        </c:if>
                >
                    删除
                </a>
            </td>
            <td>
                <a href="inner/manager/LoadEmployeeCard?id=${employee.id}">查看名片</a>
            </td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="pageIndex.jsp"></jsp:include>
<div class="popFrame" id="popFrame" style="display: none">
    <form action="" method="post" enctype="application/x-www-form-urlencoded" accept-charset="UTF-8" id="popForm"
          onsubmit="removeDisable()">
        <input type="hidden" name="companyNum" value="${sessionScope.companyNum}">
        <input type="hidden" name="departmentNum" value="${sessionScope.departmentNum}">
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                id
            </label>
            <input placeholder="id" name="id" type="text" required pattern=".{1,10}">
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                职位
            </label>
            <input placeholder="职位" name="title" type="text" required pattern=".{1,10}">
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                薪资
            </label>
            <input placeholder="薪资（可精确至小数点后两位）" name="wages" type="text" required pattern="[0-9]{1,10}.?[0-9]{1,2}">
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                入职时间
            </label>
            <input placeholder="格式：2001-01-01" name="date" type="text" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}">
        </div>
        <input type="submit" value="保存" name="RegisterSubmit">
        <input type="button" value="取消" name="cancel" onclick="closePop()">
    </form>
</div>
<div style="position: fixed;top: 95%;margin-left: 35%">
    <p>Copyright &copy; 2018.Company name All rights reserved.YJM</p>
</div>
<script src="js/jquery-3.3.1.min.js"></script>
<!-- //Jquery -->
<script src="js/employeeJS.js"></script>
<script src="js/deletJS.js"></script>
<!-- effect js -->
<script src="js/canva_moving_effect.js"></script>
</body>
</html>
