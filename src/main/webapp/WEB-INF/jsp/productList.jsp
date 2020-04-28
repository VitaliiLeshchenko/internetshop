<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<html>
<head>
    <title>product list</title>
</head>
<body>
<br>
<a href="${pageContext.request.contextPath}/" style="text-align: left">Main page</a>
<br>
<h1>All products</h1>
<table>
    <th>Name</th>
    <th>Price</th>
    <th>Add to bucket</th>
    <tr>
        <c:forEach var="products" items ="${products}">
    <tr>
        <td>
            <c:out value ="${products.name}"/>
        </td>
        <td>
            <c:out value ="${products.price}"/>
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/ProductAddToBucket?productId=${products.id}">add to bucket</a>
        </td>
    </tr>
    </c:forEach>
    </tr>
</table>

</body>
</html>
