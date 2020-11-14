/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieunt.tblroom;

import java.io.Serializable;

/**
 *
 * @author HIEUNGUYEN
 */
public class RoomDTO implements Serializable{
    private String roomID;
    private String roomType;
    private float price;
    private int quantity;
    private int hotelID;

    public RoomDTO() {
    }

    public RoomDTO(String roomID, String roomType, float price, int quantity, int hotelID) {
        this.roomID = roomID;
        this.roomType = roomType;
        this.price = price;
        this.quantity = quantity;
        this.hotelID = hotelID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }
    
    
}
