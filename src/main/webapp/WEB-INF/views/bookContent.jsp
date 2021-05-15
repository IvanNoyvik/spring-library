<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Book Content</title>
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
                <c:if test="${!empty requestScope.answer}">
                    <span class="answer"><c:out value="${requestScope.answer}"/></span>
                </c:if>
            </p>
        </div>

        <div id="templatemo_new_books">

        </div>
    </div>

    <div id="templatemo_content">


        <div id="templatemo_content_right" style="float: unset; width: auto;">

            <c:if test="${!empty requestScope.book}">

                <h1>${requestScope.book.title} <span>(${requestScope.book.author.author})</span></h1>
                <h2>
                    <c:out value="O_ops... The contents have gone somewhere. Maybe it's "/> <a style="color: #3b5998"
                                                                                               href="https://oz.by"><strong>here</strong></a>
                </h2>
            </c:if>

        </div>

        <div class="cleaner_with_height">&nbsp;</div>
    </div>

    <div id="templatemo_footer">

        <a href="https://www.linkedin.com/in/ivan-novik/"><strong>About me</strong></a>
    </div>

</div>
</body>
</html>