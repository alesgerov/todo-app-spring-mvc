<%--
  Created by IntelliJ IDEA.
  User: Tofig Alasgarov
  Date: 9/12/2021
  Time: 10:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Tasks</title>
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

</head>
<body>
    <a href="/task/add">Add task</a>
    <a href="/tasks">Tasks</a>
    <a href="/password/change">Change password</a>
    <a href="/logout">Logout</a>
    <table class="table table-striped" >
        <thead>
            <tr>
                <th scope="col">Task title</th>
                <th scope="col">Task Description</th>
                <th scope="col">Created date</th>
                <th scope="col">Deadline date</th>
                <th scope="col">You will be notified</th>
                <th scope="col">Is Done</th>
                <th scope="col">Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${tasks}" var="task">
                <tr>
                    <td>${task.title}</td>
                    <td>${task.description}</td>
                    <td>${task.createdTime.format(formatter)}</td>
                    <td>${task.validDate.format(formatter1)}</td>
                    <td>${task.needNotification}</td>
                    <td>${task.done}</td>
                    <td>
                        <c:choose>
                            <c:when test="${task.done}">
                                <a href="/undone/${task.id}">Undone</a> &nbsp;
                            </c:when>
                            <c:otherwise>
                                <a href="/done/${task.id}">Done</a> &nbsp;
                            </c:otherwise>
                        </c:choose>
                        <a href="/delete/${task.id}">Delete</a>&nbsp;
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
