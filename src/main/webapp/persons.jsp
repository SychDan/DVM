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
    <script type='text/javascript' src='<wj:locate path="jquery.min.js" relativeTo="META-INF/resources"/>'></script>
    <script type="text/javascript" src='<spring:url value="/resources/js/table.js"/>'></script>

<%--    <script type='text/javascript' src='<wj:locate path="js/bootstrap.min.js" relativeTo="META-INF/resources"/>'></script>--%>
<%--    <script type='text/javascript' src='<wj:locate path="js/bootstrap.bundle.min.js" relativeTo="META-INF/resources"/>'></script>--%>
</head>
<body>

<div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-light border-right col-xl-2" id="sidebar-wrapper">
        <div class="sidebar-heading"><a class="nav-link" href="/home">DVM</a></div>
        <div class="list-group list-group-flush">
            <c:if test="${not empty msg}">
                <div class="alert alert-${css} alert-dismissible" role="alert">


                    <button type="button" class="close" data-dismiss="alert"
                            aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <strong>${msg}</strong>
                </div>
            </c:if>
            <spring:url value="/home/add" var="addUrl"/>

            <button onclick="location.href='${addUrl}'" class="btn btn-primary" >Добавить</button>
            <button id="updateSidebar" class="btn btn-primary" disabled autocomplete = "off">Обновить</button>
            <button id="deleteSidebar" class="btn btn-danger" disabled autocomplete = "off">Удалить</button>
            <button id="downloadSidebar" class="btn btn-primary" disabled autocomplete = "off">Скачать</button>
            <table class="table table-bordered" id="myTableSidebar">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Address</th>
                    <th>IP</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="terminal" items="${terminals}">

                    <tr class="clickable-row" id="row">
                        <td>${terminal.id}</td>
                        <td>${terminal.address}</td>
                        <td>${terminal.ip}</td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
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
                            <li><a class="nav-link" href="/persons">Справочники</a></li>
                            <li><a class="nav-link" href="/otchets">Отчеты</a></li>
                            <li><a class="nav-link" href="#">Настройки</a></li>
                        </ul>
                        <ul class="nav navbar-nav ml-auto mt-2 mt-lg-0">
                            <li><a class="nav-link" href="/logout">Выход</a></li>
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
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a class="nav-link active" href="/persons">Справчник сотрудников</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/persons/devices/">Справочник устройств</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/persons/marshruts/">Справочник маршрутов</a>
                </li>
            </ul>
            <br>
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
</body>
</html>
