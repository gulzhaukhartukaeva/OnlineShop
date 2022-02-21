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

    <title><fmt:message key="cart"/></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body>
<nav class="navbar">
    <div class="nav">
        <div>
            <a href="/"><img src="/img/img_13.png" class="brand-logo" alt=""></a>
        </div>
        <div class="nav-items">
            <c:if test="${sessionScope.roleId eq null}">
                <a href="/cart"><img src="/img/img_1.png"></a>
                <div class="dropd">
                    <button class="dbtn"><b>Login</b><i class="fa fa-caret-down"></i></button>
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
                                   value="Кабинет">
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
<form action="orderPage">
    <p class="totalPrice"><fmt:message key="total.price"/>: $${sessionScope.totalPrice}</p>
    <section id="cart-container" class="container my-5">
        <table id="data2">
            <thead>
                <tr>
                    <td><fmt:message key="product.image"/></td>
                    <td><fmt:message key="product.name"/></td>
                    <td><fmt:message key="product.price"/></td>
                    <td><fmt:message key="product.quantity"/></td>
                    <td><fmt:message key="remove"/></td>
                </tr>
        </thead>
            <tbody>
                <c:forEach var="cartItem" items="${sessionScope.cartItems}">
                <tr>
                    <td><img src="data:image/jpg;base64,${cartItem.product.logo}" class="rounded-circle"
                             alt="logo" height="100" width="100"/></td>
                    <td>${cartItem.product.name}</td>
                    <td><input class="w-25 pl-1" value="${cartItem.product.price}" readonly style="border: none; text-align: center"
                               name="price"></td>
                    <td><input class="w-25 pl-1" value="${cartItem.amount}" type="number" readonly name="amount" style="border: none;"></td>
                    <td><form action="removeFromCart" method="get">
                            <input type="hidden" name="id" value="${cartItem.id}">
                            <input type="submit" class="btn btn-primary btn-sm" value="Remove"
                                   style="width: 100px; border: 1px solid aqua;">
                    </form></td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </section>
    <input type="submit" class="btn btn-primary btn-sm btn-block" name="checkout" value="<fmt:message key="input.proceed.checkout"/>"
               style="background: none; font-size: 14px; margin: auto; width: 150px; margin-bottom: 30px; color:blue;">
</form>
<footer>
    <div class="footer-content">
        <img src="/img/img_13.png" class="logo" alt="">
    </div>
    <p class="footer-title"><fmt:message key="about.company"/></p>
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
<script>
    const sizeBtns = document.querySelectorAll('.size-radio-btn');
    let checkedBtn = 0;

    sizeBtns.forEach((item, i) =>{
        item.addEventListener('click', () =>{
            sizeBtns[checkedBtn].classList.remove('check');
            item.classList.add('check');
            checkedBtn = i;
        })
    })
</script>
</body>
</html>
