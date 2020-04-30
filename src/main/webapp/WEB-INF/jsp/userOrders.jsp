<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Your orders</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="card">
    <div class="card-body">
        <div class="card">
            <div class="card-header">
                All user Orders
            </div>
            <div class="card-body">
                <table class="table">
                    <thead class="thead-light">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id</th>
                        <th scope="col">info</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <c:forEach var="order" items ="${order}" varStatus="loopCounter">
                    <tr>
                        <td>
                            <c:out value ="${loopCounter.index + 1}"/>
                        </td>
                        <td>
                            <c:out value ="${order.id}"/>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/getUserOrder?orderId=${order.id}">INFO</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/deleteOrder?orderId=${order.id}">DELETE</a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
