<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Book</title>
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
                <form action="<c:url value="/front"/>" method="post">
                    <input type="hidden" name="command" value="Forward"/>
                    <input type="hidden" name="forward" value="editBook"/>
                    <input type="hidden" name="bookId" value="${requestScope.book.id}"/>
                    <h1><input type="submit" value="Edit book"/></h1>
                </form>
            </c:if>

        </div>
    </div>

    <div id="templatemo_content">

        <div id="templatemo_content_left">
            <div class="templatemo_content_left_section">
                <c:if test="${!empty requestScope.orders && sessionScope.user.roles.contains(applicationScope.admin)}">
                    <h1>Users read this book</h1>
                    <table>
                        <tr>
                            <th><h2>User login</h2></th>
                            <th><h2>Return date</h2></th>
                        </tr>
                        <c:forEach items="${requestScope.orders}" var="order">
                            <tr>
                                <td>
                                        ${order.user.authenticate.login}
                                </td>
                                <td>
                                        ${order.dateReceiving.plusDays(order.duration)}
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>

            </div>
            <div class="templatemo_content_left_section">

            </div>


        </div>


        <c:if test="${!empty requestScope.book}">

            <div id="templatemo_content_right">

                <h1>${requestScope.book.title} <span>(${requestScope.book.author.author})</span></h1>
                <h2>Genre: <span>(/<c:forEach items="${requestScope.book.genres}" var="genre">${genre.genre}/</c:forEach>)
                        </span></h2>

                <c:url value="/front" var="image">
                    <c:param name="bookId" value="${requestScope.book.id}"/>
                    <c:param name="command" value="GetImage"/>
                </c:url>
                <div>
                    <img src="${image}" alt="CSS Template" width="150"
                         height="150"/>
                </div>

                <div class="product_info">

                    <c:if test="${requestScope.book.quantity == 0}">
                        <h3>Not available</h3>
                    </c:if>
                    <c:if test="${(requestScope.book.quantity > 0)}">

                        <h3>${requestScope.book.quantity} pcs in stock</h3>

                        <c:if test="${!empty sessionScope.user and (sessionScope.user.status.status eq 'OK') and !requestScope.haveBook}">
                            <form accept-charset="UTF-8" action="<c:url value="/front"/>" method="post">
                                <label>Duration
                                    <input class="duration-main" name="days" type="text"
                                           required="" placeholder="(1-180)in days..."
                                           pattern="^0*[1-9]\d*$"/>
                                </label>
                                <input name="command" type="hidden" value="AddOrder"/>
                                <input name="bookId" type="hidden" value="${requestScope.book.id}"/>
                                <input type="submit" value="Add in my library"/>
                            </form>

                        </c:if>
                    </c:if>

                    <p>${requestScope.book.description}</p>

                    <c:if test="${requestScope.haveBook}">

                        <c:url value="/front" var="bookContent">
                            <c:param name="command" value="Forward"/>
                            <c:param name="forward" value="bookContent"/>
                            <c:param name="bookId" value="${requestScope.book.id}"/>
                        </c:url>
                        <div class="buy_now_button"><a class="buy_now_button"
                                                       href="<c:out value="${bookContent}"/>">Read</a>
                        </div>

                    </c:if>

                </div>

                <div class="cleaner_with_height">&nbsp;</div>

            </div>


        </c:if>


        <div class="cleaner_with_height">&nbsp;</div>
    </div>

    <div id="templatemo_footer">

        <a href="https://www.linkedin.com/in/ivan-novik/"><strong>About me</strong></a>
    </div>

</div>
</body>
</html>