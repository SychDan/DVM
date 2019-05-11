<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: danny
  Date: 14.04.19
  Time: 23:19
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
            <c:choose>
                <c:when test="${personForm['id'] eq 0}">
                    <h1>Добавление пользователя</h1>
                </c:when>
                <c:otherwise>
                    <h1>Редактирование пользователя</h1>
                </c:otherwise>
            </c:choose>
            <br />
            <spring:url value="/persons" var="actionUrl" />
            <form:form method="post" modelAttribute="personForm" action="${actionUrl}" enctype="multipart/form-data">
                ${personForm.id}
                <form:input path="id" type="hidden" id="ID"/>
                <spring:bind path="family">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-2 control-label">family</label>
                        <div class="col-sm-10">
                            <form:input path="family" type="text" class="form-control"
                                        id="family" placeholder="family" />
                            <form:errors path="family" class="control-label" />
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="firstName">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-2 control-label">firstName</label>
                        <div class="col-sm-10">
                            <form:input path="firstName" type="text" class="form-control"
                                        id="firstName" placeholder="firstName" />
                            <form:errors path="firstName" class="control-label" />
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="secondName">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-2 control-label">secondName</label>
                        <div class="col-sm-10">
                            <form:input path="secondName" type="text" class="form-control"
                                        id="secondName" placeholder="secondName" />
                            <form:errors path="secondName" class="control-label" />
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="cardNumber">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-2 control-label">cardNumber</label>
                        <div class="col-sm-10">
                            <form:input path="cardNumber" type="text" class="form-control"
                                        id="cardNumber" placeholder="cardNumber" />
                            <form:errors path="cardNumber" class="control-label" />
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="access">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-2 control-label">access</label>
                        <div class="col-sm-10">
                            <form:input path="access" type="text" class="form-control"
                                        id="access" placeholder="access" />
                            <form:errors path="access" class="control-label" />
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="admin">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-2 control-label">admin</label>
                        <div class="col-sm-10">
                            <form:input path="admin" type="text" class="form-control"
                                        id="admin" placeholder="admin" />
                            <form:errors path="admin" class="control-label" />
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="status">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-2 control-label">status</label>
                        <div class="col-sm-10">
                            <form:input path="status" type="text" class="form-control"
                                        id="status" placeholder="status" />
                            <form:errors path="status" class="control-label" />
                        </div>
                    </div>
                </spring:bind>

                <spring:bind path="pass">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-2 control-label">pass</label>
                        <div class="col-sm-10">
                            <form:input path="pass" type="text" class="form-control"
                                        id="status" placeholder="pass" />
                            <form:errors path="pass" class="control-label" />
                        </div>
                    </div>
                </spring:bind>

                <spring:bind path="photo.file">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-2 control-label">status</label>
                        <div class="col-sm-10">
                            <form:input type="file" path="photo.file" id="file" class="form-control input-sm"/>
                            <form:errors path="photo.file" class="control-label" />
                        </div>
                    </div>
                </spring:bind>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <c:choose>
                            <c:when test="${personForm['id'] eq 0}">
                                <button type="submit" class="btn-lg btn-primary pull-right">Добавить
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" class="btn-lg btn-primary pull-right">Обновить
                                </button>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
            </form:form>
            <a href="/persons"><button class="btn-lg btn-primary pull-right">Отмена</button></a>
        </div>
    </div>
</div>


</body>
</html>
