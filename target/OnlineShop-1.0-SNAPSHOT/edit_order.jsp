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
    <title><fmt:message key="edit.order"/></title>
</head>
<body>
<jsp:include page="/navAdminSection.jsp"/>
<jsp:include page="/menuSection.jsp"/>
<div class="addGroup">
    <form action="editOrder">
        <div class="form-group">
            <div class="row">
                <div class="col">
                    <label for="name">Enter Name</label>
                    <input name="name" type="text" id="name" class="form-control form-control-lg" required/>

                    <label for="description">Enter Description</label>
                    <textarea name="description" class="form-control" id="description" rows="3" required></textarea>

                    <label for="quantity">Enter Quantity</label>
                    <input name="quantity" type="number" id="quantity" class="form-control form-control-lg" min="0"
                           required/>

                    <label for="price">Enter Price</label>
                    <input name="price" type="number" id="price" class="form-control form-control-lg" min="0" required/>

                    <label for="discount">Enter Discount</label>
                    <input name="discount" type="number" id="discount" class="form-control form-control-lg" min="0"
                           required/>
                </div>
                <div class="col">
                    <label for="producer">Choose Producer</label>
                    <select class="form-control form-control-lg" id="producer" name="producer">
                        <c:forEach var="producer" items="${sessionScope.producers}">
                            <option>${producer.name} </option>
                        </c:forEach>
                    </select>

                    <div class="form-group">
                        <label for="category">Choose Category</label>
                        <select class="form-control form-control-lg" id="category" name="category">
                            <c:forEach var="category" items="${sessionScope.categories}">
                                <option>${category.name}</option>
                            </c:forEach>
                        </select>

                        <label for="size">Choose Size</label>
                        <select class="form-control form-control-lg" id="size" name="size">
                            <c:forEach var="size" items="${sessionScope.sizes}">
                                <option>${size.name}</option>
                            </c:forEach>
                        </select>

                        <label for="language">Choose Language</label>
                        <select class="form-control form-control-lg" id="language" name="language">
                            <option>English</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <button type="submit" class="btn btn-primary" style="margin-left: 730px; margin-top: -15px">Save</button>
    </form>
</div>
</body>
</html>