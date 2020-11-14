/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieunt.tblorder;

import hieunt.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.naming.NamingException;

/**
 *
 * @author HIEUNGUYEN
 */
public class OrderDAO implements Serializable {

    public void addOrder(OrderDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "INSERT INTO tblOrder(userID, checkout, price)\n"
                    + "VALUES ( ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, dto.getUserID());
            java.util.Date date = new java.util.Date();
            Timestamp d = new Timestamp(date.getTime());
            ps.setTimestamp(2, d);
            ps.setFloat(3, dto.getPrice());
            ps.executeUpdate();
        } finally {

            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public String getNewestOrderID(String userID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT TOP 1 orderID FROM tblOrder\n"
                    + "WHERE userID = ?\n"
                    + "ORDER BY checkout DESC";
            ps = con.prepareStatement(sql);
            ps.setString(1, userID);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("orderID");
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
        return "";
    }

}
