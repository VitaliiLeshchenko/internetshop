<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Product create</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>Create new product</h1>
<h4>${message}</h4>
    <form method="post" action="${pageContext.request.contextPath}/product/create">
    <br>please write name  :<input type="text" name="name">
    <br>
    <br>please write price :<input type="password" name="price">
    <br>
    <button type="submit">Add</button>
    </form>
</body>
</html>
