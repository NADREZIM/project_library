<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.02.2016
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dataReminder</title>
</head>
<body>
Insert your login or password in the form:
<form name = "Reminder" method="post">
    <tr>
        <td valign="top">Your login:</td>
        <td valign="top"><input type="text"name="login"size="20"placeholder="your login"></td>
    </tr>

    <tr>
        <td valign="top">Your password</td>
        <td valign="top"><input type="text"name="password"size="20" placeholder="your password"></td>
    </tr>

    <tr>
        <td valign="bottom"><input type="submit" value="confirm" formaction="/remind"></td>
    </tr>

</form>
</body>
</html>
