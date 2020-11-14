package hieunt.cart;

import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author HIEUNGUYEN
 */
public class CartObj {

    private Map<String, RoomInCart> roomList;
    private float total;

    public Map<String, RoomInCart> getRoomList() {
        return roomList;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(int discount) {
        this.total = total * ((float)(100 - discount) / 100);
    }
    
    public void updateItem(String id, String name, int quantity, float price, String hotelName, String checkin, String checkout,int time) {
        if (this.roomList == null) {
            this.roomList = new HashMap<>();
            this.total = 0;
        }
        RoomInCart room = new RoomInCart(name, quantity, price * quantity * time, hotelName, checkin, checkout);
        if (this.roomList.containsKey(id)) {
                total = total - this.getRoomList().get(id).getPrice() + room.getPrice();
        } else {
            total += quantity * price;
        }
        this.roomList.put(id, room);
    }
    
    public void addItem(String id, String name, int quantity, float price, String hotelName, String checkin, String checkout, int time) {
        if (this.roomList == null) {
            this.roomList = new HashMap<>();
            this.total = 0;
        }
        RoomInCart room = new RoomInCart(name, quantity, price * quantity * time, hotelName, checkin, checkout);
        if (this.roomList.containsKey(id)) {
            room.setQuantity(this.roomList.get(id).getQuantity() + quantity);
            room.setPrice(room.getQuantity() * price * time);
            total = total - this.getRoomList().get(id).getPrice() + room.getQuantity() * price * time;
        } else {
            total += quantity * price * time;
        }
        this.roomList.put(id, room);
    }
    
    public void removeItem(String id) {
        if (this.roomList == null) {
            total = 0;
            return;
        }
        if (this.roomList.containsKey(id)) {
            float p = this.roomList.get(id).getPrice();
            this.total -= p;
            this.roomList.remove(id);
            if (this.roomList.isEmpty()) {
                this.roomList = null;
            }
        }
    }
    
}
