package iut.nantes.WebServlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;

@WebServlet(
        urlPatterns = {"/webservlet1","/webservlet2"},
        initParams = {
                @WebInitParam(name = "valeurInit" ,value = "0"),
                @WebInitParam(name = "pas",value = "2")
        }
)
public class TestWebServlet extends HttpServlet {
    private Integer valeur ;
    private Integer pas ;
    @Override
    public void init() throws ServletException {
        super.init();
        valeur = Integer.valueOf(getInitParameter("valeurInit"));
        pas = Integer.valueOf(getInitParameter("pas"));
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        valeur+=pas;
        System.out.println("_________________Methode doGet()____________");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter page = resp.getWriter();
        page.println("<html> <body>");
        page.println("<p> nous somme a "+valeur);
        page.println("</html> </body>");
        req.getParameterMap().forEach((key,valeur)->
                // recupere les information donne sur l'URL
            System.out.println(key + "=" + Arrays.toString(valeur))
        );
        System.out.println("valeur: "+ valeur );
        //super.doGet(req, resp);
    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPatch(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doHead(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
}
