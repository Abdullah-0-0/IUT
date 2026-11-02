    package iut.nantes.session.jsp;

    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.*;

    import java.io.IOException;

    /*
     * Servlet chargée d'authentifier l'utilisateur
     * et de gérer sa connexion à l'aide d'un cookie et d'une session.
     *
     * Elle vérifie les identifiants, mémorise le login dans un cookie,
     * stocke le login dans la session côté serveur,
     * puis redirige l'utilisateur vers une page privée.
     */
    @WebServlet(name = "jspSessionAuthenticate", value = "/jsp/session-authenticate")
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

            response.sendRedirect("/demo/JSP/Session/session-private.jsp");
        }
    }
