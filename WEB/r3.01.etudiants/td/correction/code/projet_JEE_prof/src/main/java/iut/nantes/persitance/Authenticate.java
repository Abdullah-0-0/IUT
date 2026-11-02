    package iut.nantes.persitance;

    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.Cookie;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;

    import java.io.IOException;
    import java.io.PrintWriter;

    @WebServlet(name = "persistanceAuthenticate", value = "/persistance-authenticate")
    public class Authenticate extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request,
                             HttpServletResponse response)
                throws ServletException, IOException {

            authenticate(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request,
                              HttpServletResponse response)
                throws ServletException, IOException {

            authenticate(request, response);
        }

        private void authenticate(HttpServletRequest request,
                                  HttpServletResponse response)
                throws IOException {

            String login = request.getParameter("login");
            String password = request.getParameter("password");


            if (!"Joe".equals(login) || !"JoJo44".equals(password)){
                System.out.println("login ou password err");
                String referer = request.getHeader("Referer");
                if (referer != null) {
                    response.sendRedirect(referer);
                } else {
                    response.sendRedirect("index.jsp");
                }
                return;
            }
            /*
             * Les identifiants sont corrects.
             * Création d'un cookie nommé "login" contenant le login de l'utilisateur.
             * Ce cookie sera envoyé au navigateur dans la réponse HTTP.
             */
            Cookie cookie = new Cookie("login", login);

            // Définit la durée de vie du cookie à une heure.
            cookie.setMaxAge(60 * 60);

            // Rend le cookie accessible sur tous les chemins de l'application
            // qui correspondent à ce chemin de cookie.
            cookie.setPath("/");

            // Ajoute le cookie à la réponse HTTP.
            // Le navigateur pourra le conserver et le renvoyer lors
            // des requêtes suivantes, tant qu'il est valide.
            response.addCookie(cookie);

            response.setContentType("text/html;charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<body>");
            out.println("<h1>Welcome</h1>");
            out.println("<p>Password : " + password + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
