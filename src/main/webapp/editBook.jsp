<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Edit Book</title>
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
            <c:if test="${sessionScope.user.roles.contains(applicationScope.admin)}">
                <%--                <form action="<c:url value="/front"/>" method="post" >--%>
                <%--                    <input type="hidden" name="command" value="Forward"/>--%>
                <%--                    <input type="hidden" name="forward" value="editBook"/>--%>
                <%--                    <h1><input type="submit" value="Edit book"/></h1>--%>
                <%--                </form>--%>
            </c:if>

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
            <c:if test="${!empty requestScope.book}">

                <h1><label>Edit book: </label>
                    <span>${requestScope.book.title}</span>
                </h1>

                <div class="image_panel">
                    <form action="<c:url value="/addImage"/>" method="post" enctype="multipart/form-data">
                        <input name="bookId" type="hidden" value="${requestScope.book.id}"/>

                        <c:url value="/front" var="image">
                            <c:param name="bookId" value="${requestScope.book.id}"/>
                            <c:param name="command" value="GetImage"/>
                        </c:url>
                        <div>
                            <img src="${image}" alt="CSS Template" width="150"
                                 height="150"/>
                        </div>
                        <input type="file" name="image" accept="image/*"/>
                        <input type="submit" value="Add/change Image"/>

                    </form>
                </div>


                <form action="<c:url value="/front"/>" method="post">
                    <input name="command" type="hidden" value="EditBook"/>
                    <input name="bookId" type="hidden" value="${requestScope.book.id}"/>

                    <label>Title: </label>
                    <input name="title" required type="text" value="${requestScope.book.title}"/>

                    <div class="product_info">

                        <label> Genre (/<c:forEach items="${requestScope.book.genres}" var="genre">
                            <c:out value="${genre.genre}"/>/
                        </c:forEach>)<br/>
                            <select name="genres" multiple="multiple" required size="5">
                                <c:forEach items="${requestScope.genres}" var="genre">
                                    <c:if test="${requestScope.book.genres.contains(genre)}">
                                        <option selected value="${genre.genre}">${genre.genre}</option>
                                    </c:if>
                                    <c:if test="${!requestScope.book.genres.contains(genre)}">
                                        <option value="${genre.genre}">${genre.genre}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </label>
                        <br/>

                        <label> Author (${requestScope.book.author.author}):
                            <select name="author">
                                <c:forEach items="${requestScope.authors}" var="author">
                                    <c:if test="${author eq requestScope.book.author}">
                                        <option selected value="${author.author}">${author.author}</option>
                                    </c:if>
                                    <c:if test="${author ne requestScope.book.author}">
                                        <option value="${author.author}">${author.author}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </label>
                        <br/>
                        <label>Quantity:
                            <input name="quantity" type="text" value="${requestScope.book.quantity}"
                                   required="" placeholder=">=0" pattern="[0-9]+"/></label>


                        <textarea name="description" cols="30" rows="5">${requestScope.book.description}</textarea><br/>

                        <input type="reset" value="reset"/>
                        <input type="submit" value="Edit book"/>


                        <c:url value="/front" var="Cancel">
                            <c:param name="command" value="Forward"/>
                            <c:param name="forward" value="main"/>
                        </c:url>
                        <div class="buy_now_button"><a href="<c:out value="${Cancel}"/>">Cancel</a></div>

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