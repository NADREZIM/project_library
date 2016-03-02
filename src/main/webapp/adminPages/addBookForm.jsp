<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02.03.2016
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add book</title>
</head>
<body>
<form name="addBookForm" method="post">
<table border="1">
    <tr>
        <td valign="top">Book author:</td>
        <td valign="top"><input type="text" name="author" size="20"placeholder="your name" required></td>
    </tr>

    <tr>
        <td valign="top">Book title:</td>
        <td valign="top"><input type="text" name="title" size="20"placeholder="your login" required></td>
    </tr>

    <tr>
        <td valign="top">Book count:</td>
        <td valign="top"><input pattern="\d+" name="count" size="20" placeholder="your password" required></td>
    </tr>

    <tr>
        <td valign="top"><input type="submit" value="submit info" formaction="/addBook"></td>
    </tr>
</table>
</form>
<p><a href="adminMainPage.jsp">back</a></p>
</body>
</html>
