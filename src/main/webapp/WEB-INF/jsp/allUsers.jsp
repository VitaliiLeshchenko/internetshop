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

<div class="card">
    <div class="card-body">
        <div class="card">
            <div class="card-header">
                All users
            </div>
            <div class="card-body">
                <table class="table">
                    <thead class="thead-light">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Login</th>
                        <th scope="col">Id</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <c:forEach var="users" items ="${users}" varStatus="loopCounter">
                    <tr>
                        <td>
                            <c:out value ="${loopCounter.index}"/>
                        </td>
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
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
