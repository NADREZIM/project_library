<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.01.2016
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>BookStorage</title>
</head>
<body>

<c:set value="${requestScope.nullValue}" var="nullValue"/>
<c:set value="null" var="nullInstance">
</c:set>
<c:if test="${nullValue eq nullInstance}">
   <b> You did not choose anything. Make sure that the selection point is in a right position.</b>
</c:if>

<c:set value="${requestScope.BookCountEnding}" var="bookCount"/>
<c:set value="there is no such a books in the storage" var="check">
</c:set>
<c:if test="${bookCount eq check}">
    <b> Unfortunately, you can not take such a book any more.((</b>
</c:if>

<form name="bookStorageMain" method="POST">
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
    <p>Welcome to library</p>

    <p>Books you can read:</p>
    <table>
        <tbody>
        <tr>
            <th>select</th>
            <th>ID</th>
            <th>Author</th>
            <th>Title</th>
            <th>Amount</th>
        </tr>
        <c:forEach items="${sessionScope.bookCatalog}" var="book">
            <tr>
                <td><input type="radio" name="answer" value="${book.id}" checked></td>
                <td><c:out value="${book.id}"></c:out></td>
                <td><c:out value="${book.author}"></c:out></td>
                <td><c:out value="${book.title}"></c:out></td>
                <td><c:out value="${book.count}"></c:out></td>

            </tr>
            <c:remove var="book"/>
        </c:forEach>

        <tr>
            <td valign="bottom"><input type="submit" value="take book" formaction="/connecting"></td>
        </tr>
        </tbody>
    </table>
</form>
<form name="bookStorageMain" method="get">
    <tr>
        <td valign="top"><input type="submit" value="exit" formaction="/logOut"></td>
    </tr>
</form>

<form name="bookStorageMain" method="get">
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>

    <p>Books you are reading now:</p>
    <table>
        <tbody>
        <tr>
            <th>ID</th>
            <th>Author</th>
            <th>Title</th>
            <th>Rent time</th>
        </tr>
        <c:forEach items="${sessionScope.dateRentOfBookAndBooks}" var="userRentBooks">
            <tr>
                <td><input type="checkbox" name="answer" value="${userRentBooks.value.id} ${userRentBooks.key}"></td>
                <td><c:out value="${userRentBooks.value.id}"></c:out></td>
                <td><c:out value="${userRentBooks.value.author}"></c:out></td>
                <td><c:out value="${userRentBooks.value.title}"></c:out></td>
                <td><c:out value="${userRentBooks.key}"></c:out></td>

            </tr>
        </c:forEach>
        <tr>
            <td valign="bottom"><input type="submit" value="return book" formaction="/returnBook"></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>
