/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieunt.tbluser;

import hieunt.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;

/**
 *
 * @author HIEUNGUYEN
 */
public class UserDAO implements Serializable {

    public UserDTO getUserDTO(String username, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT userName, role, phone, address, createDate "
                    + "FROM tblUser "
                    + "WHERE userId = ? AND password = ? AND status = 1";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("userName");
                String role = rs.getString("role");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                Timestamp cd = rs.getTimestamp("createDate");
                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                String createDate = f.format(cd);
                return new UserDTO(username, name, password, role, phone, address, createDate);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

     public boolean checkDuplicateEmail(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT userId "
                        + "FROM tblUser "
                        + "WHERE userId = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean createNewAccount(UserDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblUser(userId, password, userName, status, role, phone, address, createDate)"
                        + " values(?, ?, ?, ?, ?, ? ,?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, dto.getUserID());
                ps.setString(2, dto.getPassword());
                ps.setString(3, dto.getName());
                ps.setString(5, dto.getRole());
                ps.setString(6, dto.getPhone());
                Date d = new Date();
                Timestamp date = new Timestamp(d.getTime());
                ps.setString(7, dto.getAddress());
                ps.setTimestamp(8, date);
                ps.setBoolean(4, true);
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

}
