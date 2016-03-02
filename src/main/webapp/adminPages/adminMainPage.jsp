<%@ page import="liba.dto.UserDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.01.2016
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>admin Main Page</title>
</head>
<body>

Press <a href="/userAmount">get all users</a> to view all library customers.<p> </p>
Press <a href="/reportsAmount">get all reports</a> to view full report list.<p> </p>
Press <a href="/bookAmount">get all books</a> to view book main storage.<p> </p>
Press <a href = "addBookForm.jsp">add book</a> to add in the main storage<p></p>
To get an information about some user press <a href="findUserWithBook.html">here</a>
<form name="goBack" method="get">
    <tr>
        <td valign="top"><input type="submit" value="exit" formaction="/logOut"></td>
    </tr>
</form>
</body>
</html>
