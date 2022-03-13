<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<jsp:useBean id="Const" class="com.epam.online.shop.service.constants.GeneralConstants"/>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>

<html>
<head>
    <style>
        <%@include file="/css/nav.css" %>
        <%@include file="/css/home.css" %>
        <%@include file="/css/footer.css" %>
        <%@include file="/css/adminSection.css" %>
        <%@include file="/css/style.css" %>
    </style>
    <title><fmt:message key="profile"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar">
    <div class="nav">
        <div>
            <img src="/img/img_13.png" class="brand-logo" alt="">
        </div>
        <div class="nav-items">
            <c:if test="${sessionScope.roleId eq Const.userRoleId}">
                <a href="/cart"><img src="/img/img_1.png"></a>
                <div class="dropd">
                    <button class="dbtn"><b>${sessionScope.email}</b><i class="fa fa-caret-down"></i></button>
                    <div class="dropd-content">
                        <form action="logout">
                            <input type="submit" style="width: 100px; margin-top: 5px;" class="btn btn-primary btn-sm btn-block"
                                   value="<fmt:message key="button.logout"/>">
                        </form>
                    </div>
                </div>
            </c:if>
            <c:if test="${sessionScope.roleId eq Const.adminRoleId}">
                <div class="dropd">
                    <button class="dbtn"><b>${sessionScope.email}</b><i class="fa fa-caret-down"></i></button>
                    <div class="dropd-content">
                        <form action="logout">
                            <input type="submit" style="width: 100px; margin-top: 5px;" class="btn btn-primary btn-sm btn-block"
                                   value="<fmt:message key="button.logout"/>">
                        </form>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
    <c:if test="${sessionScope.roleId eq Const.userRoleId}">
        <ul class="links-container">
        </ul>
    </c:if>
    <c:if test="${sessionScope.roleId eq Const.adminRoleId}">
        <ul class="links-container">
        </ul>
    </c:if>
</nav>
<c:if test="${sessionScope.roleId eq Const.adminRoleId}">
    <jsp:include page="/menuSection.jsp"/>
    <h6 class="titleBtn"><fmt:message key="button.cabinet"/></h6>
    <div class="profile" style="margin-left: 400px; margin-right: 400px; border: 1px solid aqua; padding-top: 20px;
    padding-right: 10px; height: 300px; width: 765px;">
        <form class="form-inline" action="changePassword">
            <div class="form-group mb-2">
                <input type="password" class="form-control" id="newPassword" name="newPassword"
                       placeholder="<fmt:message key="input.enter.password"/>" style="width: 180px; margin-left: 50px;">
            </div>
            <div class="form-group mx-sm-3 mb-2">
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword"
                       placeholder="<fmt:message key="input.reenter.password"/>" style="width: 180px; margin-left: 20px;">
                <button type="submit" class="btn btn-primary mb-2" style="margin-left: 10px;">
                    <fmt:message key="button.change.password"/></button>
            </div>
            <input type="hidden" name="userId" id="userId" value="${sessionScope.id}">
            <c:if test="${requestScope.passwordNotEqual != null}">
                <small class="text-success" style="margin-left: 10px;"><fmt:message key="alert.update.password.not.equal"/></small>
            </c:if>
        </form>
        <form class="form-inline" action="changeLanguage" style="margin-top: 40px;">
            <label>
                <select name="languageToChange" class="form-control" style="margin-left: 50px; height: 35px;">
                    <c:forEach var="language" items="${sessionScope.languages}">
                        <c:if test="${language.id eq languageId}">
                            <option selected value="${language.language}">${language.language}</option>
                        </c:if>
                        <c:if test="${language.id ne languageId}">
                            <option value="${language.language}">${language.language}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </label>
            <input type="hidden" name="direction" value="cabinet.jsp">
            <button type="submit" class="btn btn-primary mb-2" style="margin-left: 20px">
                <fmt:message key="button.change.language"/></button>
        </form>
    </div>
</c:if>
<c:if test="${sessionScope.roleId eq Const.userRoleId}">
    <h6 class="titleBtn"><fmt:message key="button.cabinet"/></h6>
    <div class="profile" style="margin-left: 400px; margin-right: 400px; border: 1px solid aqua; padding-top: 20px;
    padding-right: 10px; height: 300px; width: 765px;">
        <form class="form-inline" action="changeAddress">
            <div class="form-group mb-2">
                <input type="text" class="form-control" name="newAddress" value="${sessionScope.address}"
                       style="width: 385px; margin-left: 50px; margin-bottom: 40px; margin-top: 40px;">
                <button type="submit" class="btn btn-primary mb-2" style="margin-left: 10px;">
                    <fmt:message key="button.change.address"/></button>
                <input type="hidden" name="userId" value="${sessionScope.id}">
            </div>
        </form>
        <form class="form-inline" action="changePassword">
            <div class="form-group mb-2">
                <input type="password" class="form-control" name="newPassword"
                       placeholder="<fmt:message key="input.enter.password"/>" style="width: 180px; margin-left: 50px;">
            </div>
            <div class="form-group mx-sm-3 mb-2">
                <input type="password" class="form-control" name="confirmPassword"
                       placeholder="<fmt:message key="input.reenter.password"/>" style="width: 180px; margin-left: 20px;">
                <button type="submit" class="btn btn-primary mb-2" style="margin-left: 10px;">
                    <fmt:message key="button.change.password"/></button>
            </div>
            <input type="hidden" name="userId" id="userId" value="${sessionScope.id}">
            <c:if test="${requestScope.passwordNotEqual != null}">
                <small class="text-success" style="margin-left: 10px;"><fmt:message key="alert.update.password.not.equal"/></small>
            </c:if>
        </form>
        <form class="form-inline" action="changeLanguage" style="margin-top: 40px;">
            <label>
                <select name="languageToChange" class="form-control" style="margin-left: 50px; height: 35px;">
                    <c:forEach var="language" items="${sessionScope.languages}">
                        <c:if test="${language.id eq languageId}">
                            <option selected value="${language.language}">${language.language}</option>
                        </c:if>
                        <c:if test="${language.id ne languageId}">
                            <option value="${language.language}">${language.language}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </label>
            <input type="hidden" name="direction" value="cabinet.jsp">
            <button type="submit" class="btn btn-primary mb-2" style="margin-left: 20px">
                <fmt:message key="button.change.language"/></button>
        </form>
    </div>
</c:if>
</body>
</html>

