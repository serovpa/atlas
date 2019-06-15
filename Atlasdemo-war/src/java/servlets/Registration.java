/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import classes.UsercheckLocal;
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
public class Registration extends HttpServlet {

    @EJB
    UsercheckLocal ucl;

    private String role;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Registration</title>");
            out.println("<meta charset=windows-1251>");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Registration at " + request.getContextPath() + "</h1>");
            out.println(" <Center>\n"
                    + "        <form action=\"Registration\" method=\"Post\">\n"
                    + "            Введите ваши учетные данные: <br/>\n"
                    + "            <input type=\"text\" name=\"createlogin\" id=\"login\" size=\"120\" autocomplete=\"off\" placeholder=\"Введите Ваш логин\" required/> <br/>"
                    + "            <input type=\"password\" name=\"createpsw\" id=\"psw\" size=\"120\" autocomplete=\"off\" placeholder=\"Введите Ваш пароль\" required/>  <br/>"
                    + "            <input type=\"text\" name=\"firstname\" id=\"fn\" size=\"120\" autocomplete=\"off\" placeholder=\"Введите Ваше имя\" required/>  <br/>"
                    + "            <input type=\"text\" name=\"lastname\" id=\"ln\" size=\"120\" autocomplete=\"off\" placeholder=\"Введите Вашу фамилию\" required/>  <br/>"
                    + "            <input type=\"submit\" name=\"registration\" id=\"submit\" value=\"Создать\"/>\n"
                    + "        </form>\n"
                    + "    </center>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession();
        session.setAttribute("user", "Гость");
        role = ucl.createUser(request.getParameter("createlogin"), request.getParameter("createpsw"), request.getParameter("firstname"), request.getParameter("lastname"));
        if (role.equals("N/A")) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Main page</title>");
                out.println("<meta charset=windows-1251>");
                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1> Учетная запись с логином " + (String) request.getParameter("createlogin") + " уже сущетсвует. Попробуйте другой Логин. "
                        + "Сейчас вы будете перенаправлены на главную страницу. </h1>" + "<br/>" + "<hr>");
                out.println("</body>");
                out.println("<script type=\"text/javascript\"> setTimeout(function(){ location=\"index.html\";}, 5000);</script>)");
                out.println("</html>");
            }
        } else {
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Main page</title>");
                out.println("<meta charset=windows-1251>");
                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1> Ваша учетная запись " + (String) request.getParameter("createlogin") + " создана. "
                        + "Сейчас вы будете перенаправлены на главную страницу"
                        + "Ваша роль на сайте - " + role + "</h1>" + "<br/>" + "<hr>");
                out.println("</body>");
                out.println("<script type=\"text/javascript\"> setTimeout(function(){ location=\"index.html\";}, 3000);</script>)");
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
