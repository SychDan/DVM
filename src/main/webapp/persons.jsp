<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: danny
  Date: 14.04.19
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.webjars.org/tags" prefix="wj"%>
<html>
<head>
    <title>Title</title>
    <link rel='stylesheet' href='<wj:locate path="css/bootstrap.min.css" relativeTo="META-INF/resources"/>'>
    <link href='<spring:url value="/resources/css/sidebar.css"/>' rel="stylesheet" />
    <script type="text/javascript"
            src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
    <script type='text/javascript' src='<wj:locate path="jquery.min.js" relativeTo="META-INF/resources"/>'></script>
    <script type="text/javascript" src='<spring:url value="/resources/js/table.js"/>'></script>

<%--    <script type='text/javascript' src='<wj:locate path="js/bootstrap.min.js" relativeTo="META-INF/resources"/>'></script>--%>
<%--    <script type='text/javascript' src='<wj:locate path="js/bootstrap.bundle.min.js" relativeTo="META-INF/resources"/>'></script>--%>
</head>
<body>

<div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
        <div class="sidebar-heading">Start Bootstrap </div>
        <div class="list-group list-group-flush">
            <a href="#" class="list-group-item list-group-item-action bg-light">Dashboard</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Shortcuts</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Overview</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Events</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Profile</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Status</a>
        </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">

        <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li><a class="nav-link" href="#">Журнал</a></li>
                            <li><a class="nav-link" href="#">Справочники</a></li>
                            <li><a class="nav-link" href="#">Отчеты</a></li>
                            <li><a class="nav-link" href="#">Настройки</a></li>
                        </ul>
                        <ul class="nav navbar-nav ml-auto mt-2 mt-lg-0">
                            <li><a class="nav-link" href="#">Выход</a></li>
                        </ul>
                    </div><!-- /.navbar-collapse -->

<%--            <div class="collapse navbar-collapse" id="navbarSupportedContent">--%>
<%--                            <ul class="nav navbar-nav">--%>
<%--                                --%>
<%--                            </ul>--%>
<%--                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">--%>
<%--                    <li class="nav-item active">--%>
<%--                        <a class="nav-link" href="#">Выход <span class="sr-only">(current)</span></a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </div>--%>
        </nav>

        <div class="container-fluid">
            <c:if test="${not empty msg}">
                <div class="alert alert-${css} alert-dismissible" role="alert">


                    <button type="button" class="close" data-dismiss="alert"
                            aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <strong>${msg}</strong>
                </div>
            </c:if>
            <spring:url value="/persons/add" var="addUrl"/>

            <button onclick="location.href='${addUrl}'" class="btn btn-primary" >Добавить</button>
            <button id="update" class="btn btn-primary" disabled autocomplete = "off">Обновить</button>
            <button id="delete" class="btn btn-danger" disabled autocomplete = "off">Удалить</button>
            <button id="download" class="btn btn-primary" disabled autocomplete = "off">Скачать</button>
            <table class="table table-bordered" id="myTable">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>family</th>
                    <th>firstName</th>
                    <th>secondName</th>
                    <th>cardNumber</th>
                    <th>access</th>
                    <th>admin</th>
                    <th>status</th>
                    <th>otdel</th>
                    <th>doljn</th>
                </tr>
                </thead>
                <tbody>
            <c:forEach var="person" items="${persons}">

                <tr class="clickable-row" id="row">
                    <td>${person.id}</td>
                    <td>${person.family}</td>
                    <td>${person.firstName}</td>
                    <td>${person.secondName}</td>
                    <td>${person.cardNumber}</td>
                    <td>${person.access}</td>
                    <td>${person.admin}</td>
                    <td>${person.status}</td>
                    <td>${person.otdel}</td>
                    <td>${person.doljn}</td>
                </tr>

            </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->
<%--<nav class="navbar navbar-default">--%>
<%--    <div class="container-fluid">--%>
<%--        <!-- Brand and toggle get grouped for better mobile display -->--%>
<%--        <div class="navbar-header">--%>
<%--            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">--%>
<%--                <span class="sr-only">Toggle navigation</span>--%>
<%--                <span class="icon-bar"></span>--%>
<%--                <span class="icon-bar"></span>--%>
<%--                <span class="icon-bar"></span>--%>
<%--            </button>--%>
<%--            <a class="navbar-brand" href="/home">DVM</a>--%>
<%--        </div>--%>

<%--        <!-- Collect the nav links, forms, and other content for toggling -->--%>
<%--        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">--%>
<%--            <ul class="nav navbar-nav">--%>
<%--                <li><a href="#">Журнал</a></li>--%>
<%--                <li><a href="#">Справочники</a></li>--%>
<%--                <li><a href="#">Отчеты</a></li>--%>
<%--                <li><a href="#">Настройки</a></li>--%>
<%--            </ul>--%>
<%--            <ul class="nav navbar-nav navbar-right">--%>
<%--                <li><a href="#">Выход</a></li>--%>
<%--            </ul>--%>
<%--        </div><!-- /.navbar-collapse -->--%>
<%--    </div><!-- /.container-fluid -->--%>
<%--</nav>--%>
<%--<c:if test="${not empty msg}">--%>
<%--    <div class="alert alert-${css} alert-dismissible" role="alert">--%>
<%--        <button type="button" class="close" data-dismiss="alert"--%>
<%--                aria-label="Close">--%>
<%--            <span aria-hidden="true">×</span>--%>
<%--        </button>--%>
<%--        <strong>${msg}</strong>--%>
<%--    </div>--%>
<%--</c:if>--%>
<%--<spring:url value="/persons/add" var="addUrl"/>--%>

<%--<button onclick="location.href='${addUrl}'">Добавить</button>--%>
<%--<table class="table">--%>
<%--    <thead>--%>
<%--    <tr>--%>
<%--        <th>ID</th>--%>
<%--        <th>family</th>--%>
<%--        <th>firstName</th>--%>
<%--        <th>secondName</th>--%>
<%--        <th>cardNumber</th>--%>
<%--        <th>access</th>--%>
<%--        <th>admin</th>--%>
<%--        <th>status</th>--%>
<%--        <th>otdel</th>--%>
<%--        <th>doljn</th>--%>
<%--        <th>Action</th>--%>
<%--    </tr>--%>
<%--    </thead>--%>

<%--<c:forEach var="person" items="${persons}">--%>
<%--    <tr>--%>
<%--        <td>${person.id}</td>--%>
<%--        <td>${person.family}</td>--%>
<%--        <td>${person.firstName}</td>--%>
<%--        <td>${person.secondName}</td>--%>
<%--        <td>${person.cardNumber}</td>--%>
<%--        <td>${person.access}</td>--%>
<%--        <td>${person.admin}</td>--%>
<%--        <td>${person.status}</td>--%>
<%--        <td>${person.otdel}</td>--%>
<%--        <td>${person.doljn}</td>--%>
<%--        <td>--%>
<%--            <spring:url value="/persons/${person.id}/delete" var="deleteUrl" />--%>
<%--            <spring:url value="/persons/${person.id}/update" var="updateUrl" />--%>
<%--            <spring:url value="/persons/${person.id}/download" var="downloadUrl" />--%>

<%--            <button class="btn btn-primary"--%>
<%--                    onclick="location.href='${updateUrl}'">Update</button>--%>
<%--            <button class="btn btn-danger"--%>
<%--                    onclick="location.href='${deleteUrl}'">Delete</button>--%>
<%--            <button class="btn btn-danger"--%>
<%--                    onclick="location.href='${downloadUrl}'">Download</button>--%>
<%--        </td>--%>
<%--    </tr>--%>
<%--</c:forEach>--%>
<%--</table>--%>
</body>
</html>
