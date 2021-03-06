<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Online library</title>
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
                <c:if test="${!empty answer and answer != null}">
                    <span class="answer"><c:out value="${answer}"/></span>
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
            <div class="templatemo_content_left_section">

            </div>


        </div>

        <div id="templatemo_content_right">

            <c:if test="${!empty requestScope.books}">

                <c:forEach items="${requestScope.books}" var="book">

                    <div class="templatemo_product_box">

                        <h1>${book.title} <span>(${book.author.author})</span></h1>

                        <div>
                            <c:choose>
                                <c:when test="${!empty book.image}">
                                    <img src="<c:url value="/getImage/${book.id}"/>" alt="CSS Template" width="100"
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
                            <p>${book.description}</p>
                            <c:if test="${book.quantity <= 0}">
                                <h3>Not available</h3>
                            </c:if>
                            <c:if test="${(book.quantity > 0)}">
                                <h3>${book.quantity} pcs in stock</h3>
                                <c:if test="${!empty sessionScope.user and (sessionScope.user.status.status eq 'OK')}">
                                    <form accept-charset="UTF-8" action="<c:url value="/getOrder"/>" method="post">
                                        <label>Duration
                                            <input class="duration-main" name="duration" type="text"
                                                   required="" placeholder="(1-180)in days..."
                                                   pattern="^0*[1-9]\d*$"/>
                                        </label>
                                        <input name="book.id" type="hidden" value="${book.id}"/>
                                        <input name="user.id" type="hidden" value="${sessionScope.user.id}"/>
                                        <input type="submit" value="Add in my library"/>
                                    </form>


                                </c:if>
                            </c:if>

                            <div class="detail_button"><a href="<c:url value="/book/${book.id}"/>">Detail</a></div>


                        </div>

                    </div>

                </c:forEach>

                <c:forEach var="page" begin="1" end="${requestScope.countPage}">
                    <a href="<c:url value="/main/${page-1}"/>">${page}</a>
                </c:forEach>

            </c:if>

        </div>

        <div class="cleaner_with_height"></div>

    </div>

    <div id="templatemo_footer">

        <a href="https://www.linkedin.com/in/ivan-novik/"><strong>About me</strong></a>
    </div>
</div>
</body>
</html>