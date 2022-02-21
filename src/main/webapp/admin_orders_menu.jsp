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
    <title><fmt:message key="orders"/></title>
</head>
<body>
<jsp:include page="/navAdminSection.jsp"/>
<jsp:include page="/menuSection.jsp"/>
<div class="outer">
    <table class="table table-striped text-center table-bordered" id="data2">
        <thead class="thead-light">
        <tr>
            <th><fmt:message key="order.id"/></th>
            <th><fmt:message key="order.created"/></th>
            <th><fmt:message key="order.userId"/></th>
            <th><fmt:message key="order.statusId"/></th>
            <th>Delivery Id</th>
            <th>Order Detail Id</th>
        </tr>
        </thead>
        <c:forEach var="order" items="${sessionScope.orders}">
            <tr>
                <td>${order.id}</td>
                <td>${order.dateCreated}</td>
                <td>${order.userId}</td>
                <td>${order.statusId}</td>
                <td>${order.deliveryId}</td>
                <td>${order.orderDetailId}</td>
                <td>
                    <form action="EditOrderPage">
                        <input type="hidden" name="id" value="${order.id}">
                        <input type="submit" class="btn btn-primary btn-sm btn-block"
                               value="<fmt:message key="button.edit"/>" style="width: 100px; margin: auto; text-align: center; display: block;">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</div>
<script>
    $(document).ready(function () {
        $('#data2').after('<div id="nav"></div>');
        var rowsShown = 6;
        var rowsTotal = $('#data2 tbody tr').length;
        var numPages = rowsTotal / rowsShown;
        for (i = 0; i < numPages; i++) {
            var pageNum = i + 1;
            $('#nav').append('<a href="#" rel="' + i + '">' + pageNum + '</a> ').css("font-size", "14px").css("margin-left","50px");
        }
        $('#data2 tbody tr').hide();
        $('#data2 tbody tr').slice(0, rowsShown).show();
        $('#nav a:first').addClass('active');
        $('#nav a').bind('click', function () {

            $('#nav a').removeClass('active');
            $(this).addClass('active');
            var currPage = $(this).attr('rel');
            var startItem = currPage * rowsShown;
            var endItem = startItem + rowsShown;
            $('#data2 tbody tr').css('opacity', '0.0').hide().slice(startItem, endItem).css('display', 'table-row').animate({opacity: 1}, 300);
        });
    });
</script>
</body>
</html>