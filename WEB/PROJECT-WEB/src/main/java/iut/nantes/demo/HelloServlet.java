package iut.nantes.demo;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "helloServlet" , value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message ;
    @Override
    public void init() throws ServletException {
        System.out.println("___________________Méthode init()_________________");
        message = "bonjour";
        super.init();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("___________________Méthode init(conf )_________________");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("___________________Méthode doGet()_________________");
        resp.setContentType("text/html");
        PrintWriter page = resp.getWriter();
        page.println("<html lang = fr>");
        page.println("<body>");
        page.println("<h1> bonjour partie h1 le message est le suivant" + message +" </h1>");
        page.println(" </body> </html> ");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPatch(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
