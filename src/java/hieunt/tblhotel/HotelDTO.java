/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieunt.tblhotel;

import hieunt.tblroom.RoomDTO;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author HIEUNGUYEN
 */
public class HotelDTO implements Serializable{
    private int hotelID;
    private String hotelName;
    private String address;
    private String image;
    private boolean avalable;
    private List<RoomDTO> roomList;

    public HotelDTO() {
    }

    public HotelDTO(int hotelID, String hotelName, String address, String image, boolean avalable, List<RoomDTO> roomList) {
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.address = address;
        this.image = image;
        this.avalable = avalable;
        this.roomList = roomList;
    }
    
    public HotelDTO(int hotelID, String hotelName, String address, String image, boolean avalable) {
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.address = address;
        this.image = image;
        this.avalable = avalable;
    }

    public List<RoomDTO> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<RoomDTO> roomList) {
        this.roomList = roomList;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAvalable() {
        return avalable;
    }

    public void setAvalable(boolean avalable) {
        this.avalable = avalable;
    }
    
    
}
