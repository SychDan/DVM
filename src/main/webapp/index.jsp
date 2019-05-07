<%--
  Created by IntelliJ IDEA.
  User: danny
  Date: 11.04.19
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.webjars.org/tags" prefix="wj"%>
<html>
<head>
    <title>Title</title>
    <link rel='stylesheet' href='<wj:locate path="css/bootstrap.min.css" relativeTo="META-INF/resources"/>'>
</head>
<body>
Hello ${name}
<button onclick="location.href='/home'" class="btn btn-success">Войти</button>
</body>
</html>
