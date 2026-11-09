package formulaire;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        urlPatterns = {"/formule" , "/formulaire"},
        initParams = {
                @WebInitParam(name = "login",value = ""),
                @WebInitParam(name = "password",value = "")
        }
)
public class form extends HttpServlet {

    private String login;
    private String password;

    @Override
    public void init() throws ServletException {

        super.init();

        login = getInitParameter("login");
        password = getInitParameter("password");
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("________________________Methode do Get()_______________________");

        req.getRequestDispatcher("/formulaire.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("______________________Methode do Post()______________________________");
        req.setCharacterEncoding("UTF-8");
        login = req.getParameter("login");
        password = req.getParameter("password");
        System.out.println("Login recu : " + login+" " + password);
        if ("joe".equals(login) && "JoJo44".equals(password)){
            String pageOrigne = resp.getHeader("Referer");
            // redirection
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
            return;
        }
    }
}