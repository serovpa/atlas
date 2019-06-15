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
public class Regions extends HttpServlet {

    @EJB
    PageCreatorLocal pcl;

    @EJB
    LoaderLocal ldr;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false); //возвращаем старую сессию
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>"
                    + "<html><head><title>Regions</title>"
                    + "<meta charset=windows-1251> "
                    + "<meta name=\"viewport\" content=\"width=device-width, "
                    + "initial-scale=1.0\">"
                    + "<style>a { text-decoration: none;} </style>"
                    + "</head><body>"
                    + "<Center> <h1>  Атлас от serovpa  </h1> "
                    + "</Center><hr>");
            out.println("<p>" + (String) session.getAttribute("user") + ", на этой странице представлен полный перечень субъектов РФ" + "<p/>");
            out.println("В России представлено 22 республики, 9 краев, 46 областей, "
                    + "3 города федерального значения, 1 автономная область и 4 автомных округа <br/>"
                    + "<Center> <table border = \"0\" bordercolor = \"white\" cellspacing=\"10\" cellpadding=\"10\" frame=\"vsides\" rules=\"cols\" align=\"center\"  >"
                + "<tr>"
                + "<th><font size=\"8\" color=\"teal\" face=\"Arial\">Республики </font></a></th>"  
                + "<th><font size=\"8\" color=\"teal\" face=\"Arial\">Края </font></th>"  
                + "<th><font size=\"8\" color=\"teal\" face=\"Arial\">Области</font></th>"
                + "<th><font size=\"8\" color=\"teal\" face=\"Arial\">Другие субъекты</font></th></tr>"
                + "<tr><td align=\"center\" width = \"25%\">" + (String) ldr.loadRepList() + "</td>"
                + "<td align=\"center\" width = \"25%\">" +( String) ldr.loadKrList() +"</td>"
                + "<td align=\"center\" width = \"25%\">" +( String) ldr.loadOblList() +" </td>"
                + "<td align=\"center\" width = \"25%\">" + (String) ldr.loadOtherlList() +"</td></tr>"
                + "</table>");
            out.println("<br/><form action=\"Regions\" method=\"Post\">"
                    + "О каком регионе Вы хотите узнать? Обязательно пишите название с большой буквы: <br/>"
                    + "<input type=\"text\" name=\"region\" required id=\"region\" size=\"200\" autocomplete=\"off\"/> <br/>"
                    + "<input type=\"submit\" name=\"submit\" id=\"submit\" value=\"Найти\"/>"
                    + "</form>");
            out.println("<hr>");
            out.println("</body>");
            out.println("</html>");
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
        if(ldr.loadRegInfo((String) request.getParameter("region"))==null){
            try (PrintWriter out = response.getWriter()) {
             out.println("<!DOCTYPE html>"
                    + "<html><head><title>Regions</title>"
                    + "<meta charset=UTF-8> "
                    + "<meta name=\"viewport\" content=\"width=device-width, "
                    + "initial-scale=1.0\">"
                    + "<style>a { text-decoration: none;} </style>"
                    + "</head><body>"
                    + "<Center> <h1>  Атлас от serovpa  </h1> "
                    + "</Center><hr>");
            out.println("Вероятно вы ошиблись с названием субъекта. Сейчас Вы вернетесь на предыдущую страницу.");
            out.println("<hr>");
            out.println("</body>");
            out.println("<script type=\"text/javascript\"> setTimeout(function(){ location=\"Regions\";}, 3000);</script>)");
            out.println("</html>");
            }
        }else{
            try (PrintWriter out = response.getWriter()) {
             out.println("<!DOCTYPE html>"
                    + "<html><head><title>Regions</title>"
                    + "<meta charset=UTF-8> "
                    + "<meta name=\"viewport\" content=\"width=device-width, "
                    + "initial-scale=1.0\">"
                    + "<style>a { text-decoration: none;} </style>"
                    + "</head><body>"
                    + "<Center> <h1>  Атлас от serovpa  </h1> "
                    + "</Center><hr>");
            out.println((String) ldr.loadRegInfo((String) request.getParameter("region")));
            out.println("<hr>");
            out.println("</body>");
            out.println("</html>");
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
