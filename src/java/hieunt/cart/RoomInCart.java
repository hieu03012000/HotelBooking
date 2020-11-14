/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieunt.cart;

/**
 *
 * @author HIEUNGUYEN
 */
public class RoomInCart {
    private String name;
    private int quantity;
    private float price;
    private String hotelName;
    private String checkin;
    private String checkout;

    public RoomInCart() {
    }

    public RoomInCart(String name, int quantity, float price, String hotelName, String checkin, String checkout) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.hotelName = hotelName;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
    
    
}
