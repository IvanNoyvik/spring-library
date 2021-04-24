<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<h2>Registration form with spring tags</h2>

<%--@elvariable id="user" type="by.gomel.noyvik.library.bean.User"--%>
<form:form modelAttribute="user" action="registration" method="post">
    <p>
        <form:label path="login">Login</form:label>
    </p>
    <p>
            <form:input path="login" name="login"/>
    <p>
        <form:label path="password">Password</form:label>
    </p>
    <p>
        <form:input path="password" name="password"/>
    </p>
        <form:button name="Registration">Registration</form:button>
    </p>
</form:form>

</body>
</html>
