<%-- 
    Document   : showHotel
    Created on : Oct 26, 2020, 9:13:20 AM
    Author     : HIEUNGUYEN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />" rel="stylesheet">
        <title>Home Page</title>
        <style>
            <%@include file="css/mycss.css"%>
        </style>
    </head>
    <body class="text-center">
        <div class="row" style="padding-top: 50px">
            <div class="col-xl-8" style="margin: auto">
                <h1>Hotel Booking</h1>
                <c:if test="${empty sessionScope.USER_DTO}">
                    <a href="LoginAccountServlet">Login</a> <br/>
                    <a href="createNewAccount.jsp">Create new account</a>
                </c:if>
                <c:if test="${not empty sessionScope.USER_DTO}">
                    <c:set var="userDTO" value="${sessionScope.USER_DTO}"/>
                    <h1>Welcome ${userDTO.name}</h1>
                    <a href="LogoutServlet">Logout</a>
                    <c:if test="${userDTO.role!='AD'}">
                        <br/>
                        <a href="ShowCartServlet">Show your cart</a>
                    </c:if>
                    <c:if test="${userDTO.role=='AD'}">
                        <br/>
                        <a href="ceateDiscountCode.jsp">Create Discount</a>
                    </c:if>
                </c:if>
                <form action="SearchServlet" method="POST">
                    <div class="row">
                        <div class="col-md-6">
                            <p><strong>Hotel name:</strong></p>
                            <input type="text" name="txtHotelName" value="${param.txtHotelName}" class="form-control"/>
                        </div>
                        <div class="col-md-6">
                            <p><strong>Hotel area:</strong></p>
                            <input type="text" name="txtHotelArea" value="${param.txtHotelArea}" class="form-control"/>
                        </div>
                    </div>
                    <br/>
                    <div class="row">
                        <div class="col-md-5">
                            <p><strong>Check in date:</strong></p>
                            <input type="date" name="txtCheckInDate" value="${requestScope.CHECKIN}" class="form-control"/>
                        </div>
                        <div class="col-md-5">
                            <p><strong>Check out date:</strong></p>
                            <input type="date" name="txtCheckOutDate" value="${requestScope.CHECKOUT}" class="form-control"/>
                        </div>
                        <div class="col-md-2">
                            <p><strong>Room amount:</strong></p>
                            <input type="number" name="txtRoomAmount" value="${param.txtRoomAmount}" class="form-control" min="1"/>
                        </div>
                    </div>
                    <input type="submit" value="Search" name="btAction" class="btn"/>
                </form>
                <c:if test="${userDTO.role=='U'}">
                    <form action="GetDiscountServlet">
                        <p><strong>Get Discount:</strong></p>
                        <input type="text" name="txtID" value="" class="form-control"/>
                        <input type="submit" value="GET" name="btAction" class="btn"/>
                    </form>
                </c:if>

                <c:if test="${not empty requestScope.DATE_ERROR}">
                    <font color="red">Invalid input date</font>
                </c:if>
                <c:if test="${userDTO.role=='U'}">
                    <c:if test="${not empty requestScope.CREATE_SUCCESS}">
                        <font color="green">${requestScope.CREATE_SUCCESS}</font>
                    </c:if>
                </c:if>
                <c:if test="${not empty requestScope.SUCCESSFULL}">
                    <font color="green">${requestScope.SUCCESSFULL}</font>
                </c:if>
                <c:if test="${userDTO.role=='AD'}">
                    <c:if test="${not empty requestScope.CREATE_SUCCESS}">
                        <font color="green">${requestScope.CREATE_SUCCESS}</font>
                    </c:if>
                </c:if>
                <c:set var="list" value="${requestScope.LIST_HOTEL}"/>
                <c:if test="${not empty list}">
                    <div style="padding-top: 30px">
                        <c:forEach var="dto" items="${list}" >
                            <div style="border-top: double; padding : 20px 0px 20px 0px" class="row">
                                <div class="col-md-4">
                                    <div class="col-md-12"><img src="${dto.image}" style="width: 280px"/></div>
                                </div>
                                <div class="col-md-8">
                                    <div class="row">
                                        <div class="col-md-3"><p><strong>Hotel name</strong></p> </div>${dto.hotelName}
                                    </div>
                                    <div class="row">
                                        <div class="col-md-3"><p><strong>Address</strong></p> </div>${dto.address}
                                    </div>
                                    <form action="AddToCartServlet" method="POST">
                                        <div class="row" style="margin-left: -11px">
                                            <input type="hidden" name="txtHotelID" value=" ${dto.hotelID}" />

                                            <c:if test="${userDTO.role!='AD'}">
                                                <div class="col-md-6">
                                                    <p><strong>Check in date:</strong></p>
                                                    <input type="date" name="txtCheckInRoom" value="${requestScope.DATE}" class="form-control"/>
                                                </div>
                                                <div class="col-md-6">
                                                    <p><strong>Check out date:</strong></p>
                                                    <input type="date" name="txtCheckOutRoom" value="${requestScope.DATE}" class="form-control"/>
                                                    <br/>
                                                </div> 
                                                <div class="col-md-6">
                                                    <p><strong>Room type:</strong></p>
                                                    <select name="cbChooseRoom" class="form-control">
                                                        <c:set var="roomList" value="${dto.roomList}"/>
                                                        <c:forEach var="room" items="${roomList}">
                                                            <option value="${room.roomID}">${room.roomType}-${room.price}$</option>
                                                        </c:forEach>
                                                    </select> 
                                                </div>
                                                <div class="col-md-4">
                                                    <p><strong>Room quantity:</strong></p>
                                                    <input type="number" name="txtQuantity" value="1" class="form-control" min="1"/>
                                                </div>
                                                <c:if test="${not empty userDTO}">
                                                    <div class="col-md-1">
                                                        <input type="submit" value="Add" name="addToCart" class="btn" style="margin: auto"/>
                                                    </div>
                                                </c:if>
                                                <c:if test="${empty userDTO}">
                                                    <div class="col-md-1">
                                                        <a href="login.jsp" class="btn">Add</a>
                                                    </div>
                                                </c:if>
                                            </c:if>
                                        </div> 
                                    </form>
                                </div>
                            </div>

                        </c:forEach>
                    </div>
                </c:if>
                <c:if test="${empty list}">
                    Not Found
                </c:if>
            </div> 

        </div>
    </body>
</html>
