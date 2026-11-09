package formulaire;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@WebServlet(
        urlPatterns = "/formule1",
        initParams = {
                @WebInitParam(name = "login",value = ""),
                @WebInitParam(name = "password",value = "")
        }
)
public class formPersiste extends HttpServlet {
    private String login;
    private String password;
    private Cookie[] cookies ;
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        PrintWriter page = resp.getWriter();
//        page.println("<html> <body>");
//        page.println("<h1> get partie persistente </h1>");
//        page.println(" </body> </html>");
        System.out.println("_______________________________GET___________________");
        String loginMemo = "";
        cookies = req.getCookies();
        if (cookies!= null){
            for (Cookie c : cookies){
                System.out.println("cookie : " + c.getName() + " = " + c.getValue());
                if ("login".equals(c.getName())){
                    loginMemo = URLDecoder.decode(c.getValue(), StandardCharsets.UTF_8);
                    break;
                }
            }
        }
        System.out.println("loginMemo: "+loginMemo);
        req.setAttribute("loginMemo",loginMemo);
        req.getRequestDispatcher("/formulairePersis.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("_______________________________POST___________________");
        PrintWriter page = resp.getWriter();
        login = req.getParameter("login");
        password = req.getParameter("password");

        System.out.println( "login recu :" + login +" "+ password );

        boolean ok = login.equals("joe") && password.equals("JoJo44");
        resp.setContentType("text/html;charset=UTF-8");
        String retour = req.getContextPath() + "/formule1";

        page.println("<html><body>");
        if (ok) {
            Cookie cookie = new Cookie("login",URLDecoder.decode(login,StandardCharsets.UTF_8));
            cookie.setMaxAge(60*60*20*7);
            cookie.setPath(req.getContextPath());
            resp.addCookie(cookie);
            page.println("<p>Connexion réussie, bienvenue " + login + " !</p>");
            page.println("<p><a href='" + retour + "'>Retour au formulaire</a></p>");
        } else {
            page.println("<p>Connexion échouée.</p>");
            page.println("<p><a href='" + retour + "'>Veuillez réessayer</a></p>");
        }
        page.println("</body></html>");
        System.out.println(Arrays.toString(cookies));
    }

}
