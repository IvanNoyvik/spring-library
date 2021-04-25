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

    <div id="admin_menu" style="display: flex; justify-content: space-between;">
        <c:if test="${sessionScope.user.status.status ne 'Locked'}">

            <ul>
                <!-- MAIN -->
                <c:url value="/test" var="main">
                    <c:param name="target" value="main"/>
                </c:url>
                <li><a href="<c:out value="${main}"/>" class="current">Main</a></li>

            </ul>


            <ul>
                <c:if test="${empty sessionScope.user}">
                    <!-- LOGIN -->
                    <c:url value="/test" var="login">
                        <c:param name="command" value="Forward"/>
                        <c:param name="forward" value="login"/>
                    </c:url>
                    <li><a href="<c:out value="${login}"/>" class="current">Login</a></li>

                    <!-- REGISTR -->
                    <c:url value="/test" var="registration">
                        <c:param name="command" value="Forward"/>
                        <c:param name="forward" value="registration"/>
                    </c:url>
                    <li><a href="<c:out value="${registration}"/>" class="current">Registration</a></li>
                </c:if>


                <c:if test="${!empty sessionScope.user}">

                    <!-- PROFILE -->
                    <c:url value="/test" var="profile">
                        <c:param name="command" value="Forward"/>
                        <c:param name="forward" value="profile"/>
                    </c:url>
                    <li><a href="<c:out value="${profile}"/>" class="current">Profile</a></li>

                    <!-- LOGOUT -->
                    <c:url value="/test" var="logout">
                        <c:param name="command" value="Logout"/>
                    </c:url>
                    <li><a href="<c:out value="${logout}"/>" class="current">Logout</a></li>


                </c:if>
            </ul>
        </c:if>
    </div>
    <div id="templatemo_header">
        <div id="templatemo_special_offers">
            <p>
                <%--                <c:if test="${!empty requestScope.resp and requestScope.resp != null}">--%>
                <%--                    <span class="resp"><c:out value="${requestScope.resp}"/></span>--%>
                <%--                </c:if>--%>
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

            <c:forEach items="${requestScope.books}" var="book">
                <c:out value="${book.id}"/>
                <c:out value="${book.title}"/>
                <c:out value="${book.author.author}"/>
            </c:forEach>

            <%--            <c:if test="${!empty requestScope.books}">--%>

            <%--                <c:forEach items="${requestScope.books}" var="book">--%>

            <%--                    <div class="templatemo_product_box">--%>

            <%--                        <h1>${book.title} <span>(${book.author.author})</span></h1>--%>

            <%--                        <c:url value="/front" var="image">--%>
            <%--                            <c:param name="bookId" value="${book.id}"/>--%>
            <%--                            <c:param name="command" value="GetImage"/>--%>
            <%--                        </c:url>--%>
            <%--                        <div>--%>
            <%--                            <img src="${image}" alt="CSS Template" width="100"--%>
            <%--                                 height="100"/>--%>
            <%--                        </div>--%>


            <%--                        <div class="product_info">--%>
            <%--                            <p>${book.description}</p>--%>
            <%--                            <c:if test="${book.quantity <= 0}">--%>
            <%--                                <h3>Not available</h3>--%>
            <%--                            </c:if>--%>
            <%--                            <c:if test="${(book.quantity > 0)}">--%>
            <%--                                <h3>${book.quantity} pcs in stock</h3>--%>
            <%--                                <c:if test="${!empty sessionScope.user and (sessionScope.user.status.status eq 'OK')}">--%>
            <%--                                    <form accept-charset="UTF-8" action="<c:url value="/front"/>" method="post">--%>
            <%--                                        <label>Duration--%>
            <%--                                            <input class="duration-main" name="days" type="text"--%>
            <%--                                                   required="" placeholder="(1-180)in days..."--%>
            <%--                                                   pattern="^0*[1-9]\d*$"/>--%>
            <%--                                        </label>--%>
            <%--                                        <input name="command" type="hidden" value="AddOrder"/>--%>
            <%--                                        <input name="bookId" type="hidden" value="${book.id}"/>--%>
            <%--                                        <input type="submit" value="Add in my library"/>--%>
            <%--                                    </form>--%>


            <%--                                </c:if>--%>
            <%--                            </c:if>--%>

            <%--                            <c:url value="/front" var="bookInfo">--%>
            <%--                                <c:param name="command" value="Forward"/>--%>
            <%--                                <c:param name="forward" value="book"/>--%>
            <%--                                <c:param name="bookId" value="${book.id}"/>--%>
            <%--                            </c:url>--%>
            <%--                            <div class="detail_button"><a href="<c:out value="${bookInfo}"/>">Detail</a></div>--%>


            <%--                        </div>--%>

            <%--                    </div>--%>

            <%--                </c:forEach>--%>

            <%--            </c:if>--%>

        </div>

        <div class="cleaner_with_height"></div>

    </div>

    <div id="templatemo_footer">

        <a href="https://www.linkedin.com/in/ivan-novik/"><strong>About me</strong></a>
    </div>
</div>
</body>
</html>