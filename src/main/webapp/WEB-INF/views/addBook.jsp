<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Add Book</title>
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

        <div id="templatemo_content_left">
            <div class="templatemo_content_left_section">

            </div>
            <div class="templatemo_content_left_section">

            </div>


        </div>


        <div id="templatemo_content_right">
            <c:if test="${sessionScope.user.roles.contains(applicationScope.admin)}">

                <h1>Create new book: </h1>

                <form action="<c:url value="/addBook"/>" method="post">

                    <label>Title:
                        <input name="title" required type="text" value="title"/>
                    </label>
                    <div class="product_info">

                        <label> Genre:
                            <select name="genres" multiple="multiple" required>
                                <option disabled>Select one or more genre</option>
                                <c:forEach items="${requestScope.genres}" var="genre">
                                    <option value="${genre.genre}">${genre.genre}</option>
                                </c:forEach>
                            </select>
                        </label>
                        <br/>

                        <label> Author:
                            <select name="author">
                                <c:forEach items="${requestScope.authors}" var="author">
                                    <option value="${author.author}">${author.author}</option>
                                </c:forEach>
                            </select>
                        </label>
                        <br/>
                        <label>Quantity:
                            <input name="quantity" type="text" value="quantity"
                                   required="" placeholder=">0" pattern="^0*[1-9]\d*$"/></label>
                        <br/>

                        <label>Description:
                            <textarea name="description" cols="30" rows="5"></textarea><br/>
                        </label>

                        <input type="submit" value="Add book"/>

                        <div class="buy_now_button"><a href="<c:url value="/main"/>">Cancel</a></div>
                    </div>

                    <div class="cleaner_with_height">&nbsp;</div>

                </form>

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