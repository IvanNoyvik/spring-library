<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>
        Authenticate page
    </title>
</head>
<body>

<c:if test="${error != null}">
    <h3 style="color:red;">* ${error}</h3>
</c:if>

<h2>Login form</h2>

<form method="post" action="<c:url value="/login" /> ">
    <p>Login</p>
    <p><input type="text" name="login" placeholder="Please enter login"/></p>
    <p>Password</p>
    <p><input type="text" name="password" placeholder="Please enter password"/></p>
    <p><input type="submit" value="Login"/></p>
</form>


</body>
</html>
