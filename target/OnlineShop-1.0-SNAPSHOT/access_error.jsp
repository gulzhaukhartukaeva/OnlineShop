<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<jsp:useBean id="Const" class="com.epam.onlineShopService.constants.GeneralConstants"/>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <style><%@include file="/css/nav.css"%></style>
    <style><%@include file="/css/home.css"%></style>
    <style><%@include file="/css/footer.css"%></style>
    <style><%@include file="/css/cart.css"%></style>
    <style><%@include file="/css/register_success.css"%></style>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title><fmt:message key="access.denied"/></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body>
<nav class="navbar">
    <div class="nav">
        <div>
            <img src="/img/img_13.png" class="brand-logo" alt="">
        </div>
        <div class="nav-items">
            <c:if test="${sessionScope.roleId eq null}">
                <a href="/cart"><img src="/img/img_1.png"></a>
                <div class="dropd">
                    <button class="dbtn"><b><fmt:message key="button.login"/></b><i class="fa fa-caret-down"></i></button>
                    <div class="dropd-content">
                        <form action="signup">
                            <input type="submit" style="width: 100px;" class="btn btn-primary btn-sm btn-block"
                                   value="<fmt:message key="button.register"/>">
                        </form>
                        <form action="login">
                            <input type="submit" style="width: 100px; margin-top: 5px;" class="btn btn-primary btn-sm btn-block"
                                   value="<fmt:message key="button.login"/>">
                        </form>
                    </div>
                </div>
            </c:if>
            <c:if test="${sessionScope.roleId eq Const.userRoleId}">
                <a href="/cart"><img src="/img/img_1.png"></a>
                <div class="dropd">
                    <button class="dbtn"><b>${sessionScope.email}</b><i class="fa fa-caret-down"></i></button>
                    <div class="dropd-content">
                        <form action="profilePage">
                            <input type="hidden" name="userId" value="${sessionScope.id}">
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
    <c:if test="${sessionScope.roleId ne Const.adminRoleId}">
        <ul class="links-container">
            <li class="link-item"><a href="/catalog" class="link"><fmt:message key="catalog"/></a> </li>
        </ul>
    </c:if>
</nav>
<div class="container">
    <div class="text-danger">
        <h1><fmt:message key="access.denied"/></h1>
    </div>
</div>
<footer>
    <div class="footer-content">
        <img src="/img/img_13.png" class="logo" alt="">
    </div>
    <p class="footer-title">About Company</p>
    <p class="info">
        KITCHEN PROFI.COM e-commerce website is operated by BEBEO, which has
        registered capital of €949,770 and head offices located in Paris
        (19 rue des Petites Écuries - 75010 Paris) and is registered with
        the Paris Trade & Companies Register under number 430 370 841.
    </p>
    <p class="info">support emails - kitchenprofi@gmail.com, customersupport@gmail.com</p>
    <p class="info">telephone - 8 7172 77 77 77, 8 7273 88 88 88</p>
    <div class="footer-social-container">
        <div>
            <a href="#" class="social-link">privacy page</a>
            <a href="#" class="social-link">terms & services</a>
        </div>
        <div>
            <a href="#" class="social-link">instagram</a>
            <a href="#" class="social-link">twitter</a>
        </div>
    </div>
</footer>
</body>
</html>
