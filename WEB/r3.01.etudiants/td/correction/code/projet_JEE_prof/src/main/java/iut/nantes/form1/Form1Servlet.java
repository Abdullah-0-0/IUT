package iut.nantes.form1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
/*
 * Déclaration de la servlet Form1Servlet.
 * Elle est accessible à l'adresse /form1-servlet
 * à partir du contexte de l'application web.
 */
@WebServlet(name = "form1Servlet", value = "/form1-servlet")
public class Form1Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        afficherLogin(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        afficherLogin(request, response);
    }
    /*
     * Méthode privée qui récupère les données envoyées
     * par le formulaire et construit une page HTML.
     *
     * Elle est appelée par doGet() et doPost().
     */
    private void afficherLogin(HttpServletRequest request,
                               HttpServletResponse response)
            throws IOException {

        // Récupère la valeur du paramètre "login"
        String login = request.getParameter("login");
        // Récupère la valeur du paramètre "password".
        String password = request.getParameter("password");
        // Si aucun paramètre "login" n'a été envoyé,
        // remplace null par une chaîne vide.
        if (login == null) {
            login = "";
        }
        // Si aucun paramètre "password" n'a été envoyé,
        // remplace null par une chaîne vide.
        if (password == null) {
            password = "";
        }
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Informations reçues</h1>");
        out.println("<p>Login : " + login + "</p>");
        out.println("<p>Password : " + password + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
