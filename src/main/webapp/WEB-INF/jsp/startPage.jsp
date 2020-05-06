<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Start page</title>
</head>
<div class="card">
    <div class="card-header">
        <ul class="nav nav-pills card-header-pills">
            <li class="nav-item">
                <a class="nav-link active" href="/">Main page</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/registration">Registration</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/login">Login</a>
            </li>
        </ul>
    </div>
</div>
<div class="jumbotron">
    <div class="text-info">${message}</div>
    <br>
    <h1 class="display-4">Hello, world!</h1>
    <p class="lead">This is a simple internet shop. Created by Vitalii Leshchenko</p>
    <hr class="my-4">
    <p>At first, You need to authorize.</p>
    <p>You can also visit following pages:
        <a class="nav-link" href="${pageContext.request.contextPath}/product/list">Product catalogue</a>
        <a class="navbar-brand" href="${pageContext.request.contextPath}/inject/data">INJECT</a>
    </p>

</div>
</body>
</html>