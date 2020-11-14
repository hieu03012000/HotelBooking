<%-- 
    Document   : showCart
    Created on : Nov 5, 2020, 1:05:05 AM
    Author     : HIEUNGUYEN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Cart</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <style>
            <%@include file="css/mycss.css"%>
        </style>
    </head>
    <body class="text-center">
        <c:if test="${not empty sessionScope.USER_DTO}">
            <c:set var="UserDTO" value="${sessionScope.USER_DTO}"/>
            <div class="row" style="padding-top: 50px">
                <div class="col-xl-8" style="margin: auto">
                    <h1>Your cart</h1>
                    <h1>${userDTO.name}</h1>
                    <a href="LogoutServlet">Logout</a> <br/>
                    <a href="SearchServlet">Back</a><br/>
                    <c:if test="${not empty requestScope.DATE_ERROR}">
                        <font color="red">Invalid input date</font>
                    </c:if>
                    <c:if test="${not empty sessionScope.DISCOUNT}">
                        <h4>You have ${sessionScope.DISCOUNT}% discount</h4>
                    </c:if>
                    <c:set var="cart" value="${sessionScope.CART}"/>
                    <c:if test="${not empty cart}">
                        <c:set var="list" value="${cart.roomList}"/>
                        <c:if test="${not empty list}">
                            <div style="padding-top: 30px">
                                <c:forEach var="cartDetail" items="${list}" varStatus="counter">
                                    <div class="row" style="border-top: double; padding: 20px 0px 20px 0px">
                                        <div class="col-xl-10">
                                            <div class="row">
                                                <div class="col-md-1"><p><strong>${counter.count}</strong></p></div>
                                                            <c:set var="dto" value="${cartDetail.value}"/>
                                                <div class="col-md-2"><p><strong>Hotel name:</strong></p></div>
                                                <div class="col-md-4">${dto.hotelName}</div>
                                                <div class="col-md-2"><p><strong>Room type:</strong></p></div>
                                                <div class="col-md-3">${dto.name}</div>
                                                <form action="UpdateCartServlet" method="POST" style="margin-left: 50px" class="col-md-12">
                                                    <input type="hidden" name="txtRoomID" value="${cartDetail.key}" />
                                                    <c:url var="url" value="RemoveRoomServlet">
                                                        <c:param name="txtRoomID" value="${cartDetail.key}"/>
                                                    </c:url>
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <p><strong>Check in date:</strong></p>
                                                            <input type="date" name="txtCheckInRoom" value="${dto.checkin}" class="form-control"/>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <p><strong>Check out date:</strong></p>
                                                            <input type="date" name="txtCheckOutRoom" value="${dto.checkout}" class="form-control"/>
                                                            <br/>
                                                        </div>
                                                        <div class="col-md-3"><p><strong>Room amount:</strong></p></div>
                                                        <div class="col-md-2">
                                                            <input type="number" name="txtQuantity" value="${dto.quantity}" class="form-control" min="1"/>
                                                        </div>
                                                        <div class="col-md-3"><p style="text-align: right"><strong>Price:</strong></p></div>
                                                        <div class="col-md-2">${dto.price}</div>
                                                        <div class="col-md-1">
                                                            <input type="submit" value="Update" name="addToCart" class="btn" style="margin: auto"/>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>  
                                        </div>
                                        <div class="col-2">
                                            <div class="col-md-1">
                                                <input type="submit" value="Remove" name="remove" class="btn btn1" data-toggle="modal" data-target="#exampleModalCenter" style="margin: auto"/>
                                            </div>
                                            <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered" role="document">
                                                    <div class="modal-content">
                                                        <div class="form-group">
                                                            <br/>
                                                            Do you want to remove
                                                        </div>
                                                        <div class="modal-footer">
                                                            <input type="reset" value="Close" class="btn" data-dismiss="modal"/>
                                                            <a href="${url}" class="btn">Yes</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>   
                                        </div>

                                    </div>
                                </c:forEach>
                            </div>
                            <h2>${cart.total}</h2>
                            <a href="CheckOutServlet" class="btn">Confirm</a> <br/>
                            <c:if test="${not empty requestScope.CHECKOUT_ERROR}">
                                <font color="red"> The number ${requestScope.CHECKOUT_ERROR} is out of stock</font>
                            </c:if>
                        </c:if>
                        <c:if test="${empty list}">
                            <h2>Nothing in your cart</h2>
                        </c:if>
                    </c:if>
                    <c:if test="${empty cart}">
                        <h2>Nothing in your cart</h2>
                    </c:if>
                </div>
            </div>
        </c:if>
    </body>
</html>
