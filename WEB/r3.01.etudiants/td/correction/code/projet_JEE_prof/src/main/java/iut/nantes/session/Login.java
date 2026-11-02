package iut.nantes.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
/*
 * Servlet chargée d'afficher le formulaire de connexion.
 *
 * Elle recherche un cookie nommé "login".
 * Si ce cookie existe, sa valeur est utilisée pour préremplir
 * le champ Login du formulaire.
 */
@WebServlet(name = "sessionLogin", value = "/session-login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {


        // Récupération des cookies
        Cookie[] cookies = request.getCookies();

        String loginCookie = null;

        if (cookies != null) {
            loginCookie = Arrays.stream(cookies)
                    .filter(cookie -> "login".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }


        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Connexion</title>");
        out.println("</head>");

        out.println("<body>");

        out.println("<h1>session</h1>");

        out.println("<form method=\"post\" action=\"session-authenticate\">");

        out.println("<label for=\"login\">Login :</label>");
        out.println("<input type=\"text\" id=\"login\" name=\"login\" value=\""
                + (loginCookie != null ? loginCookie : "")
                + "\" required>");

        out.println("<label for=\"password\">Mot de passe :</label>");
        out.println("<input type=\"password\" id=\"password\" name=\"password\" required>");


        out.println("<input type=\"submit\" value=\"Se connecter\">");

        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
    }


}




