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
    <!-- JQuery -->
    <%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--%>
    <%--    <script src="js/uploadFile.js"></script>--%>
</head>
<body>
<c:choose>
    <c:when test="${otchetForm['id'] eq 0}">
        <h1>Добавление пользователя</h1>
    </c:when>
    <c:otherwise>
        <h1>Редактирование пользователя</h1>
    </c:otherwise>
</c:choose>
<br />
<spring:url value="/otchets" var="actionUrl" />
<form:form method="post" modelAttribute="otchetForm" action="${actionUrl}" enctype="multipart/form-data">
    ${otchetForm.id}
    <form:input path="id" type="hidden" id="ID"/>
    <spring:bind path="name">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <label class="col-sm-2 control-label">name</label>
            <div class="col-sm-10">
                <form:input path="name" type="text" class="form-control"
                            id="firstName" placeholder="name" />
                <form:errors path="name" class="control-label" />
            </div>
        </div>
    </spring:bind>

    <spring:bind path="photo.file">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <label class="col-sm-2 control-label">Photo</label>
            <div class="col-sm-10">
                <form:input type="file" path="photo.file" id="file" class="form-control input-sm"/>
                <form:errors path="photo.file" class="control-label" />
            </div>
        </div>
    </spring:bind>

    <spring:bind path="media.file">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <label class="col-sm-2 control-label">Media</label>
            <div class="col-sm-10">
                <form:input type="file" path="media.file" id="file" class="form-control input-sm"/>
                <form:errors path="media.file" class="control-label" />
            </div>
        </div>
    </spring:bind>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <c:choose>
                <c:when test="${otchetForm['id'] eq 0}">
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
