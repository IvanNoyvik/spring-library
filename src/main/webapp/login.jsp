<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="<c:url value="static/login/images/icons/favicon.ico" />"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="<c:url value="static/login/vendor/bootstrap/css/bootstrap.min.css" />"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="<c:url value="static/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css" />"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="<c:url value="static/login/fonts/Linearicons-Free-v1.0.0/icon-font.min.css" />"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value="static/login/vendor/animate/animate.css" />"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="<c:url value="static/login/vendor/css-hamburgers/hamburgers.min.css" />"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="<c:url value="static/login/vendor/animsition/css/animsition.min.css" />"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value="static/login/vendor/select2/select2.min.css" />"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="<c:url value="static/login/vendor/daterangepicker/daterangepicker.css" />"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/login/css/util.css" />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/login/css/main.css" />"/>
    <!--===============================================================================================-->

    <title>Login</title>

</head>

<%--<c:import url="/WEB-INF/jsp/head.jsp" />--%>

<%--<br>--%>
<%--<form action="/front" method="post">--%>
<%--  <p> <input name="login" type="text"/> </p>   <br />--%>
<%--    <p>  <input name="password" type="password"/> </p><br />--%>
<%--    <p>  <input name="name" type="text"/> </p><br />--%>
<%--    <input name="command" type="hidden" value="Registration" datafld="Registration" />--%>
<%--    <p> <input type="submit" value="Registration"/></p>--%>
<%--</form>--%>


<body style="background-color: #d7cece;">

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <form class="login100-form validate-form" action="/front" method="post">
					<span class="login100-form-title p-b-43">
						Login to continue
					</span>
                <input type="hidden" value="Login" name="command">


                <div class="wrap-input100 validate-input" data-validate="Valid email is required: ex@abc.xyz">
                    <input class="input100" type="text" name="login" pattern="[A-Za-zА-Яа-яЁё0-9]{3,20}">
                    <span class="focus-input100"></span>
                    <span class="label-input100">Login</span>
                </div>


                <div class="wrap-input100 validate-input" data-validate="Password is required">
                    <input class="input100" type="password" name="password" pattern="[A-Za-zА-Яа-яЁё0-9]{1,40}">
                    <span class="focus-input100"></span>
                    <span class="label-input100">Password</span>
                </div>


                <div class="container-login100-form-btn">
                    <input class="login100-form-btn" type="submit" value="Login">
                </div>

                <div class="text-center p-t-46 p-b-20">
                    <c:if test="${!empty requestScope.resp}">
                        <span class="error"><c:out value="${requestScope.resp}"/></span>
                    </c:if>
                </div>

                <div class="login100-form-social flex-c-m">

                    <c:url value="/front" var="registration">
                        <c:param name="command" value="Forward"/>
                        <c:param name="forward" value="registration"/>
                    </c:url>
                    <li><a href="<c:out value="${registration}"/>" class="login100-form-social-item flex-c-m bg1 m-r-5">Registration</a>
                    </li>

                    <c:url value="/front" var="back">
                        <c:param name="command" value="Forward"/>
                        <c:param name="forward" value="main"/>
                    </c:url>
                    <li><a href="<c:out value="${back}"/>" class="login100-form-social-item flex-c-m bg2 m-r-5">Back to
                        menu</a></li>
                </div>
            </form>


            <div class="login100-more" style="background-image: url('static/login/images/bg-01.jpg');">
            </div>
        </div>
    </div>
</div>


<!--===============================================================================================-->
<script src="<c:url value="static/login/vendor/jquery/jquery-3.2.1.min.js"/>"></script>
<!--===============================================================================================-->
<script src="<c:url value="static/login/vendor/animsition/js/animsition.min.js"/>"></script>
<!--===============================================================================================-->
<script src="<c:url value="static/login/vendor/bootstrap/js/popper.js"/>"></script>
<script src="<c:url value="static/login/vendor/bootstrap/js/bootstrap.min.js"/>"></script>
<!--===============================================================================================-->
<script src="<c:url value="static/login/vendor/select2/select2.min.js"/>"></script>
<!--===============================================================================================-->
<script src="<c:url value="static/login/vendor/daterangepicker/moment.min.js"/>"></script>
<script src="<c:url value="static/login/vendor/daterangepicker/daterangepicker.js"/>"></script>
<!--===============================================================================================-->
<script src="<c:url value="static/login/vendor/countdowntime/countdowntime.js"/>"></script>
<!--===============================================================================================-->
<script src="<c:url value="static/login/js/main.js"/>"></script>

</body>
</html>

