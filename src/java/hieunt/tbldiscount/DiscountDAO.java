/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieunt.tbldiscount;

import hieunt.util.DBHelper;
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
public class DiscountDAO {

    public void addOrder(String id, String name, int percen) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "INSERT INTO tblDisCount(codeID, codeName, discountPercen, dateCreate)\n"
                    + "VALUES ( ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            java.util.Date date = new java.util.Date();
            Timestamp d = new Timestamp(date.getTime());
            ps.setTimestamp(4, d);
            ps.setString(2, name);
            ps.setInt(3, percen);
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

    public boolean checkDuplicate(String codeID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT codeID "
                        + "FROM tblDisCount "
                        + "WHERE codeID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, codeID);
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

    public int getDiscount(String codeID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT discountPercen "
                        + "FROM tblDisCount "
                        + "WHERE codeID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, codeID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("discountPercen");
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
        return 0;
    }
}
