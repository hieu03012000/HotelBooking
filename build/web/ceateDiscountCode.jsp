<%-- 
    Document   : bookingHistory
    Created on : Nov 7, 2020, 1:46:10 AM
    Author     : HIEUNGUYEN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Discount</title>
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
        <form action="CreateDiscountServlet" method="POST" class="form-signin">
            <h1><font color=3399FF>Create DisCount</font></h1>
            <br/>
            <c:set var="err" value="${requestScope.CREATE_ERROR}"/>
            <div class="col-xl-12">
                <p><strong>Discount ID*</strong></p>
                <input type="text" class="form-control" placeholder="ID" required autofocus name="txtID">
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
            <div class="col-xl-12">
                <p><strong>Discount name*</strong></p>
                <input type="text" class="form-control" placeholder="Name" required autofocus name="txtName">
                <c:if test="${not empty err.passwordLengthError}">
                    <font color="red" class="abs">${err.passwordLengthError}</font>
                </c:if>
            </div><br/>
            <div class="col-md-12">
                <p><strong>Discount Percent:</strong></p>
                <input type="number" name="txtPercent" value="${param.txtRoomAmount}" class="form-control" min="1"/>
            </div>
            <c:if test="${not empty err.phoneError}">
                <font color="red" class="abs">${err.phoneError}</font>
            </c:if>
            <br/>
            <input type="submit" value="Create" name="btAction" class="btn btn1"/>
            <input type="reset" value="Reset" class="btn"/>
        </form>
    </body>
</html>

