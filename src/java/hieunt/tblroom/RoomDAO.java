/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieunt.tblroom;

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
public class RoomDAO implements Serializable {

    public List<RoomDTO> listRoomByHotelID(int hotelID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RoomDTO> list = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT r.roomID, rt.typeName, r.price, r.quantity\n"
                    + "FROM tblRoom r JOIN tblRoomType rt ON r.typeID = rt.typeID \n"
                    + "WHERE r.hotelID = ? AND r.status = 1";
            ps = con.prepareStatement(sql);
            ps.setInt(1, hotelID);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                String roomID = rs.getString("roomID");
                String roomType = rs.getString("typeName");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                list.add(new RoomDTO(roomID, roomType, price, quantity, hotelID));
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

    public RoomDTO finRoomByID(String roomID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT r.roomID, rt.typeName, r.price, r.quantity, r.hotelID\n"
                    + "FROM tblRoom r JOIN tblRoomType rt ON r.typeID = rt.typeID \n"
                    + "WHERE r.roomID = ? AND r.status = 1";
            ps = con.prepareStatement(sql);
            ps.setString(1, roomID);
            rs = ps.executeQuery();
            if (rs.next()) {
                String roomType = rs.getString("typeName");
                int hotelID = rs.getInt("hotelID");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                return new RoomDTO(roomID, roomType, price, quantity, hotelID);
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

    public int getQuantityRoombyDate(String roomID, String checkin, String checkout) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT (quantity - ISNULL((SELECT SUM(quantity)\n"
                    + "FROM tblOrderDetail\n"
                    + "WHERE roomID = ? \n"
                    + "AND (? BETWEEN ISNULL(checkinDate, '1900-01-01') AND ISNULL(checkoutDate, '1900-01-01')\n"
                    + "OR ? BETWEEN ISNULL(checkinDate, '1900-01-01') AND ISNULL(checkoutDate, '1900-01-01'))\n"
                    + "GROUP BY roomID),0)) AS quantity\n"
                    + "FROM tblRoom\n"
                    + "WHERE roomID = ? AND status = 1";
            ps = con.prepareStatement(sql);
            ps.setString(1, roomID);
            ps.setString(2, checkin);
            ps.setString(3, checkout);
            ps.setString(4, roomID);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantity");
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
        return 0;
    }
}
