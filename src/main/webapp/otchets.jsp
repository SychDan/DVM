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
<html>
<head>
    <title>Title</title>
    <link rel='stylesheet' href='<wj:locate path="css/bootstrap.min.css" relativeTo="META-INF/resources"/>'>
    <link href='<spring:url value="/resources/css/sidebar.css"/>' rel="stylesheet" />
    <script type='text/javascript' src='<wj:locate path="jquery.min.js" relativeTo="META-INF/resources"/>'></script>
    <script type="text/javascript" src='<spring:url value="/resources/js/table.js"/>'></script>
</head>
<body>
<button onclick="location.href='/home'">Домашняя страница</button>
<c:if test="${not empty msg}">
    <div class="alert alert-${css} alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert"
                aria-label="Close">
            <span aria-hidden="true">×</span>
        </button>
        <strong>${msg}</strong>
    </div>
</c:if>
<spring:url value="/otchets/add" var="addUrl"/>
<button onclick="location.href='${addUrl}'">Добавить</button>
<table class="table-bordered table">
    <thead>
    <tr>
        <th>ID</th>
        <th>name</th>
        <th>Person</th>
        <th>date</th>
        <th>Action</th>
    </tr>
    </thead>

    <c:forEach var="otchet" items="${otchets}">
        <tr>
            <td>${otchet.id}</td>
            <td>${otchet.name}</td>
            <td>${otchet.person}</td>
            <td>${otchet.date}</td>
            <td>
                <spring:url value="/otchets/${otchet.id}/delete" var="deleteUrl" />
                <spring:url value="/otchets/${otchet.id}/update" var="updateUrl" />
                <spring:url value="/otchets/${otchet.id}/downloadPhoto" var="downloadPhotoUrl" />
                <spring:url value="/otchets/${otchet.id}/downloadMedia" var="downloadMediaUrl" />

                <button class="btn btn-primary"
                        onclick="location.href='${updateUrl}'">Update</button>
                <button class="btn btn-danger"
                        onclick="location.href='${deleteUrl}'">Delete</button>
                <button class="btn btn-danger" onclick="location.href='${downloadPhotoUrl}'">Download photo</button>
                <button class="btn btn-danger" onclick="location.href='${downloadMediaUrl}'">Download media</button>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
