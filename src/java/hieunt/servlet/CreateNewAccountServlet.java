/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieunt.servlet;

import hieunt.util.PasswordEncryption;
import hieunt.tbluser.UserCreateError;
import hieunt.tbluser.UserDAO;
import hieunt.tbluser.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {

    private final String NEW_ACCOUNT_PAGE = "createNewAccount.jsp";
    private final String SEARCH_PAGE = "SearchServlet";
    
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
        String url = NEW_ACCOUNT_PAGE;
        String email = request.getParameter("txtUsername");
        String pass = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String name = request.getParameter("txtName");
        String phone = request.getParameter("txtPhone");
        String address = request.getParameter("txtAddress");

        UserCreateError error = new UserCreateError();
        boolean foundErr = false;
        try {
            UserDAO dao = new UserDAO();
            // "\\w+@\\w+[.]\\w+"
            // "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"
            if (!email.matches("\\w+@\\w+[.]\\w+")) {
                foundErr = true;
                error.setEmailFormatError("Email format: acb@example.com");
            }
            if (pass.length() < 6 || pass.length() > 50) {
                foundErr = true;
                error.setPasswordLengthError("Password length from 6 to 50");
            } else if (!confirm.equals(pass)) {
                foundErr = true;
                error.setConfirmNotMatch("Not match with password");
            }
            if (name.length() < 3 || name.length() > 50) {
                foundErr = true;
                error.setNameLengthError("Name length from 3 to 50");
            }
            if (dao.checkDuplicateEmail(email)) {
                foundErr = true;
                error.setEmailExistedError("Email have alredy access");
            }
            if(!phone.matches("(\\d{4}-)?\\d{3}-\\d{3}")) {
                foundErr = true;
                error.setPhoneError("Phone number format is xxxx-xxx-xxx");
            }
            if(address.length() < 3 || address.length() > 80) {
                foundErr = true;
                error.setAddLengthError("Name length from 3 to 80");
            }
            
            if (foundErr) {
                request.setAttribute("CREATE_ERROR", error);
            } else {
                PasswordEncryption pe = new PasswordEncryption();
                String password = pe.toSHAString(pass);
                UserDTO dto = new UserDTO(email, name, password, "U", phone, address, "");
                boolean result = dao.createNewAccount(dto);
                if(result) {
                    url = SEARCH_PAGE;
                    request.setAttribute("SUCCESSFULL", "Create success");
                }
            }
        } catch (SQLException ex) {
            log("CreateNewAccount_SQL_" + ex.getMessage());
        } catch (NamingException ex) {
            log("CreateNewAccount_Naming_" + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            log("CreateNewAccount_NoSuch_" + ex.getMessage());
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
