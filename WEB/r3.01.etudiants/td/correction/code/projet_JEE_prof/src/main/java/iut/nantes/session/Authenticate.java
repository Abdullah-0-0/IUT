    package iut.nantes.session;

    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.*;

    import java.io.IOException;
    import java.io.PrintWriter;
    /*
     * Servlet chargée de vérifier les identifiants de l'utilisateur.
     *
     * Si les identifiants sont corrects :
     * - un cookie mémorise le login dans le navigateur ;
     * - une session HTTP conserve le login côté serveur ;
     * - l'utilisateur est redirigé vers la page privée.
     */
    @WebServlet(name = "sessionAuthenticate", value = "/session-authenticate")
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
                throws IOException, ServletException {

            String login = request.getParameter("login");
            String password = request.getParameter("password");

            System.out.println("login: " + login+"*");
            System.out.println("password: " + password+"*");

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

            Cookie cookie = new Cookie("login", login);

            // Durée de vie : 1 hueure
            cookie.setMaxAge(60 * 60);

            // Le cookie est disponible sur toute l'application
            cookie.setPath("/");

            response.addCookie(cookie);

            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            session.setAttribute("login", login);

            response.sendRedirect("session-private");
        }
    }
