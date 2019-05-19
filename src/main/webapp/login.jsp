<%--
  Created by IntelliJ IDEA.
  User: danny
  Date: 19.05.19
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.webjars.org/tags" prefix="wj"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "spring" uri= "http://www.springframework.org/tags"%>
<%@ taglib prefix= "security" uri= "http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Login Page</title>
    <link rel='stylesheet' href='<wj:locate path="css/bootstrap.min.css" relativeTo="META-INF/resources"/>'>
    <link href='<spring:url value="/resources/css/signin.css"/>' rel="stylesheet" />
    <script type='text/javascript' src='<wj:locate path="jquery.min.js" relativeTo="META-INF/resources"/>'></script>
    <script type="text/javascript" src='<spring:url value="/resources/js/login.js"/>'></script>

</head>
<body>
<form name= "form" action= "/auth" method= "post" class= "form-signin">

    <br/>
    <c:if test= "${not empty param.error}">
    <font size= "2" color= "red"><b>Неправильный код</b></font>
    </c:if>

    <h2 class= "form-signin-heading">Пожалуйста войдите</h2>

    <label for= "input" class= "sr-only"><spring:message code= "" text= "Email"/></label>
    <input id= "input" class= "form-control" name= "login" maxlength="5" onkeypress='validate(event)'/>

    <input type= "hidden" id= "inputPassword" class= "form-control" value="" name= "password"/>
    <button type="button" id="0"  class="button_number btn btn-primary btn-lg">0</button>
    <button type="button" id="1" class="button_number btn btn-primary btn-lg">1</button>
    <button type="button" id="2" class="button_number btn btn-primary btn-lg">2</button>
    <button type="button" id="3" class="button_number btn btn-primary btn-lg">3</button>
    <button type="button" id="4" class="button_number btn btn-primary btn-lg">4</button>
    <button type="button" id="5" class="button_number btn btn-primary btn-lg">5</button>
    <button type="button" id="6" class="button_number btn btn-primary btn-lg">6</button>
    <button type="button" id="7" class="button_number btn btn-primary btn-lg">7</button>
    <button type="button" id="8" class="button_number btn btn-primary btn-lg">8</button>
    <button type="button" id="9" class="button_number btn btn-primary btn-lg">9</button>
        <br>
        <br>
        <button type="button"  class="btn btn-primary" id="removeLastElem">Очистить</button>
<%--    <div class= "checkbox">--%>
<%--        <label>--%>
<%--            <input type= "checkbox" id= "rememberme"  name= "_spring_security_remember_me"/>Запомнить меня--%>
<%--        </label>--%>
<%--    </div>--%>
    <input type= "submit" value= "Ok" class= "btn btn-lg btn-primary" >
    <br/>
<%--    <a href= "javascript:history.back()">Назад</a>--%>
</body>
</html>
