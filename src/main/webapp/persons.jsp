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
<spring:url value="/persons/add" var="addUrl"/>
<button onclick="location.href='${addUrl}'">Добавить</button>
<table>
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
        <th>Action</th>
    </tr>
    </thead>

<c:forEach var="person" items="${persons}">
    <tr>
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

        <td>
            <spring:url value="/persons/${person.id}/delete" var="deleteUrl" />
            <spring:url value="/persons/${person.id}/update" var="updateUrl" />

            <button class="btn btn-primary"
                    onclick="location.href='${updateUrl}'">Update</button>
            <button class="btn btn-danger"
                    onclick="location.href='${deleteUrl}'">Delete</button>
        </td>
    </tr>
</c:forEach>
</table>
</body>
</html>
