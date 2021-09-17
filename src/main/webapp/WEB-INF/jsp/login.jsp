<%--
  Created by IntelliJ IDEA.
  User: Tofig Alasgarov
  Date: 9/5/2021
  Time: 1:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <!--===============================================================================================-->


    <style>
        .error{
            margin-left: 100px;
            color: red;
        }
    </style>
</head>
<body>

<div class="limiter">
    <div class="container-login100" style="background-image: url('images/bg-01.jpg');">
        <div class="wrap-login100 p-t-30 p-b-50">
				<span class="login100-form-title p-b-41">
					Account Login
				</span>
            <form:form cssClass="login100-form validate-form p-b-33 p-t-5" modelAttribute="form">
                <div class="wrap-input100 validate-input" data-validate = "Enter username">
                    <form:input path="username" cssClass="input100"  placeholder="Username"/>
                    <span class="focus-input100" data-placeholder="&#xe82a;"></span>
                    <form:errors path="username" cssClass="ui-state-error error"/>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Enter password">
                    <form:password path="password" cssClass="input100"  placeholder="Password" />
                    <span class="focus-input100" data-placeholder="&#xe80f;"></span>
                    <form:errors path="password" cssClass="ui-state-error error" />
                </div>
                <c:if test='${requestScope.containsKey("error")}'>
                    <p class="ui-state-error error">${error}</p>
                </c:if>
                <div class="container-login100-form-btn m-t-32">
                    <input type="submit" class="login100-form-btn" title="Login">
                </div>
            </form:form>
            <a href="/registration" class="error">Registration</a>
        </div>
    </div>
</div>


<div id="dropDownSelect1"></div>
</body>
</html>
