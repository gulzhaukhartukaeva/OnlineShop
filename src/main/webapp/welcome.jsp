<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>

<html>
<head>
    <style><%@include file="/css/nav.css"%></style>
    <style><%@include file="/css/home.css"%></style>
    <style><%@include file="/css/footer.css"%></style>
    <style><%@include file="/css/adminSection.css"%></style>
    <style><%@include file="/css/style.css"%></style>
    <jsp:useBean id="Const" class="com.epam.onlineShopService.constants.GeneralConstants"/>

    <title>Kitchen Profi</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
                        <button class="dbtn"><b><fmt:message key="button.login"/></b><i class="fa fa-caret-down"></i></button>
                        <div class="dropd-content">
                            <form action="signup">
                                <input type="submit" style="width: 100px; opacity: 0.5;" class="btn btn-primary btn-sm btn-block"
                                       value="<fmt:message key="button.register"/>">
                            </form>
                            <form action="login">
                                <input type="submit" style="width: 100px; margin-top: 5px; opacity: 0.5;" class="btn btn-primary btn-sm btn-block"
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
        <c:if test="${sessionScope.roleId ne Const.adminRoleId}">
        <ul class="links-container">
            <li class="link-item"><a href="/catalog" class="link"><fmt:message key="catalog"/></a> </li>
        </ul>
        </c:if>
        <c:if test="${sessionScope.roleId eq Const.adminRoleId}">
            <ul class="links-container"></ul>
        </c:if>
    </nav>
    <c:if test="${sessionScope.roleId ne Const.adminRoleId}">
        <header class="main-section"></header>
        <section class="product">
            <h2 class="product-category"><fmt:message key="all.products"/></h2>
            <button class="pre-btn"><img src="/img/img.png"></button>
            <button class="nxt-btn"><img src="/img/img.png"></button>
            <div class="product-container">
                <c:forEach var="product" items="${sessionScope.products}">
                    <form action="addToCart" method="post">
                        <div class="product-card">
                            <div class="product-image">
                                <span class="discount-tag"><b>${product.discount}% off</b></span>
                                <img src="data:image/jpg;base64,${product.logo}" class="rounded-circle"
                                     height="350" width="250"/>
                                <input type="hidden" name="productId" value="${product.id}">
                                <button type="submit" class="card-btn">add to cart</button>
                            </div>
                            <div class="product-info">
                                <h3 class="product-brand">${product.name}</h3>
                                <p class="product-card-description">${product.description}</p>
                                <span class="price">$${product.price}</span>
                            </div>
                        </div>
                    </form>
                </c:forEach>
            </div>
        </section>
        <section class="collection-container">
            <a class="collection">
                <img src="/img/img_55.png" alt="">
                <p class="collection-title">Blomus</p>
            </a>
            <a class="collection">
                <img src="/img/img_56.png" alt="">
                <p class="collection-title">Villeroy $ Boch</p>
            </a>
            <a class="collection">
                <img src="/img/img_58.png" alt="">
                <p class="collection-title">Luminarc</p>
            </a>
        </section>
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
    </c:if>
    <c:if test="${sessionScope.roleId eq Const.adminRoleId}">
        <jsp:include page="/menuSection.jsp"/>
    </c:if>
    <script>
            const productContainers = [...document.querySelectorAll('.product-container')];
            const nxtBtn = [...document.querySelectorAll('.nxt-btn')];
            const preBtn = [...document.querySelectorAll('.pre-btn')];

            productContainers.forEach((item, i)=>{
                let containerDimentions = item.getBoundingClientRect();
                let containerWidth = containerDimentions.width;

                nxtBtn[i].addEventListener('click', ()=>{
                    item.scrollLeft += containerWidth;
                })

                preBtn[i].addEventListener('click', ()=>{
                    item.scrollLeft -= containerWidth;
                })
            })
        </script>
    </body>
</html>
