<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<c:set value="${requestScope.adminPath}" var="adminPath"/>
<c:set value="http://localhost:8080/checking/admin" var="realAdminPath">
</c:set>
<c:if test="${adminPath eq realAdminPath}">
    <form name="roleAdmin" method="get">
    <input type="submit" value="likeAdmin" formaction="adminPages/adminMainPage.jsp">
    <input type="submit" value="likeUser" formaction="bookCatalog">
    </form>
</c:if>

<%
    String show = (String) request.getAttribute("warning");
    if (show == null) {
%>
Please, login yourself in this page.
<%} else {%>
Warning: <%=request.getAttribute("warning")%>
<% } %>


<form name="VisitForm" method="post">
    <table border="1">

        <tr>
            <td valign="top">Your login:</td>
            <td valign="top"><input type="text" name="login" size="20" placeholder="your login"></td>
        </tr>

        <tr>
            <td valign="top">Your login password:</td>
            <td valign="top"><input type="text" name="userLoginPassword" size="20" placeholder="your password">
            </td>
        </tr>

        <tr>
            <td valign="top"><input type="submit" value="enter" formaction="/checking"></td>
        </tr>

        <tr>
            <td valign="top"><input type="submit" value="registration" formaction="userPages/welcome_to_library.html"></td>
        </tr>

    </table>

</form>
Forgot your password or login? Press<p><a href="userPages/loginPasswordReminder.jsp">here</a></p>

</body>
</html>