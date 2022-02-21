<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<jsp:useBean id="Const" class="com.epam.onlineShopService.constants.GeneralConstants"/>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>

<html>
<head>
    <title>Sing Up</title>
    <style><%@include file="/css/login.css"%></style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<section class="vh-100" style="background-color: #9A616D;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-xl-10">
                <div class="card" style="border-radius: 1rem;">
                    <div class="row g-0">
                        <div class="col-md-6 col-lg-5 d-none d-md-block">
                            <img src="/img/img_31.png" alt=""
                                 class="img-fluid" style="border-radius: 1rem 0 0 1rem;"/>
                        </div>
                        <div class="col-md-6 col-lg-7 d-flex align-items-center">
                            <div class="card-body p-4 p-lg-5 text-black">
                                <form action="signupAction" method="post">
                                    <div class="form-outline mb-4">
                                        <input class="form-control form-control-lg" type="text" placeholder="<fmt:message key="input.surname"/>"
                                               name="surname" id="surname" required>
                                    </div>
                                    <div class="form-outline mb-4">
                                        <input class="form-control form-control-lg" type="text" placeholder="<fmt:message key="input.name"/>"
                                               name="name" id="name" required>
                                    </div>
                                    <div class="form-outline mb-4">
                                        <input class="form-control form-control-lg" type="email" placeholder="<fmt:message key="input.email"/>"
                                               name="email" id="email" required>
                                        <c:if test="${requestScope.notCorrectEmail != null}">
                                            <small class="text-danger"><fmt:message key="error.email"/></small>
                                        </c:if>
                                        <c:if test="${requestScope.loginIsExist != null}">
                                            <small class="text-danger"><fmt:message key="error.email.exists"/></small>
                                        </c:if>
                                    </div>
                                    <div class="form-outline mb-4">
                                        <input class="form-control form-control-lg" type="date" placeholder="<fmt:message key="input.birthDate"/>"
                                               name="birthDate" id="birthDate" required>
                                    </div>
                                    <div class="form-outline mb-4">
                                        <input class="form-control form-control-lg" type="number" placeholder="<fmt:message key="input.phoneNumber"/>"
                                               name="phoneNumber" id="phoneNumber" required>
                                        <c:if test="${requestScope.notCorrectPhone != null}">
                                            <small class="text-danger"><fmt:message key="error.phone"/></small>
                                        </c:if>
                                    </div>
                                    <div class="form-outline mb-4">
                                        <input class="form-control form-control-lg" type="password" placeholder="<fmt:message key="input.password"/>"
                                               name="password" id="password" required>
                                    </div>
                                    <div class="pt-1 mb-4">
                                        <button class="btn btn-dark btn-lg btn-block" type="submit"><fmt:message key="sign.up"/></button>
                                        <button class="btn btn-dark btn-lg btn-block" type="button"><fmt:message key="button.cancel"/></button>
                                    </div>
                                    <p class="mb-5 pb-lg-2" style="color: #393f81;"><fmt:message key="already.have.account"/>
                                        <a href="/login" style="color: #393f81;"><fmt:message key="sign.in"/></a></p>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>

