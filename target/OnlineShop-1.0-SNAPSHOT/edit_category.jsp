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
    <title><fmt:message key="edit.category"/></title>
</head>
<body>
<jsp:include page="/navAdminSection.jsp"/>
<jsp:include page="/menuSection.jsp"/>
<div class="outer">
    <form action="editCategory">
        <div class="form-group">
            <input type="hidden" name="languageId" value="${Const.russianId}">
            <c:forEach var="category" items="${sessionScope.categoriesLocalized}">
                <c:if test="${category.languageId eq Const.russianId}">
                    <input type="hidden" name="categoryId" value="${category.id}">
                    <input type="text" name="name" value="${category.name}" style="width: 280px; margin-left: 350px; margin-top: 20px; text-align: center;" required>
                </c:if>
            </c:forEach>

            <input type="hidden" name="languageId" value="${Const.englishId}">
            <c:forEach var="category" items="${sessionScope.categoriesLocalized}">
                <c:if test="${category.languageId eq Const.englishId}">
                    <input type="hidden" name="categoryId" value="${category.id}">
                    <input type="text" name="name" value="${category.name}" style="width: 280px; margin-left: 350px; margin-top: 20px; text-align: center;" required>
                </c:if>
            </c:forEach>

            <input type="hidden" name="categoryId" value="${sessionScope.categoryEdit.id}">
        </div>
        <button type="submit" class="btn btn-primary" style="margin-left: 400px"><fmt:message key="button.save"/></button>
    </form>
</div>
</body>
</html>