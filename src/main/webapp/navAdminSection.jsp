<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<jsp:useBean id="Const" class="com.epam.online.shop.service.constants.GeneralConstants"/>
<style><%@include file="/css/style.css"%></style>

<nav class="navbar">
    <div class="nav">
        <div>
            <img src="/img/img_13.png" class="brand-logo" alt="">
        </div>
        <div class="nav-items">
            <c:if test="${sessionScope.roleId eq Const.adminRoleId}">
                <div class="dropd">
                    <button class="dbtn"><b>${sessionScope.email}</b><i class="fa fa-caret-down"></i></button>
                    <div class="dropd-content">
                        <form action="profilePage">
                            <input type="hidden" name="userId" id="userId" value="${sessionScope.id}">
                            <input type="submit" style="width: 100px;" class="btn btn-primary btn-sm btn-block"
                                   value="<fmt:message key="button.cabinet"/>">
                        </form>
                        <form action="logout">
                            <input type="submit" style="width: 100px; margin-top: 5px;" class="btn btn-primary btn-sm btn-block"
                                   value="<fmt:message key="button.logout"/>">
                        </form>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
    <c:if test="${sessionScope.roleId eq Const.adminRoleId}">
        <ul class="links-container"></ul>
    </c:if>
</nav>