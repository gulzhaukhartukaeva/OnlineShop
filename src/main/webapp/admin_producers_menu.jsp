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
    <title><fmt:message key="producers"/></title>
</head>
<body>
<jsp:include page="/navAdminSection.jsp"/>
<jsp:include page="/menuSection.jsp"/>
<div class="outer">
    <form action="addProducerPage">
        <button type="submit" class="btnAdd"><fmt:message key="add.producer"/></button>
    </form>
    <table class="table table-striped table-bordered" id="data2">
        <thead class="thead-light">
        <tr>
            <th><fmt:message key="producer.id"/></th>
            <th><fmt:message key="producer.name"/></th>
            <th><fmt:message key="language"/></th>
            <th><fmt:message key="action"/></th>
        </tr>
        </thead>
        <c:forEach var="producer" items="${sessionScope.producers}">
            <tr>
                <td>${producer.id}</td>
                <td>${producer.name}</td>
                <c:if test="${producer.languageId eq Const.russianId}">
                    <td>Русский</td>
                </c:if>
                <c:if test="${producer.languageId eq Const.englishId}">
                    <td>English</td>
                </c:if>
                <td>
                    <form action="EditProducerPage">
                        <input type="hidden" name="producerId" value="${producer.id}">
                        <input type="submit" class="btn btn-primary btn-sm btn-block"
                               value="<fmt:message key="button.edit"/>" style="width: 100px; margin: auto; text-align: center; display: block;">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
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