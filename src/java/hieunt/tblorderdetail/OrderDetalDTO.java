/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieunt.tblorderdetail;

import java.io.Serializable;

/**
 *
 * @author HIEUNGUYEN
 */
public class OrderDetalDTO implements Serializable{
    private String orderDetailID;
    private String orderID;
    private String roomID;
    private String quantity;
    private String checkinDate;   
    private String checkoutDate;   

    public OrderDetalDTO() {
    }

    public OrderDetalDTO(String orderDetailID, String orderID, String roomID, String quantity, String checkinDate, String checkoutDate) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.roomID = roomID;
        this.quantity = quantity;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
    }

    public String getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }
    
    
}
