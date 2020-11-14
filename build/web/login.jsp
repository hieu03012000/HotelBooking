<%-- 
    Document   : login
    Created on : Oct 28, 2020, 11:23:48 AM
    Author     : HIEUNGUYEN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />" rel="stylesheet">
        <script src="https://www.google.com/recaptcha/api.js?render=6LelhtwZAAAAAGsG_K9EnD_NFl5gH1rpx_ifDciB"></script>

        <title>Login</title>
        <style>
            <%@include file="css/mycss.css"%>
            body{
                background: grey;
            }
            form{
                width:  350px;
                border: 3px solid #f1f1f1;
                padding: 20px;
                left: 38%;
                top: 15%;
                position: absolute;
                background: white;
                height: 475px;
                border-radius: 10px;
            }
        </style>
    </head>
    <body class="text-center">
        <form action="LoginAccountServlet" method="POST" class="form-signin">
            <h1>LOGIN</h1><br/>
            <p><strong> Username: </strong></p>
            <input type="text" class="form-control" placeholder="Username" required autofocus name="txtUsername">
            <br/>
            <p><strong> Password: </strong></p>
            <input type="password" class="form-control" placeholder="Password" required name="txtPassword">
            <br/>
            <input type="submit" value="Login" class="btn btn1"/>
            <input type="reset" value="Reset" class="btn" /> <br/>
            <a href="createNewAccount.jsp">Create new account</a>
            <br/>
            <script>
                function onClick(e) {
                    e.preventDefault();
                    grecaptcha.ready(function () {
                        grecaptcha.execute('6LelhtwZAAAAAGsG_K9EnD_NFl5gH1rpx_ifDciB', {action: 'submit'}).then(function (token) {
                            // Add your logic to submit to your backend server here.
                        });
                    });
                }
            </script>
            <c:if test="${not empty sessionScope.INVALID}">
                ${sessionScope.INVALID}
            </c:if>
        </form>
    </body>
</html>
