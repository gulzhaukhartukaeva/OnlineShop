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
    <title><fmt:message key="edit.product"/></title>
</head>
<body>
<jsp:include page="/navAdminSection.jsp"/>
<jsp:include page="/menuSection.jsp"/>
<div class="outer" style="height: 440px;">
    <form action="editProduct" class="form-inline" style="margin-left: 50px; margin-right: 50px; margin-top: 50px; justify-content: space-between">
        <div class="form-group mb-2">
            <input type="hidden" name="languageId" value="${Const.russianId}">
            <label class="form-control" for="nameRu" style="border: none; color: grey; font-weight: 100;"><fmt:message key="input.enter.name.ru"/></label>
            <c:forEach var="product" items="${sessionScope.productsLocalized}">
                <c:if test="${product.languageId eq Const.russianId}">
                    <input type="hidden" name="productId" value="${product.id}">
                    <input type="text" class="form-control" name="name" id="nameRu" value="${product.name}" style="width: 185px; border: 1px solid bisque;" required>
                </c:if>
            </c:forEach>

            <input type="hidden" name="languageId" value="${Const.englishId}">
            <label class="form-control" for="nameEn" style="border: none; color: grey; font-weight: 100; margin-left: 50px;">
                <fmt:message key="input.enter.name.en"/></label>
            <c:forEach var="product" items="${sessionScope.productsLocalized}">
                <c:if test="${product.languageId eq Const.englishId}">
                    <input type="hidden" name="productId" value="${product.id}">
                    <input type="text" class="form-control" name="name" id="nameEn" value="${product.name}" style="margin-left: 15px; width: 185px; border: 1px solid bisque;" required>
                </c:if>
            </c:forEach>
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <input type="hidden" name="languageId" value="${Const.russianId}">
            <label class="form-control" for="descEn" style="border: none; color: grey; font-weight: 100;">
                <fmt:message key="input.enter.description.ru"/></label>
            <c:forEach var="product" items="${sessionScope.productsLocalized}">
                <c:if test="${product.languageId eq Const.russianId}">
                    <input type="hidden" name="productId" value="${product.id}">
                    <textarea class="form-control" name="description" id="descEn" style="margin-top: 20px; border: 1px solid bisque; width: 210px"
                              rows="3" required>${product.description}</textarea>
                </c:if>
            </c:forEach>

            <input type="hidden" name="languageId" value="${Const.englishId}">
            <label class="form-control" for="descRu" style="border: none; color: grey; font-weight: 100; margin-left: 50px;">
                <fmt:message key="input.enter.description.en"/></label>
            <c:forEach var="product" items="${sessionScope.productsLocalized}">
                <c:if test="${product.languageId eq Const.englishId}">
                    <input type="hidden" name="productId" value="${product.id}">
                    <textarea class="form-control" name="description" id="descRu" style="margin-top: 20px; margin-left: 20px; border: 1px solid bisque; width: 210px;"
                              rows="3" required>${product.description}</textarea>
                </c:if>
            </c:forEach>
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <label class="form-control" for="producer" style="border: none; color: grey; font-weight: 100; margin-top: 20px">
                <fmt:message key="input.enter.producer"/></label>
            <select class="form-control form-control-lg" id="producer" name="producer" style="margin-top: 20px; width: 185px; border: 1px solid bisque; color: lightslategrey;">
                <c:forEach var="producer" items="${sessionScope.producers}">
                    <c:if test="${producer.id eq product.producer.id}">
                        <option selected value="${producer.id}">${producer.name}</option>
                    </c:if>
                    <c:if test="${producer.id ne product.producer.id}">
                        <option value="${producer.id}">${producer.name}</option>
                    </c:if>
                </c:forEach>
            </select>

            <label class="form-control" for="category" style="border: none; color: grey; font-weight: 100; margin-top: 20px; margin-left: 50px;">
                <fmt:message key="input.enter.category"/></label>
            <select class="form-control form-control-lg" id="category" name="category"
                    style="margin-left: 18px; width: 250px; border: 1px solid bisque; color: lightslategrey; margin-top: 20px;">
                <c:forEach var="category" items="${sessionScope.categories}">
                    <c:if test="${category.id eq product.category.id}">
                        <option selected value="${category.id}">${category.name}</option>
                    </c:if>
                    <c:if test="${category.id ne product.category.id}">
                        <option value="${category.id}">${category.name}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <label class="form-control" for="size" style="border: none; color: grey; font-weight: 100; margin-top: 20px;">
                <fmt:message key="input.enter.size"/></label>
            <select class="form-control form-control-lg" id="size" name="size"
                    style="width: 245px; border: 1px solid bisque; color: lightslategrey; margin-top: 20px;">
                <c:forEach var="size" items="${sessionScope.sizes}">
                    <c:if test="${size.id eq product.size.id}">
                        <option selected value="${size.id}">${size.name}</option>
                    </c:if>
                    <c:if test="${size.id ne product.size.id}">
                        <option value="${size.id}">${size.name}</option>
                    </c:if>
                </c:forEach>
            </select>

            <label class="form-control" for="quantity" style="margin-top: 20px; border: none; color: grey; font-weight: 100; margin-left: 50px;">
                <fmt:message key="input.enter.quantity"/></label>
            <input name="quantity" type="number" id="quantity" class="form-control" min="0" required value="${sessionScope.product.quantity}"
                   style="margin-top: 20px; width: 100px; border: 1px solid bisque; margin-left: 10px; width: 255px;"/>
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <label class="form-control" for="price" style="margin-top: 20px; border: none; color: grey; font-weight: 100;">
                <fmt:message key="input.enter.price"/></label>
            <input name="price" type="number" id="price" class="form-control" min="0" required value="${sessionScope.product.price}"
                   style="margin-top: 20px; width: 100px; border: 1px solid bisque; margin-left: 18px; width: 245px;"/>

            <label class="form-control" for="discount" style="margin-top: 20px; border: none; color: grey; font-weight: 100; margin-left: 50px;">
                <fmt:message key="input.enter.discount"/></label>
            <input name="discount" type="number" id="discount" class="form-control" min="0" required value="${sessionScope.product.discount}"
                   style="margin-top: 20px; width: 100px; border: 1px solid bisque; margin-left: 44px; width: 250px;"/>
        </div>
        <button type="submit" class="btn btn-primary" style="margin-left: 780px; margin-top: 15px;"><fmt:message key="button.edit"/></button>
    </form>
</div>
</body>
</html>