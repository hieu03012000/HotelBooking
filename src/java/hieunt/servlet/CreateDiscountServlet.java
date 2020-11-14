/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieunt.servlet;

import hieunt.tbldiscount.DiscountDAO;
import hieunt.tbluser.UserCreateError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "CreateDiscountServlet", urlPatterns = {"/CreateDiscountServlet"})
public class CreateDiscountServlet extends HttpServlet {

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
        String url = "SearchServlet";
        try {
            String id = request.getParameter("txtID");
            String name = request.getParameter("txtName");
            String p = request.getParameter("txtPercent");
            UserCreateError error = new UserCreateError();
            boolean foundErr = false;
            int percent = Integer.parseInt(p);
            DiscountDAO dDAO = new DiscountDAO();
            if (dDAO.checkDuplicate(id)) {
                foundErr = true;
                error.setEmailExistedError("Duplicate ID");
            }
            if (name.length() < 1 || name.length() > 50) {
                foundErr = true;
                error.setEmailFormatError("Name length from 1 to 50");
            } 
            if (name.length() < 6 || name.length() > 50) {
                foundErr = true;
                error.setPasswordLengthError("Name length from 6 to 50");
            } 
            if (percent > 100 || percent < 1) {
                foundErr = true;
                error.setPhoneError("From 1  to 100");
            }
            if(foundErr) {
                request.setAttribute("CREATE_ERROR", error);
                url = "ceateDiscountCode.jsp";
            } else {
                dDAO.addOrder(id, name, percent);
                request.setAttribute("CREATE_SUCCESS", "Create disount successfully");
            }
        } catch (SQLException ex) {
            log.error("SQL_" + ex.getMessage());
        } catch (NamingException ex) {
            log.error("Naming_" + ex.getMessage());
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
