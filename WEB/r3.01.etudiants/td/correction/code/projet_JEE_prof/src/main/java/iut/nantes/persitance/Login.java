package iut.nantes.persitance;

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
 * Déclaration de la servlet Login.
 * Elle est accessible à l'adresse /persistance-login
 * à partir du contexte de l'application web.
 *
 * Son rôle est d'afficher le formulaire de connexion
 * et de préremplir le champ login si un cookie existe.
 */
@WebServlet(name = "persistanceLogin", value = "/persistance-login")
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

        out.println("<h1>Connexion</h1>");

        out.println("<form method=\"post\" action=\"persistance-authenticate\">");

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




