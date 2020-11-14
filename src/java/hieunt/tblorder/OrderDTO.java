/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieunt.tblorder;

import java.io.Serializable;

/**
 *
 * @author HIEUNGUYEN
 */
public class OrderDTO implements Serializable{
    private String orderID;
    private String userID;
    private String checkoutDate;
    private float price;

    public OrderDTO() {
    }

    public OrderDTO(String orderID, String userID, String checkoutDate, float price) {
        this.orderID = orderID;
        this.userID = userID;
        this.checkoutDate = checkoutDate;
        this.price = price;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }
    
}
