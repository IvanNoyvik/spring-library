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
            <c:url value="/front" var="main">
                <c:param name="command" value="Forward"/>
                <c:param name="forward" value="main"/>
            </c:url>
            <li><a href="<c:out value="${main}"/>" class="current">Main</a></li>

        </ul>


        <ul>
            <c:if test="${empty sessionScope.user}">
                <!-- LOGIN -->
                <c:url value="/front" var="login">
                    <c:param name="command" value="Forward"/>
                    <c:param name="forward" value="login"/>
                </c:url>
                <li><a href="<c:out value="${login}"/>" class="current">Login</a></li>

                <!-- REGISTR -->
                <c:url value="/front" var="registration">
                    <c:param name="command" value="Forward"/>
                    <c:param name="forward" value="registration"/>
                </c:url>
                <li><a href="<c:out value="${registration}"/>" class="current">Registration</a></li>
            </c:if>


            <c:if test="${!empty sessionScope.user}">

                <!-- PROFILE -->
                <c:url value="/front" var="profile">
                    <c:param name="command" value="Forward"/>
                    <c:param name="forward" value="profile"/>
                </c:url>
                <li><a href="<c:out value="${profile}"/>" class="current">Profile</a></li>

                <!-- LOGOUT -->
                <c:url value="/front" var="logout">
                    <c:param name="command" value="Logout"/>
                </c:url>
                <li><a href="<c:out value="${logout}"/>" class="current">Logout</a></li>


            </c:if>
        </ul>
    </c:if>
</div>

</body>
</html>
