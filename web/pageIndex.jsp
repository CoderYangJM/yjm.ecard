<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 22943
  Date: 2019/12/17
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div  style="margin-left:10%;text-align: center;background: #eee;opacity: 0.8;width: 80%">
    <c:forEach var="i" begin="1" end="${sessionScope.pagElementNum}" step="1" varStatus="status">
        <div style="display: inline-block">
            <a href="inner/manager/LoadEmployee?pageNum=${status.index}&companyNum=${sessionScope.companyNum}&departmentNum=${sessionScope.departmentNum}">
            <font style="<c:if test="${status.index==sessionScope.currentPage}">color:red</c:if>">
                    第 ${status.index}页
            </font>
            </a>
        </div>
    </c:forEach>
</div>
