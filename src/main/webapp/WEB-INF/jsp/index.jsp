<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Main page</title>
  </head>
  <body>
  <div class="card">
      <div class="card-header">
          <ul class="nav nav-tabs card-header-tabs">
              <li class="nav-item col-2">
                  <a class="nav-link active" href="${pageContext.request.contextPath}/">Main page</a>
              </li>
              <li class="nav-item col-8">

              </li>
              <li class="nav-item col-2">
                  <a class="nav-link" href="${pageContext.request.contextPath}/registration">Registration</a>
              </li>
          </ul>
      </div>
      <nav class="navbar navbar-light bg-white">
          <a class="navbar-brand" href="${pageContext.request.contextPath}/injectData">TEST DATA INJECT
              - create test Users and Products</a>
      </nav>
      <nav class="navbar navbar-light bg-white">
          <a class="navbar-brand" href="${pageContext.request.contextPath}/productCreate">Create product</a>
      </nav>
      <nav class="navbar navbar-light bg-white">
          <a class="navbar-brand" href="${pageContext.request.contextPath}/productList">Product catalogue</a>
      </nav>
      <nav class="navbar navbar-light bg-white">
          <a class="navbar-brand" href="${pageContext.request.contextPath}/shoppingCartShow">Shopping Cart</a>
      </nav>
      <nav class="navbar navbar-light bg-white">
          <a class="navbar-brand" href="${pageContext.request.contextPath}/allUsers">All users</a>
      </nav>
  </div>
  </body>
  </body>
</html>
