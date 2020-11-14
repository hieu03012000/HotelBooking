/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieunt.servlet;

import hieunt.tblhotel.HotelDAO;
import hieunt.tblhotel.HotelDTO;
import hieunt.tblroom.RoomDAO;
import hieunt.tblroom.RoomDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author HIEUNGUYEN
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

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
        String url = "showHotel.jsp";
        try {
            boolean err = false;
            String hotelName = "";
            if (request.getParameter("txtHotelName") != null) {
                hotelName = request.getParameter("txtHotelName");
            }
            String hotelArea = "";
            if (request.getParameter("txtHotelArea") != null) {
                hotelArea = request.getParameter("txtHotelArea");
            }

            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();

            String checkInDate;
            if (request.getParameter("txtCheckInDate") != null) {
                checkInDate = request.getParameter("txtCheckInDate");
            } else {
                checkInDate = formater.format(d);
            }
            request.setAttribute("CHECKIN", checkInDate);
            String checkOutDate;
            if (request.getParameter("txtCheckOutDate") != null) {
                checkOutDate = request.getParameter("txtCheckOutDate");
            } else {
                checkOutDate = formater.format(d);
            }
            request.setAttribute("CHECKOUT", checkOutDate);
            request.setAttribute("DATE", formater.format(d));

            Date date1 = formater.parse(checkInDate);
            Date date2 = formater.parse(checkOutDate);
            if (date1.compareTo(date2) > 0) {
                err = true;

            }
            if (err) {

            } else {
                String amountString = "";
                int amount = 1;
                if (request.getParameter("txtRoomAmount") != null) {
                    amountString = request.getParameter("txtRoomAmount");
                    try {
                        amount = Integer.parseInt(amountString);
                    } catch (Exception e) {
                        amount = 1;
                    }
                }
                HotelDAO dao = new HotelDAO();
                RoomDAO rDAO = new RoomDAO();
                List<HotelDTO> list = dao.getHotelList(hotelName, hotelArea, checkInDate, checkOutDate, amount);
                if (list != null) {
                    for (HotelDTO hotelDTO : list) {
                        List<RoomDTO> listRoom = rDAO.listRoomByHotelID(hotelDTO.getHotelID());
                        hotelDTO.setRoomList(listRoom);
                    }
                    request.setAttribute("LIST_HOTEL", list);
                }
            }
        } catch (SQLException ex) {
            log("SearchServlet_SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchServlet_Naming_" + ex.getMessage());
        } catch (ParseException ex) {
            log("SearchServlet_Parse_" + ex.getMessage());
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
