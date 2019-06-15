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
public class Sights extends HttpServlet {

    @EJB
    PageCreatorLocal pcl;

    @EJB
    LoaderLocal ldr;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false); //возвращаем старую сессию
        try (PrintWriter out = response.getWriter()) {
            out.println(pcl.sights((String) session.getAttribute("user")));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false); //возвращаем старую сессию
        session.setAttribute("region", request.getParameter("region"));
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>"
                    + "<html><head><title>Sights</title>"
                    + "<meta charset=UTF-8> "
                    + "<meta name=\"viewport\" content=\"width=device-width, "
                    + "initial-scale=1.0\">"
                    + "<style>a { text-decoration: none;} </style>"
                    + "</head><body>"
                    + "<Center> <h1>  Атлас от serovpa  </h1> "
                    + "</Center><hr>");
            out.println("В регионе " + request.getParameter("region") + " можно увидеть:" + "<br/>");
            out.println((String) ldr.loadSightsInRegList((String) request.getParameter("region"), (String) request.getParameter("type")));
            out.println((String) pcl.sightsInReg((String) session.getAttribute("user")));
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
