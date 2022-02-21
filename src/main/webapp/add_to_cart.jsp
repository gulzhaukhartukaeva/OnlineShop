<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<jsp:useBean id="Const" class="com.epam.onlineShopService.constants.GeneralConstants"/>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>

<button class="card-btn"><b><fmt:message key="add.to.cart"/></b></button>

<form action="addToCart" id="add" method="post">
    <input type="hidden" name="title" value="titleeee">
    <button type="submit" form="add" class="card-btn"><fmt:message key="add.to.cart"/></button>
</form>