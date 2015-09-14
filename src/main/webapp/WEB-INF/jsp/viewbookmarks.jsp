<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Contacts manager</title>

</head>
<body>
Bookmarks List for <b>${account.username}</b>
<hr>

<table width="40%" cellpadding="5" bordercolor="#000066"
       bgcolor="#FFFFFF" border="1"   cellspacing="0">
    <tr>
        <td><div align="center"><b>id
        </b></div></td>
        <td><div align="center"><b>url
        </b></div></td>
        <td><div align="center"><b>description
        </b></div></td>
        <td><div align="center"><b>action
        </b></div></td>
    </tr>

        <c:forEach var="bookmark" items="${bookmarks}">
            <tr>
            <td><div align="center"><c:out  value="${bookmark.id}"/>
            </div></td>
                <td><div align="center"><c:out  value="${bookmark.uri}"/>
                </div></td>
                <td><div align="center"><c:out  value="${bookmark.description}"/>
                </div></td>
                <td><div align="center"><a href="/ManageBookmarks/${account.username}/delete?bid=${bookmark.id}">
                    delete</a>&nbsp|&nbsp
                    <a href="/ManageBookmarks/${account.username}/edit?bid=${bookmark.id}">edit</a>
                </div></td>

            </tr>
        </c:forEach>
</table>
<hr>
<a href="/ManageBookmarks/${account.username}/add">Add new bookmark</a>

</body>
</html>