<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 22943
  Date: 2019/12/11
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navBar" style="height: 50px;width: 100%;background:#eee;border-radius: 10px;text-align: center;font-size: 30px;opacity:
    0.8;">
    <span style="margin: 30px">
    <c:if test="${sessionScope.role=='Manager'}">
        <a href="inner/manager/company/LoadCompany"><font style="<c:if test="${param.get('focus')==1}"> color: red;</c:if>">公司管理</font></a>
    </c:if>
    </span>
    <span style="margin: 30px">
    <c:if test="${sessionScope.role=='HR'}">
        <a href="inner/manager/LoadDepartment"><font style="<c:if test="${param.get('focus')==2}"> color: red;</c:if>">部门管理</font></a>
    </c:if>
    </span>
    <span style="margin: 30px;">
    <a href="inner/LoadCard"><font style="<c:if test="${param.get('focus')==3}"> color: red;</c:if>">我的名片</font></a>
    </span>

    <span style="margin-left: 50px;margin-right: 50px">
        <a href="inner/LoadCollectionCard"><font style="<c:if test="${param.get('focus')==4}"> color: red;</c:if>">收藏夹</font></a>
    </span>
    <c:if test="${sessionScope.role=='Manager'}">
        <a href="inner/manager/company/LoadStatistic"><font style="<c:if test="${param.get('focus')==5 }"> color: red;</c:if>">统计</font></a>
    </c:if>
    <span style="float: right;margin-top: 10px;margin-right: 30px;font-size: 20px ">
        <span style="margin-right: 20px">欢迎${sessionScope.user.userName}</span>
        <a href="inner/LogoutServlet">退出登录</a>
        <a href="updateUser.jsp">修改账号</a>
    </span>
</div>