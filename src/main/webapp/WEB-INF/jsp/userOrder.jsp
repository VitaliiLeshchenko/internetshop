<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<br>Order ID : ${id}<br>
<br>Order created : ${date}
<br>Products at order : ${products}
<br>total price : ${price}

<br><a href="${pageContext.request.contextPath}/deleteOrder?orderId=${id}">delete order</a>
<br><a href="#">confirm order</a>
</body>
</html>
