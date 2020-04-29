<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Your order</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="card">
    <div class="card-body">
        <div class="card-header">
            Order ID : ${id}
        </div>
        <div class="card-body">
            Order created : ${date}
            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th>#</th>
                    <th>Product</th>
                    <th>Price</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <c:forEach var="product" items="${products}" varStatus="loopCounter" >
                <tr>
                    <td>
                        <c:out value="${loopCounter.index + 1}"/>
                    </td>
                    <td>
                        <c:out value="${product.name}"/>
                    </td>
                    <td>
                        <c:out value="${product.price}"/>
                    </td>
                </tr>
                    </c:forEach>
                </tr>
                </tbody>
            </table>
        </div>
        total price : ${price}
    </div>
    <a href="${pageContext.request.contextPath}/deleteOrder?orderId=${id}">delete order</a>
    <a href="#">confirm order</a>
</div>
</body>
</html>
