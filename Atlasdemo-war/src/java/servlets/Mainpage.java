/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import classes.PageCreatorLocal;
import classes.User;
import classes.UsercheckLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
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
public class Mainpage extends HttpServlet {

    @EJB
    UsercheckLocal ucl;

    @EJB
    PageCreatorLocal pcl;

    private String role;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("windows-1251");
        response.setCharacterEncoding("windows-1251");
        HttpSession session = request.getSession();
        if (request.getParameter("login") == null //вход в режиме гостя
                || request.getParameter("login") != null
                && request.getParameter("login").trim().isEmpty()) {

            session.setAttribute("user", "Гость");
            try (PrintWriter out = response.getWriter()) {
                out.println(pcl.mainpageGuest((String) session.getAttribute("user"))); // отрисовываем страницу при помощи бина
            }
        } else { //вход при заполненном логине
            User user = new User(request.getParameter("login"), request.getParameter("psw")); // создаем пользователя
            session.setAttribute("user", request.getParameter("login"));
            role = ucl.userCheck(user.getLogin(), user.getPsw()); // проверяем роль
            switch (role) {
                case "N/A":
                    try (PrintWriter out = response.getWriter()) {
                        session.setAttribute("user", user.getLogin());
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Main page</title>");
                        out.println("<meta charset=windows-1251>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1> Неверные логин и/или пароль </h1>" + "<br/>" + "<hr>");
                        out.println("<p>" + "Вы будете перенаправлены на стартовую страницу через 3 секунды" + "<p/> <br/>");
                        out.println("<hr>");
                        out.println("</body>");
                        out.println("<script type=\"text/javascript\"> setTimeout(function(){ location=\"index.html\";}, 3000);</script>)");
                        out.println("</html>");
                    }
                    break;
                case "u":
                    String attr = (String) session.getAttribute("user"); //запрашиваем атрибут сессии
                    if (attr != null) { //проверяем на существование
                        if (attr.equals(user.getLogin())) { //проверяем на совпадение с логином
                            try (PrintWriter out = response.getWriter()) {
                                session.setAttribute("role", role);
                                out.println(pcl.mainpageUser((String) session.getAttribute("user")));
                            }
                        } else { // если с логином не совпадает, то прерываем сессию, и запускаем метод заново
                            session.invalidate();
                            doPost(request, response);
                        }
                    } else { //если атрибута не было, то создаем его и работаем с ним
                        session.setAttribute("user", user.getLogin());
                        session.setAttribute("role", role);
                        try (PrintWriter out = response.getWriter()) {
                            out.println(pcl.mainpageUser((String) session.getAttribute("user")));
                        }
                    }
                    break;
                case "a":
                    try (PrintWriter out = response.getWriter()) {
                        session.setAttribute("user", user.getLogin());
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Main page</title>");
                        out.println("<meta charset=windows-1251>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Главная страница по адресу " + request.getContextPath() + "</h1>" + "<br/>" + "<hr>");
                        out.println("<p>" + "Вы зашли как " + (String) session.getAttribute("user") + "<p/> <br/>");
                        out.println("<p>" + "Ваша роль " + (String) ucl.userCheck(user.getLogin(), user.getPsw()) + "<p/> <br/>");
                        out.println("<hr>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    break;
            }
        }
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
