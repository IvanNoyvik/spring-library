<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
            <c:if test="${requestScope.errors != null}">
                <c:forEach var="error" items="${requestScope.errors}">
                    <p>
                    <h3 class="error">${error}</h3>
                </c:forEach>
            </c:if>

        </div>


        <div id="templatemo_new_books">
            <p>
            <h2>Your account is blocked</h2>
            <p>
            <li><a href="<c:url value="/logout"/>" class="current">Logout</a></li>
        </div>
    </div>

    <div id="templatemo_content">

        <div id="templatemo_content_left">
            <div class="templatemo_content_left_section">
                <h1>Your messages</h1>
                <c:if test="${!empty requestScope.messages}">
                    <c:forEach items="${requestScope.messages}" var="mess">
                        <div class="templatemo_product_box">
                            <span style="font-size:13px; font-style: italic">${mess.dateSent}:</span>
                            <div class="product_info">
                                    ${mess.content}
                            </div>
                        </div>

                    </c:forEach>
                </c:if>

            </div>
            <div class="templatemo_content_left_section">

            </div>


        </div>


        <div id="templatemo_content_right">

            <h2>Send an unlock request to the administrator</h2>
            <form accept-charset="UTF-8" action="<c:url value="/sentMessage"/>" method="post">
                <input name="user.id" value="${sessionScope.user.id}" type="hidden"/>
                <fieldset>
                    <textarea name="content" cols="30" rows="5">Enter text ... max 255 chars</textarea><br/>
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