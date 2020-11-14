/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieunt.servlet;

import hieunt.cart.CartObj;
import hieunt.tblhotel.HotelDAO;
import hieunt.tblroom.RoomDAO;
import hieunt.tblroom.RoomDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author HIEUNGUYEN
 */
@WebServlet(name = "UpdateCartServlet", urlPatterns = {"/UpdateCartServlet"})
public class UpdateCartServlet extends HttpServlet {

        private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(AddToCartServlet.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = "showCart.jsp";
        try {
            String roomID = request.getParameter("txtRoomID");
            int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            RoomDAO rDAO = new RoomDAO();
            RoomDTO room = rDAO.finRoomByID(roomID);
            HotelDAO hDAO = new HotelDAO();
            String hotelName = hDAO.getHotelName(room.getHotelID());
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();

            String checkInDate;
            if (request.getParameter("txtCheckInRoom") != null) {
                checkInDate = request.getParameter("txtCheckInRoom");
            } else {
                checkInDate = formater.format(d);
            }
            request.setAttribute("CHECKIN", checkInDate);
            String checkOutDate;
            if (request.getParameter("txtCheckOutRoom") != null) {
                checkOutDate = request.getParameter("txtCheckOutRoom");
            } else {
                checkOutDate = formater.format(d);
            }
            request.setAttribute("CHECKOUT", checkOutDate);

            Date date1 = formater.parse(checkInDate);
            Date date2 = formater.parse(checkOutDate);
            if (date1.compareTo(date2) <= 0) {
                int day = (int) (date2.getTime() - date1.getTime());
                HttpSession s = request.getSession();
                CartObj cart = (CartObj) s.getAttribute("CART");
                if (cart == null) {
                    cart = new CartObj();
                }
                cart.updateItem(roomID, room.getRoomType(), quantity, room.getPrice(), hotelName, checkInDate, checkOutDate, (day/86400000) + 1);
                s.setAttribute("CART", cart);
            } else {
                request.setAttribute("DATE_ERROR", "Can not book");
            }
        } catch (SQLException ex) {
            log("UpdateCartServlet_SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateCartServlet_Naming_" + ex.getMessage());
        } catch (ParseException ex) {
            log("UpdateCartServlet_Parse_" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
