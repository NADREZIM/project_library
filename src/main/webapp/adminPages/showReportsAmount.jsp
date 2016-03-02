<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.01.2016
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ReportAmount</title>
</head>
<body>
<form name="reportsAmountMain" method="GET">
    <style>
        table,th,td
        {
            border:1px solid black;
        }
    </style>
    <p>Reports list:</p>
    <table>
        <tbody>
        <tr><th>ID</th><th>BookTitle</th><th>BookAuthor</th><th>RentBookAmount</th><th>UserName</th><th>UserLogin</th><th>UserPassword</th><th>UserBirthday</th><th>RentBookDate</th><th>ReturnBookDate</th></tr>
        <c:forEach items="${sessionScope.reportsAmount}" var="report">
            <tr>
                <td><c:out value="${report.id}"></c:out></td>
                <td><c:out value="${report.book.title}"></c:out></td>
                <td><c:out value="${report.book.author}"></c:out></td>
                <td><c:out value="${report.book.count}"></c:out></td>
                <td><c:out value="${report.user.name}"></c:out></td>
                <td><c:out value="${report.user.login}"></c:out></td>
                <td><c:out value="${report.user.password}"></c:out></td>
                <td><c:out value="${report.user.birthday}"></c:out></td>
                <td><c:out value="${report.rentBook}"></c:out></td>
                <td><c:out value="${report.returnBook}"></c:out></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
<p><a href="adminPages/adminMainPage.jsp">back</a></p>
</body>
</html>
