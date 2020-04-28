<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<html>
<head>
    <title>ShoppingCart</title>
</head>
<body>
<br>
<a href="${pageContext.request.contextPath}/" style="text-align: left">Main page</a>
<br>
<h1>Products at bucket</h1>
<table>
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
            <a href="${pageContext.request.contextPath}/productDeleteFromBucket?productId=${shoppingCartList.id}">DELETE</a>
        </td>
    </tr>
    </c:forEach>
    </tr>
</table>
<h4>total price : ${price}</h4>
<br>
<a href="${pageContext.request.contextPath}/shoppingCartShow">Complete order</a>
<br>
</body>
</html>
