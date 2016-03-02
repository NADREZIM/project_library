<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.01.2016
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>UserAmount</title>
</head>
<body>
<c:set value="${requestScope.nullValue}" var="nullValue"/>
<c:set value="null" var="nullInstance">
</c:set>
<c:if test="${nullValue eq nullInstance}">
    <b> You did not choose anything. Make sure that the selection point is in a right position.</b>
</c:if>
<form name="userAmountMain" method="get">
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
    <p>Users list:</p>
    <p>To delete some of them, just choose necessary customers:</p>
    <table>
        <tbody>
        <tr>
            <th>select</th>
            <th>ID</th>
            <th>Name</th>
            <th>Login</th>
            <th>Password</th>
            <th>Birthday</th>
        </tr>
        <c:forEach items="${sessionScope.usersAmount}" var="user">
            <tr>
                <td><input type="checkbox" name="chosenUsers" value="${user.id}"></td>
                <td><c:out value="${user.id}"></c:out></td>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.password}"></c:out></td>
                <td><c:out value="${user.birthday}"></c:out></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <tr>
        <td valign="bottom"><input type="submit" value="delete" formaction="/deleteUsers"></td>
    </tr>
</form>

<p><a href="adminPages/adminMainPage.jsp">back</a></p>
</body>
</html>
