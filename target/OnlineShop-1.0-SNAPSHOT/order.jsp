<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>
<jsp:useBean id="Const" class="com.epam.online.shop.service.constants.GeneralConstants"/>

<html>
<head>
    <style><%@include file="/css/nav.css"%></style>
    <style><%@include file="/css/home.css"%></style>
    <style><%@include file="/css/footer.css"%></style>
    <style><%@include file="/css/cart.css"%></style>
    <style><%@include file="/css/style.css"%></style>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
    <title>Shopping cart</title>
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
</nav>
<div class="outer" style="margin-bottom: 30px;">
    <form class="form-inline" action="pay">
            <label for="address" class="form-control" style="margin-left: 18px; margin-top: 20px; border: none">
                <fmt:message key="input.address"/></label>
            <input type="text" class="form-control" id="address" name="deliveryAddress" value="${sessionScope.address}"
                   style="width: 1075px; margin-top: 20px; margin-left: 30px;" required>
            <input type="hidden" name="userId" value="${sessionScope.id}">

        <div class="form-group mx-sm-3 mb-2" style="margin-top: 40px;">
            <label for="holder" class="form-control" style="border: none;"><fmt:message key="input.card.holder"/></label>
            <input type="text" class="form-control" id="holder" style="width: 600px; margin-left: 50px;" required>
        </div>
        <div class="form-group mb-2">
            <label for="cardNumber" class="form-control" style="margin-left: 16px; margin-top: 10px; border: none;">
                <fmt:message key="input.card.number"/></label>
            <input class="form-control" min="0" maxlength="16" id="cardNumber"
                   style="margin-top: 10px; margin-left: 117px;" required placeholder="xxxx xxxx xxxx xxxx" autocomplete="cc-number"
            inputmode="numeric" type="tel" pattern="[0-9\s]{13,16}">

            <label for="cvv" class="form-control" style="margin-left: 60px; border: none; margin-top: 10px;">CVV / CVN </label>
            <input type="password" maxlength="3" class="form-control" id="cvv" style="margin-top: 10px; margin-left: 20px; width: 193px;" placeholder="***" required>

        </div>
        <div class="form-group mb-2">
            <label for="expireDate" class="form-control" style="margin-top: 10px; margin-left: 18px; border: none;">
                <fmt:message key="input.expiration.date"/></label>
            <input type="date" class="form-control" id="expireDate" style="margin-top: 10px; margin-left: 102px; width: 222px;" required>
        </div>

        <div class="form-group mb-2">
            <label for="totalPriceCard" class="form-control" style="margin-left:660px; margin-top: 40px; font-weight: 700; border: none;">
                <fmt:message key="input.total.price"/>$</label>
            <input type="text" readonly class="form-control" name="totalPriceCard" id="totalPriceCard"
                   value="${sessionScope.totalPriceCard}" style="background: white; margin-top: 38px; font-weight: 700; border: none; width: 200px;">
        </div>
        <button type="submit" class="btn btn-primary mb-2" style="margin-left: 210px; margin-top: 10px; background: cadetblue;">
            <fmt:message key="button.pay"/></button>
    </form>
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
