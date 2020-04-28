<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<html>
  <head>
    <title>My first web page!!!</title>
  </head>
  <body>
  <h3><br> ${time}</h3>
  <br>
  <a href="${pageContext.request.contextPath}/injectData">TEST DATA INJECT
    - create test Users and Products</a>
  <br>
  <a href="${pageContext.request.contextPath}/registration">registration</a>
  <br>
  <a href="${pageContext.request.contextPath}/productCreate">create product</a>
  <br>
  <a href="${pageContext.request.contextPath}/productList">see all products</a>
  <br>
  <a href="${pageContext.request.contextPath}/shoppingCartShow">shoppingCartShow</a>
  <br>
  <a href="${pageContext.request.contextPath}/allUsers">all users</a>
  </body>
</html>
