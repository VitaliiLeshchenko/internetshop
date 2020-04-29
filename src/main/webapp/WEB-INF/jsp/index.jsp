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
  <jsp:include page="header.jsp"/>
  <div class="card">
      <nav class="navbar navbar-light bg-white">
          <a class="navbar-brand" href="${pageContext.request.contextPath}/injectData">TEST DATA INJECT
              - create test Users and Products</a>
      </nav>
      <nav class="navbar navbar-light bg-white">
          <a class="navbar-brand" href="${pageContext.request.contextPath}/allUsers">All users</a>
      </nav>
      <nav class="navbar navbar-light bg-white">
          <a class="navbar-brand" href="${pageContext.request.contextPath}/productDelete">AdminProduct</a>
      </nav>
  </div>
  </body>
</html>
