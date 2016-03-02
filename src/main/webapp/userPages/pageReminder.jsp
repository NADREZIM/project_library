<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.02.2016
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
String data = (String) session.getAttribute("forgotten data");
    if (data==null){
        request.setAttribute("warning","<b>Incorrect login or login password. Please try again ones more.</b>");
        request.getRequestDispatcher("/logIn.jsp").forward(request,response);
    }else {%>
Your personal informational data is: <%=session.getAttribute("forgotten data")%>. Do not forget it))
<%}%>
<form name = "back" method="get">
    <tr>
        <td valign="bottom"><input type="submit" value="back" formaction="/logOut"></td>
    </tr>
</form>
</body>
</html>
