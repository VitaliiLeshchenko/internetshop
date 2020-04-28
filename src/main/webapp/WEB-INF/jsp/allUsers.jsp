<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<html>
<head>
    <title>All users here</title>
</head>
<body>
<br>
<a href="${pageContext.request.contextPath}/" style="text-align: left">Main page</a>
<br>
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
