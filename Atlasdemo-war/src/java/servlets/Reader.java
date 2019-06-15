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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Павлик
 */
@WebServlet(name = "Reader", urlPatterns = {"/Reader"})
public class Reader extends HttpServlet {

    @EJB
    PageCreatorLocal pcl;

    @EJB
    LoaderLocal ldr;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false); //возвращаем старую сессию
        try (PrintWriter out = response.getWriter()) {
            out.println(pcl.reader((String) session.getAttribute("user")));
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
        session.setAttribute("type", request.getParameter("type"));
        session.setAttribute("region", request.getParameter("region"));
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("type").equals("sight")) {
                out.println(pcl.readCommets((String) session.getAttribute("user")));
                out.println(ldr.loadSightsInRegList((String) request.getParameter("region"), ""));
                out.println("<form action=\"CommentsShow\" method=\"Get\">"
                        + "<br/>Введите название объекта, о котором вы хотите почитать комментарии. <br/>"
                        + "<input type=\"text\" name=\"dest\" required size=\"120\" autocomplete=\"off\" placeholder=\"Объект\" "
                        + "/> <br/>"
                        + "<input type=\"submit\" value=\"Показать комментарии\"></form>");
                out.println("</body> </html>");
            } else {
                out.println(pcl.readCommets((String) session.getAttribute("user")));
                out.println(ldr.loadAllEventsInReg((String) request.getParameter("region")));
                out.println("<form action=\"CommentsShow\" method=\"Get\">"
                        + "<br/>Введите название объекта, о котором вы хотите почитать комментарии. <br/>"
                        + "<input type=\"text\" name=\"dest\" required size=\"120\" autocomplete=\"off\" placeholder=\"Объект\" "
                        + "/> <br/>"
                        + "<input type=\"submit\" value=\"Показать комментарии\"></form>");
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
