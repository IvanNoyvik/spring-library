<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Profile</title>
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
            <c:if test="${!empty sessionScope.user}">

                <ul>
                    <li>You login: ${sessionScope.user.authenticate.login}</li>
                    <li>You name: ${sessionScope.user.name}</li>
                    <li>You email: ${sessionScope.user.email}</li>
                </ul>

                <a href="<c:url value="/page/editUser" />" style="margin-left: 50px;">Edit profile...</a>
            </c:if>

        </div>
    </div>

    <div id="templatemo_content">

        <div id="templatemo_content_left">

            <div class="templatemo_content_left_section">
                <c:if test="${sessionScope.user.roles.contains(applicationScope.admin)}">
                    <div class="buy_now_button"><a class="buy_now_button"
                                                   href="<c:url value="/admin"/>">Admin panel</a>
                    </div>
                </c:if>
            </div>

            <div class="templatemo_content_left_section"></div>

        </div>

        <div id="templatemo_content_right">

            <c:if test="${!empty requestScope.orders}">

                <c:forEach items="${requestScope.orders}" var="order">

                    <div class="templatemo_product_box">


                        <h1>${order.book.title} (${order.book.author.author}) </h1>

                        <div>
                            <c:choose>
                                <c:when test="${!empty order.book.image}">
                                    <img src="<c:url value="/getImage/${order.book.id}"/>" alt="CSS Template"
                                         width="100"
                                         height="100"/>
                                </c:when>
                                <c:otherwise>
                                    <img src="<c:url value="/static/main/images/no_image.png"/>" alt="CSS Template"
                                         width="100"
                                         height="100"/>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <div class="product_info">
                            <p>${order.book.description}</p>

                            <c:if test="${applicationScope.now.now() >= order.dateReceiving.plusDays(order.duration)}">
                                <h3>Book is expired
                                    return the book to the library</h3>


                            </c:if>
                            <c:if test="${applicationScope.now.now() < order.dateReceiving.plusDays(order.duration)}">
                                <h3> Expected return date: ${order.dateReceiving.plusDays(order.duration)} </h3>
                                <h3>${order.dateReceiving.plusDays(order.duration).toEpochDay() - applicationScope.now.now().toEpochDay()}
                                    days
                                    left </h3>


                                <div class="buy_now_button"><a class="buy_now_button"
                                                               href="<c:url value="/bookContent/${order.book.id}"/>">Read</a>
                                </div>

                            </c:if>

                                <%--                            <div class="detail_button">--%>
                                <%--                                <a--%>
                                <%--                                &lt;%&ndash;                                        todo ReturnOrder&ndash;%&gt;--%>
                                <%--                                    href="<c:url value="/returnOrder?id=${order.id}"/>">Return</a>--%>
                                <%--                            </div>--%>
                            <div class="detail_button">
                                <form action="<c:url value="/returnOrder"/>" method="post">
                                    <input type="hidden" value="${order.id}" name="id"/>
                                    <input type="hidden" value="${order.book.id}" name="bookId"/>
                                    <input type="submit" value="Return"/>
                                </form>
                            </div>
                        </div>
                    </div>

                </c:forEach>

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