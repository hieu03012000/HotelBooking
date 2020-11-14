/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieunt.tblorderdetail;

import hieunt.cart.RoomInCart;
import hieunt.util.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import javax.naming.NamingException;

/**
 *
 * @author HIEUNGUYEN
 */
public class OrderDetailDAO {
    public void addDetail(Map<String, RoomInCart> list, String oID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            Set<String> keys = list.keySet();
            for (String key : keys) {
                String sql = "INSERT INTO tblOrderDetail(orderID, roomID, quantity, checkinDate, checkoutDate)\n"
                        + "VALUES (?, ?, ? , ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, oID);
                ps.setString(2, key);
                ps.setInt(3, list.get(key).getQuantity());
                ps.setString(4, list.get(key).getCheckin());
                ps.setString(5, list.get(key).getCheckout());
                ps.executeUpdate();
            }

        } finally {

            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
