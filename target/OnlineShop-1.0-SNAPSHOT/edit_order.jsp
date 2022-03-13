<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<jsp:useBean id="Const" class="com.epam.online.shop.service.constants.GeneralConstants"/>
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
<div class="outer">
    <form action="editOrder">
        <div class="form-group">
            <label for="status" style="margin-top: 20px; margin-bottom: 10px; margin-left: 250px;">Select Delivery Status</label>
            <select class="form-control" id="status" name="status" style="margin-left: 250px; width: 150px; height: 30px;">
                <c:forEach var="status" items="${sessionScope.statuses}">
                    <c:if test="${status.id eq orderEdit.status.id}">
                        <option selected value=${status.id}>${status.name}</option>
                    </c:if>
                    <c:if test="${status.id ne orderEdit.status.id}">
                        <option value=${status.id}>${status.name}</option>
                    </c:if>
                </c:forEach>
            </select>
            <input type="hidden" name="orderId" value="${sessionScope.orderEdit.id}">

        </div>

        <button type="submit" class="btn btn-primary" style="margin-left: 500px; margin-top: -50px;"><fmt:message key="button.save"/></button>
    </form>
</div>
</body>
</html>