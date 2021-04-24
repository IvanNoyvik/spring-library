<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.gomel.noyvik.library.bean.Role" %>

<html>
<head>
    <title>
       Main page
    </title>
</head>
<body>

<%--<form action="<%= ApplicationConstant.LOGOUT_KEY %>" method="post" >--%>
<%--    <input type="submit" name="action" value="<%= ApplicationConstant.LOGOUT_PAGE_TITLE %>">--%>
<%--</form>--%>

<p>User login = ${user.login}</p>
<p>User password = ${user.password}</p>
<p>User role =  ${user.role}</p>


<c:if test="${user.role == Role.ADMIN}" >
    <ul>
       <c:forEach items="${users}" var="user">
           <li>${user.login} ${user.role}</li>
       </c:forEach>
    </ul>
</c:if>

</body>
</html>
