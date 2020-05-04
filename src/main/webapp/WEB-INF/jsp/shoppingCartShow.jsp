<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Product create</title>
    <title>ShoppingCart</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>Products at bucket</h1>
<table class="table">
    <th>Name</th>
    <th>Price</th>
    <th>Delete</th>
    <tr>
        <c:forEach var="shoppingCartList" items ="${shoppingCartList}">
    <tr>
        <td>
            <c:out value ="${shoppingCartList.name}"/>
        </td>
        <td>
            <c:out value ="${shoppingCartList.price}"/>
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/cart/delete/product?product_id=${shoppingCartList.id}">DELETE</a>
        </td>
    </tr>
    </c:forEach>
    </tr>
</table>
<h4>total price : ${price}</h4>
<br>
<a href="${pageContext.request.contextPath}/order/complete">Complete order</a>
<br>
</body>
</html>
