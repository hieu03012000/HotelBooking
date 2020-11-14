/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieunt.servlet;

import hieunt.tbluser.UserDAO;
import hieunt.tbluser.UserDTO;
import hieunt.util.PasswordEncryption;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author HIEUNGUYEN
 */
@WebServlet(name = "LoginAccountServlet", urlPatterns = {"/LoginAccountServlet"})
public class LoginAccountServlet extends HttpServlet {

    private final String HOME_PAGE = "SearchServlet";
    private final String LOGIN_PAGE = "login.jsp";
    private static final Logger log = LogManager.getLogger(AddToCartServlet.class);

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
        String url = LOGIN_PAGE;
        try {
            String username = request.getParameter("txtUsername");
            String pass = request.getParameter("txtPassword");
            HttpSession s = request.getSession();

            if (username != null && pass != null) {
                PasswordEncryption pe = new PasswordEncryption();
                String password = pe.toSHAString(pass);

                UserDAO dao = new UserDAO();
                UserDTO dto = dao.getUserDTO(username, password);
                if (dto != null) {
                    s.setAttribute("USER_DTO", dto);
                    s.setAttribute("INVALID", "");
                    url = HOME_PAGE;
                } else {
                    s.setAttribute("INVALID", "Invalid username or password");
                }
            }
        } catch (SQLException ex) {
            log.error("SQL_" + ex.getMessage());
        } catch (NamingException ex) {
            log.error("Naming_" + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            log.error("NoSuch_" + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
