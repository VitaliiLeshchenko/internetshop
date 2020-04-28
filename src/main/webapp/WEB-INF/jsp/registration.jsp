<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<br>
<a href="${pageContext.request.contextPath}/" style="text-align: left">Main page</a>
<br>
    <h1>registration</h1>
    <h4>${massage}</h4>
<form method="post" action="${pageContext.request.contextPath}/registration">
    <br>please provide your login:<input type="text" name="login">
    <br>please provide your password:<input type="password" name="pwd">
    <br>please repeat your password:<input type="password" name="pwd-repeat">
    <button type="submit">Register</button>
</form>
</body>
</html>
