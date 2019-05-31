<%--
  Created by IntelliJ IDEA.
  User: danny
  Date: 30.05.19
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.webjars.org/tags" prefix="wj"%>
<html>
<head>
    <title>Настройки</title>
    <link rel='stylesheet' href='<wj:locate path="css/bootstrap.min.css" relativeTo="META-INF/resources"/>'>
    <link href='<spring:url value="/resources/css/sidebar.css"/>' rel="stylesheet" />
    <script type='text/javascript' src='<wj:locate path="jquery.min.js" relativeTo="META-INF/resources"/>'></script>
    <script type="text/javascript" src='<spring:url value="/resources/js/table.js"/>'></script>
</head>
<body>
<div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-light border-right col-xl-2" id="sidebar-wrapper">
        <div class="sidebar-heading"><a class="nav-link" href="/home">DVM</a></div>

    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">

        <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a class="nav-link" href="/journals">Журнал</a></li>
                    <li><a class="nav-link" href="/persons">Справочники</a></li>
                    <li><a class="nav-link" href="/otchets">Отчеты</a></li>
                    <li><a class="nav-link" href="#">Настройки</a></li>
                </ul>
                <ul class="nav navbar-nav ml-auto mt-2 mt-lg-0">
                    <li><a class="nav-link" href="/logout">Выход</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </nav>

        <div class="container-fluid">
            <form class="form-inline">
                <div class="form-group">
                    <label for="exampleInputEmail1">Название терминала:</label><br>

                    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="001">
                </div>

                <button type="submit" class="btn btn-primary">Сохранить</button>
            </form>
            <form class="form-inline">
                <div class="form-group">
                    <label for="exampleInputEmail1">Срок хранения медиаданных:</label><br>

                    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="001">
                </div>

                <button type="submit" class="btn btn-primary">Сохранить</button>
            </form>
            <form class="form-inline">
                <div class="form-group">
                    <label for="exampleInputEmail1">Новая опция:</label><br>

                    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="001">
                </div>

                <button type="submit" class="btn btn-primary">Сохранить</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
