/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.StudentApplicationDAO;
import dto.StudentApplicationDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class testController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        try {

            HttpSession session = request.getSession();
//            StudentApplicationDAO dao = new StudentApplicationDAO();
//            StudentApplicationDTO dto = new StudentApplicationDTO(4, "Test1", true, "2022-01-01", "test", "test", 0);
//            LocalDateTime myDateObj = LocalDateTime.now();
//            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            String formattedDate = myDateObj.format(myFormatObj);   
            StudentApplicationDAO dao = new StudentApplicationDAO();
            StudentApplicationDTO dto = new StudentApplicationDTO(1, "Thi NE", "1231234", false);
            boolean check = dao.deleteStudentApplication(4);
//            int count = dao.count("123123");
            session.setAttribute("id", check);
//            StudentApplicationDTO dto2 = dao.search(1,"t").get(dao.search(1,"t").size()-1);
//            session.setAttribute("id", dto2.getDescription());
//            StudentApplicationDTO dto2 = dao.search(1,"b").get(0);
//            session.setAttribute("id", dto2.getDescription());
            
            url = "test.jsp";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
