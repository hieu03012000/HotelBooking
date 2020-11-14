<%-- 
    Document   : createNewAccount
    Created on : Oct 30, 2020, 8:01:46 PM
    Author     : HIEUNGUYEN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account</title>
        <link href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />" rel="stylesheet">
        <style>
            <%@include file="css/mycss.css"%>
            body{
                background: grey;
            }
            form{
                width:  700px;
                border: 3px solid #f1f1f1;
                padding: 20px;
                left: 28%;
                top: 5%;
                position: absolute;
                background: white;
                border-radius: 10px;
            }
            .abs{
                position: absolute;
                left: 30px;
            }
        </style>
    </head>
    <body class="text-center">
        <form action="CreateNewAccountServlet" method="POST" class="form-signin">
            <h1><font color=3399FF>NEW ACCOUNT</font></h1>
            <br/>
            <c:set var="err" value="${requestScope.CREATE_ERROR}"/>
            <div class="col-xl-12">
                <p><strong>Username*</strong></p>
                <input type="text" class="form-control" placeholder="Username" required autofocus name="txtUsername">
                <c:if test="${not empty err.emailFormatError}">
                    <font color="red" class="col-md-6, abs">${err.emailFormatError}</font>
                </c:if>
                <c:if test="${empty err.emailFormatError}">
                    <c:if test="${not empty err.emailExistedError}">
                        <font color="red" class="abs">${err.emailExistedError}</font>
                    </c:if>
                </c:if>
            </div>
            <br/>
            <div class="row">
                <div class="col-xl-6">
                    <p><strong>Password*</strong></p>
                    <input type="password" class="form-control" placeholder="Password" required autofocus name="txtPassword">
                    <c:if test="${not empty err.passwordLengthError}">
                        <font color="red" class="abs">${err.passwordLengthError}</font>
                    </c:if>
                </div>
                <div class="col-xl-6">
                    <p><strong>Confirm password*</strong></p>
                    <input type="password" class="form-control" placeholder="Confirm password" required autofocus name="txtConfirm">
                    <c:if test="${not empty err.confirmNotMatch}">
                        <font color="red" class="abs">${err.confirmNotMatch}</font>
                    </c:if>
                </div>
            </div>
            <br/>
            <div class="col-xl-12">
                <p><strong>Name*</strong></p>
                <input type="text" class="form-control" placeholder="Name" required autofocus name="txtName">
                <c:if test="${not empty err.nameLengthError}">
                    <font color="red" class="abs">${err.nameLengthError}</font>
                </c:if>
            </div>
            <br/>   
            <div class="row">
                <div class="col-xl-6">
                    <p><strong>Phone*</strong></p>
                    <input type="text" class="form-control" placeholder="Phone Number" required autofocus name="txtPhone">
                    <c:if test="${not empty err.phoneError}">
                        <font color="red" class="abs">${err.phoneError}</font>
                    </c:if>
                </div>
                <div class="col-xl-6">
                    <p><strong>Address*</strong></p>
                    <input type="text" class="form-control" placeholder="Address" required autofocus name="txtAddress">
                    <c:if test="${not empty err.addLengthError}">
                        <font color="red" class="abs">${err.addLengthError}</font>
                    </c:if>
                </div>
            </div>
            <br/>
            <input type="submit" value="Create New Account" name="btAction" class="btn btn1"/>
            <input type="reset" value="Reset" class="btn"/>
        </form>
    </body>
</html>
