<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<jsp:useBean id="Const" class="com.epam.online.shop.service.constants.GeneralConstants"/>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>

<!doctype html>
<html lang="en">
<head>
    <style><%@include file="/css/nav.css"%></style>
    <style><%@include file="/css/home.css"%></style>
    <style><%@include file="/css/footer.css"%></style>

    <title><fmt:message key="catalog"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="main.css">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600;700&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/332a215f17.js" crossorigin="anonymous"></script>
</head>
<style>
    li{
        list-style-type: none;
        padding: 10px;
        border: none;
    }

    .side-content .list{
        font-size: 18px;
        font-weight: 700;
        text-align: center;
        padding-bottom: 20px;
    }

    .breadcrumb .crumb-inner p{
        font-size: 15px;
        color: #fff;
    }

    .side-area{
        margin-bottom: 30px;
    }
    .side-area:last-child{
        margin-bottom: 0px;
    }

    .side-shadow{
        box-shadow: 0px 10px 10px 0px rgba(153,153,153,0.2);
    }
    .side-title h3{
        margin-bottom: 20px;
        font-size: 14px;
        color: #fff;
        font-weight: 600;
        line-height: 40px;
        position: relative;
        background: #444546;
        padding: 10px 15px 10px 80px;
        text-transform: uppercase;
    }
</style>
<body>
    <nav class="navbar">
        <div class="nav" style="margin-right: 50px;">
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
    <section class="category">
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div>
                    <aside class="side-area product-side side-shadow mt-5">
                        <div class="side-title">
                            <h3><fmt:message key="categories"/></h3>
                        </div>
                        <div class="side-content">
                            <ul class="list">
                                <c:forEach var="category" items="${sessionScope.categories}">
                                    <form action="findProductByCategory">
                                        <input type="hidden" value="${category.id}" name="categoryId">
                                        <input type="hidden" value="${category.name}" name="categoryName">
                                        <li><input type="submit" value="${category.name}"></li>
                                    </form>
                                </c:forEach>
                            </ul>
                        </div>
                    </aside>
                </div>
            </div>
            <div class="col-lg-9">
                <div class="row">
                    <c:forEach var="product" items="${sessionScope.products}">
                        <div class="col-lg-4 col-sm-6">
                            <div class="product-container" style="margin-bottom: 40px; margin-top: 40px; width: 400px;">
                                <form action="addToCart" method="post">
                                    <div class="product-card">
                                        <div class="product-image">
                                            <span class="discount-tag"><b>${product.discount}% off</b></span>
                                            <img src="data:image/jpg;base64,${product.image}" height="200" width="200" style="object-fit: cover;"/>
                                            <input type="hidden" name="productId" value="${product.id}">
                                            <button type="submit" class="card-btn"><fmt:message key="add.to.cart"/></button>
                                        </div>
                                        <div class="product-info">
                                            <h3 class="product-brand">${product.name}</h3>
                                            <p class="product-card-description">${product.description}</p>
                                            <span class="price">$${product.price}</span>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
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
</body>
</html>