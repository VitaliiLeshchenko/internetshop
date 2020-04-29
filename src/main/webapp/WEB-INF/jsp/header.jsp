<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
</head>
<body>
<div class="card">
    <div class="card-header">
        <ul class="nav nav-pills card-header-pills">
            <li class="nav-item col-2">
                <a class="nav-link active" href="${pageContext.request.contextPath}/">Main page</a>
            </li>
            <li class="nav-item col-2">
                <a class="nav-link" href="${pageContext.request.contextPath}/registration">registration</a>
            </li>
            <li class="nav-item col-2">
                <a class="nav-link" href="${pageContext.request.contextPath}/productList">Product catalogue</a>
            </li>
            <li class="nav-item col-2">
                <a class="nav-link" href="${pageContext.request.contextPath}/getUserOrders">Your orders</a>
            </li>
            <li class="nav-item col-2">
                <a class="nav-link" href="${pageContext.request.contextPath}/shoppingCartShow">Your card</a>
            </li>
            <li class="nav-item col-2">
                <a class="nav-link" href="#">Your Page</a>
            </li>
        </ul>
    </div>
</div>
</body>
</html>
