package cycle;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CycleServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("Methode init() appelée");
        super.init();
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Methode init(ServletConfig config) appelée");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Methode doGet() appelée");
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Methode doPost() appelée");
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Methode doPut() appelée");
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Methode doDelete() appelée");
        super.doDelete(req, resp);
    }

    @Override
    public void doPatch(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
        System.out.println("Methode doPatch() appelée");
        super.doPatch(request,response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
