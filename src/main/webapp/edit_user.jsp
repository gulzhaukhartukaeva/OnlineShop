<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<jsp:useBean id="Const" class="com.epam.onlineShopService.constants.GeneralConstants"/>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <style>
        <%@include file="/css/nav.css" %>
        <%@include file="/css/adminSection.css" %>
    </style>
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Edit User</title>
</head>
<body>
<jsp:include page="/navAdminSection.jsp"/>
<jsp:include page="/menuSection.jsp"/>
<div class="addGroup" style="margin-left: 535px; margin-right: 400px; border: 1px solid aqua; padding-top: 20px; padding-right: 10px;
    height: 275px; width: 500px;">
    <form action="editUser">
        <div class="form-group">
            <label for="banned" style="margin-top: 20px; margin-bottom: 10px; margin-left: 200px;">Select User Role</label>
            <select class="form-control" id="userRole" name="userRole" style="margin-left: 200px; width: 110px; height: 30px;">
                <c:forEach var="role" items="${sessionScope.roles}">
                    <c:if test="${role.id eq userEdit.roleID}">
                        <option selected value=${role.id}>${role.role}</option>
                    </c:if>
                    <c:if test="${role.id ne userEdit.roleID}">
                        <option value=${role.id}>${role.role}</option>
                    </c:if>
                </c:forEach>
            </select>
            <input name="id" id="id" type="hidden" value="${sessionScope.userEdit.id}">
            <label class="form-check-label" for="banned" style="margin-left: 200px; margin-top: 30px;">Select Banned Status</label>
            <select class="form-control" id="banned" name="banned" style="margin-left: 200px; width: 110px; height: 30;">
                <c:if test="${userEdit.isBanned eq 'false'}">
                    <option selected value="false">NO</option>
                    <option value="true">YES</option>
                </c:if>
                <c:if test="${userEdit.isBanned eq 'true'}">
                    <option selected value="true">YES</option>
                    <option value="false">NO</option>
                </c:if>

            </select>
        </div>
        <button type="submit" class="btn btn-primary" style="margin-left: 180px; margin-top: 10px;">Save</button>
    </form>
</div>
</body>
</html>