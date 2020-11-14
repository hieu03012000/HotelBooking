/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieunt.tblhotel;

import hieunt.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author HIEUNGUYEN
 */
public class HotelDAO implements Serializable {

    public List<HotelDTO> getHotelList(String hotelName, String hotelArea, String checkIn, String checkOut, int amount) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<HotelDTO> list = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT hotelID, hotelName, address, image, available\n"
                    + "FROM tblHotel\n"
                    + "WHERE hotelName LIKE ? AND address LIKE ? AND available = 1 \n"
                    + "AND hotelID IN (SELECT r.hotelID \n"
                    + "FROM tblRoom r LEFT JOIN tblOrderDetail od ON r.roomID = od.roomID\n"
                    + "WHERE (r.quantity - ISNULL(od.quantity, 0)) >= ?\n"
                    + "AND NOT ? BETWEEN ISNULL(od.checkinDate, '1900-01-01') AND ISNULL(od.checkoutDate, '1900-01-01')\n"
                    + "AND NOT ? BETWEEN ISNULL(od.checkinDate, '1900-01-01') AND ISNULL(od.checkoutDate, '1900-01-01'))";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + hotelName + "%");
            ps.setString(2, "%" + hotelArea + "%");
            ps.setInt(3, amount);
            ps.setString(4, checkIn);
            ps.setString(5, checkOut);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                int productID = rs.getInt("hotelID");
                String name = rs.getString("hotelName");
                String address = rs.getString("address");
                String image = rs.getString("image");
                boolean available = rs.getBoolean("available");
                list.add(new HotelDTO(productID, name, address, image, available));
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
        return list;
    }

    public String getHotelName(int hotelID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT hotelName\n"
                    + "FROM tblHotel\n"
                    + "WHERE hotelID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, hotelID);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("hotelName");
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
}
