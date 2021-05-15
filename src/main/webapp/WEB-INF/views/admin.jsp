<!DOCTYPE html<%-- PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"--%>>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Admin panel</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link href="<c:url value="/static/admin/admin.css" />" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="admin_container">


    <c:import url="head.jsp"/>

    <div id="admin_header">
        <div id="admin_special_offers">
            <p>
                <c:if test="${!empty requestScope.answer}">
                    <span class="answer"><c:out value="${requestScope.answer}"/></span>
                </c:if>
            </p>
        </div>


        <div id="admin_new_books">
            <ul>
                <li>
                    <form accept-charset="UTF-8" action="<c:url value="/addGenre"/>" method="post">
                        <label>Enter genre<br/>
                            <input name="genre" type="text" class="genre-inp"
                                   required="" placeholder="Enter genre..."/>
                        </label><br/>
                        <input type="submit" value="Add genre"/>
                    </form>
                </li>
                <li>
                    <form accept-charset="UTF-8" action="<c:url value="/addAuthor"/>" method="post">
                        <label>Enter author<br/>
                            <input name="author" type="text" class="author-inp"
                                   required="" placeholder="Enter author..."/>
                        </label><br/>
                        <input type="submit" value="Add author"/>
                    </form>
                </li>
                <li>
                    <c:if test="${sessionScope.user.roles.contains(applicationScope.admin)}">
                        <div class="buy_now_button"><a class="buy_now_button"
                                                       href="<c:url value="/addBook"/>">Add Book</a>
                        </div>
                    </c:if>
                </li>
            </ul>
        </div>
    </div>

    <div id="admin_content">

        <div id="admin_content_left">
            <div class="admin_content_left_section">
                <h1>Overdue orders</h1>
                <c:if test="${!empty requestScope.orders && sessionScope.user.roles.contains(applicationScope.admin)}">
                    <c:forEach items="${requestScope.orders}" var="order">

                        <div class="admin_product_box">

                            <span style="font-size:13px; font-style: italic">-- ${order.user.authenticate.login} (${order.user.name})</span>

                            Overdue the book <span style="font-style: italic">(${order.book.title})</span> by
                                ${applicationScope.now.now().toEpochDay() - order.dateReceiving.plusDays(order.duration).toEpochDay()}
                            days

                            <div class="product_info">
                                <c:if test="${!order.user.status.status.equalsIgnoreCase('Limited') and order.user.id ne sessionScope.user.id}">
                                    <form accept-charset="UTF-8" action="<c:url value="/сhangeStatus"/>" method="post">
                                        <label>Duration
                                            <input name="duration" type="text" class="duration"
                                                   required="" placeholder="in days..." pattern="^0*[1-9]\d*$"/>
                                        </label>
                                        <input name="userId" type="hidden" value="${order.user.id}"/>
                                        <input name="status" type="hidden" value="Limited"/>
                                        <input class="submit-limit" type="submit" value="Limited"/>
                                    </form>
                                </c:if>
                                <c:if test="${!order.user.status.status.equalsIgnoreCase('Locked') and order.user.id ne sessionScope.user.id}">
                                    <form accept-charset="UTF-8" action="<c:url value="/сhangeStatus"/>" method="post">
                                        <input name="userId" type="hidden" value="${order.user.id}"/>
                                        <input name="status" type="hidden" value="Locked"/>
                                        <input class="submit-lock" type="submit" value="Locked"/>
                                    </form>
                                </c:if>

                            </div>

                        </div>

                    </c:forEach>

                </c:if>
            </div>
            <div class="admin_content_left_section">
                <h1>Messages</h1>
                <c:if test="${!empty requestScope.messages}">
                    <c:forEach items="${requestScope.messages}" var="mess">
                        <div class="admin_product_box">
                            <span style="font-size:14px; font-style: italic">-- From: ${mess.user.authenticate.login} (${mess.user.name}) ${mess.dateSent}</span>
                            <div class="product_info">
                                    ${mess.content}
                            </div>
                            <c:if test="${mess.user.status.status.equals('Locked')}">
                                <form accept-charset="UTF-8" action="<c:url value="/сhangeStatus"/>" method="post">
                                    <input name="userId" type="hidden" value="${mess.user.id}"/>
                                    <input name="status" type="hidden" value="OK"/>
                                    <input class="submit-unlock" type="submit" value="Unlocked"/>
                                </form>
                            </c:if>
                        </div>

                    </c:forEach>
                </c:if>

            </div>


        </div>

        <div id="admin_content_right">
            <h1>Users</h1>

            <c:if test="${!empty requestScope.users}">
                <table>
                    <tr>
                        <th><h2>Login</h2></th>
                        <th class="table-width"><h2>User Status</h2></th>
                        <th class="table-width"><h2>Count Overdue Order</h2></th>
                        <th class="table-width" STYLE="text-align: right"><h2> Change</h2></th>
                        <th class="table-width" STYLE="text-align: left"><h2> Status </h2></th>
                        <th class="table-width"><h2>Delete User</h2></th>

                    </tr>
                    <c:forEach items="${requestScope.users}" var="user">
                        <c:if test="${user.id ne sessionScope.user.id}">
                            <tr>
                                <td><h3>${user.authenticate.login}</h3></td>
                                <td class="table-text-center"><h3>${user.status.status}</h3></td>
                                <td class="table-text-center"><h3>${user.countOverdueOrder}</h3></td>

                                <td class="table-text-center">
                                    <c:choose>
                                        <c:when test="${user.status.status.equalsIgnoreCase('Locked')
                                                    or user.status.status.equalsIgnoreCase('Limited')}">
                                            <h3>
                                                <form accept-charset="UTF-8" action="<c:url value="/сhangeStatus"/>"
                                                      method="post">
                                                    <input name="userId" type="hidden" value="${user.id}"/>
                                                    <input name="status" type="hidden" value="OK"/>
                                                    <input class="submit-unlock" type="submit" value="Unlocked"/>
                                                </form>
                                            </h3>
                                        </c:when>
                                        <c:otherwise>
                                            <h3>
                                                <form accept-charset="UTF-8" action="<c:url value="/сhangeStatus"/>"
                                                      method="post">
                                                    <input name="userId" type="hidden" value="${user.id}"/>
                                                    <input name="status" type="hidden" value="Locked"/>
                                                    <input class="submit-lock" type="submit" value="Locked"/>
                                                </form>
                                            </h3>
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                                <td class="table-text-center">
                                    <c:choose>
                                        <c:when test="${user.status.status.equalsIgnoreCase('Locked')
                                                        or user.status.status.equalsIgnoreCase('OK')}">
                                            <h3>
                                                <form accept-charset="UTF-8" action="<c:url value="/сhangeStatus"/>"
                                                      method="post">
                                                    <label>
                                                        <input class="duration" name="duration" type="text"
                                                               required="" placeholder="in days..."
                                                               pattern="^0*[1-9]\d*$"/>
                                                    </label>
                                                    <input name="userId" type="hidden" value="${user.id}"/>
                                                    <input name="status" type="hidden" value="Limited"/>
                                                    <input class="submit-limit" type="submit" value="Limited"/>
                                                </form>
                                            </h3>
                                        </c:when>
                                        <c:otherwise>
                                            <h3>
                                                <form accept-charset="UTF-8" action="<c:url value="/сhangeStatus"/>"
                                                      method="post">
                                                    <input name="userId" type="hidden" value="${user.id}"/>
                                                    <input name="status" type="hidden" value="Locked"/>
                                                    <input class="submit-lock" type="submit" value="Locked"/>
                                                </form>
                                            </h3>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="table-text-center">
                                    <h3>
                                        <form accept-charset="UTF-8" action="<c:url value="/deleteUser"/>"
                                              method="post">
                                            <input name="id" type="hidden" value="${user.id}"/>
                                            <input class="submit-delete" type="submit" value="Delete"/>
                                        </form>
                                    </h3>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>

                </table>

            </c:if>


        </div>

        <div class="cleaner_with_height">&nbsp;</div>
    </div>

    <div id="admin_footer">

        <a href="https://www.linkedin.com/in/ivan-novik/"><strong>About me</strong></a>
    </div>
</div>

</body>
</html>