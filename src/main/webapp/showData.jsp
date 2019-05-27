<%--
  Created by IntelliJ IDEA.
  User: danny
  Date: 27.05.19
  Time: 0:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.webjars.org/tags" prefix="wj"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Показ данных</title>
    <link href='<spring:url value="/resources/css/sidebar.css"/>' rel="stylesheet" />
    <link rel='stylesheet' href='<wj:locate path="css/bootstrap.min.css" relativeTo="META-INF/resources"/>'>
    <script type='text/javascript' src='<wj:locate path="jquery.min.js" relativeTo="META-INF/resources"/>'></script>
    <script type='text/javascript' src='<wj:locate path="js/bootstrap.min.js" relativeTo="META-INF/resources"/>'></script>
    <script type="text/javascript" src='<spring:url value="/resources/js/modalWindow.js"/>'></script>
</head>
<body>
<div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-light border-right col-xl-2" id="sidebar-wrapper">
        <div class="sidebar-heading"><a class="nav-link" href="/home">DVM</a></div>
        <div class="list-group list-group-flush">
            <c:if test="${not empty msgTerminal}">
                <div class="alert alert-${css} alert-dismissible" role="alert">


                    <button mode="button" class="close" data-dismiss="alert"
                            aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <strong>${msgTerminal}</strong>
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
                    <li><a class="nav-link" href="/journals">Журнал</a></li>
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

            <H1>Сотрудник: ${person}</H1>
            <br>
            <spring:url value="/journals/${entry.id}/showData/createOtchet" var="actionUrl"/>
            <form action="${actionUrl}" method="post">
                <input type="hidden" value="${entry.id}">
                <input type="submit" value="Создать отчет">
            </form>
            <div class="row">
                <div class="col-xs-12 col-sm-4">
                    <H4>Видеоматериалы</H4>
                    <table class="table table-bordered" id="videoTable">
                        <thead>
                        <tr>
                            <tr style="display: none">id</tr>
                            <th>Дата</th>
                            <th>Название</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="video" items="${videos}">

                            <tr class="clickable-row" id="row">
                                <td style="display: none">${video.id}</td>
                                <td>${video.date}</td>
                                <td>${video.name}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-xs-12 col-sm-4">
                    <H4>Фото</H4>
                    <table class="table table-bordered" id="photoTable">
                        <thead>
                        <tr style="display: none">id</tr>
                        <th>Название</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="photo" items="${photos}">

                            <tr class="clickable-row" id="row">
                                <td style="display: none">${photo.id}</td>
                                <td>${photo.photo}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-xs-12 col-sm-4">
                    <H4>Логи</H4>
                    <table class="table table-bordered" id="logTable">
                        <thead>
                        <tr style="display: none">id</tr>
                        <th>Название</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="log" items="${logs}">

                            <tr class="clickable-row" id="row">
                                <td style="display: none">${log.id}</td>
                                <td>${log.name}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- Modal window -->

            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalLabel" aria-hidden="true" th:fragment="modalContents">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Видеоматериал</h5>
                            <button mode="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body" id="areaValue">
                            <video id="video_telechargeable" width="360" height="250" controls>
                                <source src="/journals/7/video" mode="video/mp4"></source>
                            </video>
                        </div>
                        <div class="modal-footer">
                            <button mode="button" class="btn btn-primary"
                                    data-dismiss="modal">Закрыть</button>
                        </div>
                    </div>
                </div>
            </div>


            <!-- end Modal -->

        </div>
    </div>
</div>
</body>
</html>
