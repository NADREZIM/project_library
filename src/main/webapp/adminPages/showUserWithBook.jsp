<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02.02.2016
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
<form name="userwithsomething" method="post">
    <style>
        table,th,td
        {
            border:1px solid black;
        }
    </style>
    <p>User information:</p>
    <table>
        <tbody>
        <tr><th>ID</th><th>Name</th><th>Login</th><th>Password</th><th>Birthday</th></tr>
        <c:set var="user1" value="${userDto}"/>
            <tr>
                <td><c:out value="${user1.id}"></c:out></td>
                <td><c:out value="${user1.name}"></c:out></td>
                <td><c:out value="${user1.login}"></c:out></td>
                <td><c:out value="${user1.password}"></c:out></td>
                <td><c:out value="${user1.birthday}"></c:out></td>
            </tr>
        </tbody>
    </table>

    <style>
        table,th,td
        {
            border:1px solid black;
        }
    </style>
    <p>Books in reading:</p>
    <table>
        <tbody>
        <tr><th>ID</th><th>Author</th><th>Title</th><th>Count</th></tr>
        <c:forEach items="${sessionScope.listBook}" var="book">
            <tr>
            <td><c:out value="${book.id}"></c:out></td>
            <td><c:out value="${book.author}"></c:out></td>
            <td><c:out value="${book.title}"></c:out></td>
            <td><c:out value="${book.count}"></c:out></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
<p><a href="adminPages/adminMainPage.jsp">back</a></p>
</body>
</html>
