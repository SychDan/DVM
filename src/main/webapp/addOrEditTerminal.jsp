<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: danny
  Date: 14.04.19
  Time: 1:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <title>Title</title>
</head>
<body>
<c:choose>
    <c:when test="${terminalForm['id'] eq 0}">
        <h1>Добавление терминала</h1>
    </c:when>
    <c:otherwise>
        <h1>Редактирование терминала</h1>
    </c:otherwise>
</c:choose>
<br />
<spring:url value="/home" var="actionUrl"/>
<form:form method="post" modelAttribute="terminalForm" action="${actionUrl}">
    ${terminalForm.id}
    <form:input path="id" type="hidden" id="ID"/>
    <spring:bind path="nameGroup">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <label class="col-sm-2 control-label">nameGroup</label>
            <div class="col-sm-10">
                <form:input path="nameGroup" type="text" class="form-control"
                            id="nameGroup" placeholder="nameGroup" />
                <form:errors path="nameGroup" class="control-label" />
            </div>
        </div>
    </spring:bind>
    <spring:bind path="termNumber">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <label class="col-sm-2 control-label">termNumber</label>
            <div class="col-sm-10">
                <form:input path="termNumber" type="text" class="form-control"
                            id="termNumber" placeholder="termNumber" />
                <form:errors path="termNumber" class="control-label" />
            </div>
        </div>
    </spring:bind>
    <spring:bind path="address">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <label class="col-sm-2 control-label">termNumber</label>
            <div class="col-sm-10">
                <form:input path="address" type="text" class="form-control"
                            id="address" placeholder="address" />
                <form:errors path="address" class="control-label" />
            </div>
        </div>
    </spring:bind>
    <spring:bind path="ip">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <label class="col-sm-2 control-label">termNumber</label>
            <div class="col-sm-10">
                <form:input path="ip" type="text" class="form-control"
                            id="ip" placeholder="ip" />
                <form:errors path="ip" class="control-label" />
            </div>
        </div>
    </spring:bind>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <c:choose>
                <c:when test="${terminalForm['id'] eq 0}">
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
</body>
</html>
