
package servlets;

import classes.CommentWriterLocal;
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
public class CommentsLoader extends HttpServlet {

    @EJB
    PageCreatorLocal pcl;

    @EJB
    CommentWriterLocal cwl;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        try (PrintWriter out = response.getWriter()) {
            if (session.getAttribute("type").equals("event")) {
                out.println(pcl.confirmComment(cwl.writeEventComment(request.getParameter("comment"),
                        request.getParameter("title"), (String) session.getAttribute("user"), request.getParameter("dest"))));
            } else {
                out.println(pcl.confirmComment(cwl.writeSightComment(request.getParameter("comment"),
                        request.getParameter("title"), (String) session.getAttribute("user"), request.getParameter("dest"))));
            }
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

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
