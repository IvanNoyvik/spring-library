<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>BAN</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link href="<c:url value="/static/main/templatemo_style.css" />" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="templatemo_container">

    <c:import url="head.jsp"/>

    <div id="templatemo_header">
        <div id="templatemo_special_offers">
            <p>
                <c:if test="${!empty requestScope.resp}">
                    <span class="resp"><c:out value="${requestScope.resp}"/></span>
                </c:if>
            </p>
        </div>


        <div id="templatemo_new_books">
            <p>
            <h2>Your account is blocked</h2>
            <p>
                <c:url value="/front" var="logout">
                    <c:param name="command" value="Logout"/>
                </c:url>
            <li><a href="<c:out value="${logout}"/>" class="current">Logout</a></li>
        </div>
    </div>

    <div id="templatemo_content">

        <div id="templatemo_content_left">
            <div class="templatemo_content_left_section">

            </div>
            <div class="templatemo_content_left_section">

            </div>


        </div>


        <div id="templatemo_content_right">

            <h2>Send an unlock request to the administrator</h2>
            <form accept-charset="UTF-8" action="<c:url value="/front"/>" method="post">
                <fieldset>
                    <input name="command" type="hidden" value="SentMessage"/> <br/>
                    <textarea name="context" cols="30" rows="5">Enter text</textarea><br/>
                    <input type="submit" value="Sent"/> <br/>
                </fieldset>
            </form>
        </div>

        <div class="cleaner_with_height">&nbsp;</div>


        <div class="cleaner_with_height">&nbsp;</div>
    </div>

    <div id="templatemo_footer">

        <a href="https://www.linkedin.com/in/ivan-novik/"><strong>About me</strong></a>
    </div>

</div>


</body>
</html>