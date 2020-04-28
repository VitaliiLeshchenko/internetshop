<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="/WEB-INF/css/style.css"%></style>

<html>
<head>
    <title>Create product</title>
</head>
<body>
<br>
<a href="${pageContext.request.contextPath}/" style="text-align: left">Main page</a>
<br>
<h1>Create new product</h1>
<h4>${massage}</h4>
    <form method="post" action="${pageContext.request.contextPath}/productCreate">
    <br>please write name  :<input type="text" name="name">
    <br>
    <br>please write price :<input type="password" name="price">
    <br>
    <button type="submit">Add</button>
    </form>
</body>
</html>
