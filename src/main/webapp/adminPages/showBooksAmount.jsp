<%@ page import="java.util.List" %>
<%@ page import="liba.dto.BookDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.01.2016
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>BooksAmount</title>
</head>
<body>
<c:set value="${requestScope.nullValue}" var="nullValue"/>
<c:set value="null" var="nullInstance">
</c:set>
<c:if test="${nullValue eq nullInstance}">
    <b> You did not choose anything. Make sure that the selection point is in a right position.</b>
</c:if>

<form name="bookStorageMain" method="GET">
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
    <p>Books List:</p>

    <p>To delete some of them, just choose necessary books:</p>
    <table>
        <tbody>
        <tr>
            <th>select</th>
            <th>ID</th>
            <th>Author</th>
            <th>Title</th>
            <th>Amount</th>
        </tr>
        <c:forEach items="${sessionScope.booksAmount}" var="book">
            <tr>
                <td><input type="checkbox" name="answer" value="${book.id}"></td>
                <td><c:out value="${book.id}"></c:out></td>
                <td><c:out value="${book.author}"></c:out></td>
                <td><c:out value="${book.title}"></c:out></td>
                <td><c:out value="${book.count}"></c:out></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <tr>
        <td valign="bottom"><input type="submit" value="delete" formaction="/deleteBooks"></td>
    </tr>
</form>
<form name="updateBooks" method="post">

    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
    <p>Update Book Form:</p>
    <p>To update the books, just input necessary changes into form rows:</p>
    <table>
        <tbody>
        <tr>
            <th>Current ID</th>
            <th>ID</th>
            <th>Author</th>
            <th>Title</th>
            <th>Amount</th>
        </tr>
        <c:forEach items="${sessionScope.booksAmount}" var="book">
            <td><input type="hidden" name="oldID" value="${book.id}"><c:out value="${book.id}"></c:out></td>
            <td><input pattern="\d+" name="bookID" size="20" value="${book.id}"></td>
            <td><input type="text" name="bookAuthor" size="20" value="${book.author}"></td>
            <td><input type="text" name="bookTitle" size="20" value="${book.title}"></td>
            <td><input pattern="\d+" name="bookCount" size="20" value="${book.count}"></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <tr>
        <td valign="bottom"><input type="submit" value="update" formaction="/updateBooks"></td>
    </tr>
</form>
<p><a href="adminPages/adminMainPage.jsp">back</a></p>
</body>
</html>


