<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Product create</title>
    <title>Registration</title>
</head>
<body>
<jsp:include page="header.jsp"/>
    <h1>registration</h1>
    <h4>${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/registration">
    <br>please write your login:     <input type="text" name="login">
    <br>
    <br>please write your name:      <input type="text" name="name">
    <br>
    <br>please write your password:  <input type="password" name="pwd">
    <br>
    <br>please repeat your password: <input type="password" name="pwd-repeat">
    <br>
    <button type="submit">Register</button>
</form>
</body>
</html>
