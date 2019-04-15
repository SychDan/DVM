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
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:choose>
    <c:when test="${personForm['id'] eq 0}">
        <h1>Добавление пользователя</h1>
    </c:when>
    <c:otherwise>
        <h1>Редактирование пользователя</h1>
    </c:otherwise>
</c:choose>
<br />
<spring:url value="/persons" var="actionUrl"/>
<form:form method="post" modelAttribute="personForm" action="${actionUrl}">
    ${perosnForm.id}
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
            <label class="col-sm-2 control-label">termNumber</label>
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
</body>
</html>
