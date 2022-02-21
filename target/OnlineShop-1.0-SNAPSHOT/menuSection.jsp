<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<jsp:useBean id="Const" class="com.epam.onlineShopService.constants.GeneralConstants"/>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<div class="btn-group btn-group-lg" style="margin-left: 400px;">
    <button type="submit" class="btn btn-primary" form="categories"><fmt:message key="button.all.categories"/></button>
    <button type="submit" class="btn btn-primary" form="users"><fmt:message key="button.all.users"/></button>
    <button type="submit" class="btn btn-primary" form="products"><fmt:message key="button.all.products"/></button>
    <button type="submit" class="btn btn-primary" form="producers"><fmt:message key="button.all.producers"/></button>
</div>
<form action="showCategories" method="get" id="categories"></form>
<form action="showUsers" method="get" id="users"></form>
<form action="showProducts" method="get" id="products"></form>
<form action="showOrders" method="get" id="orders"></form>
<form action="showProducers" method="get" id="producers"></form>

