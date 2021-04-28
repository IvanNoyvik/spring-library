<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Head</title>
</head>
<body>

<div id="admin_menu" style="display: flex; justify-content: space-between;">
    <c:if test="${sessionScope.user.status.status ne 'Locked'}">

        <ul>
            <!-- MAIN -->
            <li><a href="<c:url value="/main?page=0"/>" class="current">Main</a></li>

        </ul>


        <ul>
            <c:if test="${empty sessionScope.user}">
                <!-- LOGIN -->

                <li><a href="<c:url value="/login"/>" class="current">Login</a></li>

                <!-- REGISTR -->
                <li><a href="<c:url value="/registration"/>" class="current">Registration</a></li>
            </c:if>


            <c:if test="${!empty sessionScope.user}">

                <!-- PROFILE -->
                <li><a href="<c:url value="/profile"/>" class="current">Profile</a></li>

                <!-- LOGOUT -->
                <li><a href="<c:url value="/logout"/>" class="current">Logout</a></li>


            </c:if>
        </ul>
    </c:if>
</div>

</body>
</html>
