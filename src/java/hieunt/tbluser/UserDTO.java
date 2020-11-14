/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieunt.tbluser;

import java.io.Serializable;

/**
 *
 * @author HIEUNGUYEN
 */
public class UserDTO implements Serializable{
    private String userID;
    private String name;
    private String password;
    private String role;
    private String phone;
    private String address;
    private String createDate;
    

    public UserDTO() {
    }

    public UserDTO(String userID, String name, String password, String role, String phone, String address, String createDate) {
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.address = address;
        this.createDate = createDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }


    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    
}
