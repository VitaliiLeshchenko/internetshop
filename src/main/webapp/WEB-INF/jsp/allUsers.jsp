<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>All users</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <h1>All Users</h1>
    <table>
            <th>Login</th>
            <th>Id</th>
            <th>Delete</th>
        <tr>
            <c:forEach var="users" items ="${users}">
        <tr>
            <td>
            <c:out value ="${users.login}"/>
            </td>
            <td>
            <c:out value ="${users.id}"/>
            </td>
        <td>
            <a href="${pageContext.request.contextPath}/deleteUser?userId=${users.id}">DELETE</a>
        </td>
        </tr>
        </c:forEach>
        </tr>
    </table>
</body>
</html>
