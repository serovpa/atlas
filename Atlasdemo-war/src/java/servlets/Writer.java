/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import classes.LoaderLocal;
import classes.PageCreatorLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Павлик
 */
public class Writer extends HttpServlet {

    @EJB
    PageCreatorLocal pcl;

    @EJB
    LoaderLocal ldr;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false); //возвращаем старую сессию
        try (PrintWriter out = response.getWriter()) {
            String attr = (String) session.getAttribute("role");
            if (attr != null) {
                if (attr.equals("u")) { //проверяем атрибут на существование и на валидность
                    out.println(pcl.writer((String) session.getAttribute("user")));
                }
                if (attr.equals("a")) {
                }
            } else {
                out.println(pcl.redirectGuest((String) session.getAttribute("user")));
            }
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false); //возвращаем старую сессию
        session.setAttribute("type", request.getParameter("type"));
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("type").equals("sight")) {
                out.println(pcl.writerCommWrite((String) session.getAttribute("user")));
                out.println(ldr.loadSightsInRegList((String) request.getParameter("region"), ""));
                out.println(pcl.formsForComm());
                out.println("</body> </html>");

            } else {
                out.println(pcl.writerCommWrite((String) session.getAttribute("user")));
                out.println(ldr.loadAllEventsInReg((String) request.getParameter("region")));
                out.println(pcl.formsForComm());
                out.println("</body> </html>");
            }
        }
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
