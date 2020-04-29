<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Product create</title>
    <title>product list</title>
</head>
<body>
<jsp:include page="header.jsp"/>


<div class="card">
    <div class="card-body">
        <div class="card">
            <div class="card-header">
                All products
            </div>
            <table class="table">
                <th>#</th>
                <th>Name</th>
                <th>Price</th>
                <th>Add to bucket</th>
                <tr>
                    <c:forEach var="products" items ="${products}" varStatus="count">
                <tr>
                    <td>
                        <c:out value ="${count.index}"/>
                    </td>
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
        </div>
    </div>
</div>

</body>
</html>
