package iut.nantes.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
/*
Déclaration de la servlet qui représente la page privée.
 */
@WebServlet(name = "sessionPrivate", value = "/session-private")
public class Private extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("login") != null) {

            String login = (String) session.getAttribute("login");

            // utilisateur connecté
            response.setContentType("text/html;charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>session</title>");
            out.println("</head>");

            out.println("<body>");

            out.println("<h1>Welcome</h1>");
            out.println("<p>"+login+"</p>");

            out.println("</body>");
            out.println("</html>");

        } else {

            // utilisateur non connecté
            response.sendRedirect("session-login");
            return;
        }




    }


}




