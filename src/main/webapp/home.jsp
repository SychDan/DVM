<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: danny
  Date: 14.04.19
  Time: 1:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
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
<button onclick="location.href='${addUrl}'">Добавить</button>
    <c:forEach var="terminal" items="${terminals}">

        <spring:url value="/home/${terminal.id}/delete" var="deleteUrl" />
        <spring:url value="/home/${terminal.id}/update" var="updateUrl" />

        <li>
            ${terminal.termNumber} ${terminal.address} ${terminal.ip}
            <button onclick="location.href='${updateUrl}'">Редактировать</button>
            <button onclick="location.href='${deleteUrl}'">Удалить</button>
        </li>
    </c:forEach>
</body>
</html>
