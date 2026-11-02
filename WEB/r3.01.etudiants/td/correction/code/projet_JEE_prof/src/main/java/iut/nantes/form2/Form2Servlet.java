    package iut.nantes.form2;

    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;

    import java.io.IOException;
    import java.io.PrintWriter;

    @WebServlet(name = "form2Servlet", value = "/form2-servlet")
    public class Form2Servlet extends HttpServlet {

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


            /*
             * Vérifie les identifiants saisis.
             *
             * "Joe".equals(login) vérifie que le login est "Joe".
             * "JoJo44".equals(password) vérifie que le mot de passe est "JoJo44".
             *
             * L'opérateur || signifie "OU" :
             * si le login OU le mot de passe est incorrect,
             * l'authentification échoue.
             *
             * L'utilisation de equals() sur une chaîne constante
             * évite une NullPointerException si le paramètre vaut null.
             */
            if (!"Joe".equals(login) || !"JoJo44".equals(password)){
                // Récupère l'en-tête HTTP Referer, qui indique généralement
                // la page depuis laquelle la requête a été envoyée.
                String referer = request.getHeader("Referer");
                if (referer != null) {
                    // Si l'en-tête Referer existe, redirige vers cette adresse.
                    response.sendRedirect(referer);
                } else {
                    response.sendRedirect("index.jsp");
                }
                return;
            }

            response.setContentType("text/html;charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<body>");
            out.println("<h1>Welcome</h1>");
            out.println("<p>Login : " + login + "</p>");
            out.println("<p>Password : " + password + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
