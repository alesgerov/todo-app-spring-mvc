<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Add task</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/css/util.css">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <style>
        .white {
            color: white;
        }

        .big {
            size: 50px;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
            var dtToday = new Date();

            var month = dtToday.getMonth() + 1;
            var day = dtToday.getDate();
            var year = dtToday.getFullYear();

            if (month < 10)
                month = '0' + month.toString();
            if (day < 10)
                day = '0' + day.toString();

            var maxDate = year + '-' + month + '-' + day;


            alert(maxDate);
            $('#txtDate').attr('min', maxDate);


        });
    </script>


    <!--===============================================================================================-->


</head>
<body>

<div class="limiter">
    <a href="/task/add">Add task</a>
    <a href="/tasks">Tasks</a>
    <a href="/logout">Logout</a>
    <div class="container-login100" style="background-image: url('/images/bg-01.jpg');">
        <div class="wrap-login100 p-t-30 p-b-50">
				<span class="login100-form-title p-b-41">
					Add task
				</span>
            <form:form cssClass="wrap-login100 p-t-30 p-b-50" modelAttribute="form">
                <div class="mb-3">
                    <label for="title" class="form-label white">Task title</label>
                    <form:input path="title" cssClass="form-control" id="title" placeholder="Task title"
                                required="true"/>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label white">Task description</label>
                    <form:textarea path="description" cssClass="form-control" id="description"
                                   placeholder="Task description" required="true"/>
                </div>
                <div class="mb-3">
                    <label for="needNotification" class="form-label white">Do you need notification?</label>
                    <form:checkbox path="needNotification" cssClass="form-check big" id="needNotification"/>
                </div>

                <label for="txtDate" class="form-label white">Deadline</label>
                <input type="date" id="txtDate" name="deadline">

                <div class="container-login100-form-btn m-t-32">
                    <input type="submit" class="login100-form-btn" title="Add task">
                </div>
            </form:form>
        </div>
    </div>
</div>


<div id="dropDownSelect1"></div>
</body>
</html>
