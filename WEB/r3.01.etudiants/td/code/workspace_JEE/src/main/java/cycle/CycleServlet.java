package cycle;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cycle-servlet")
public class CycleServlet extends HttpServlet {
    public CycleServlet(){
        System.out.println("Constructeur CycleServlet appelé");
    }
    @Override
    public void init(){
        System.out.println("Méthode init() appelée");
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Méthode init(ServletConfig) appelée");
        super.init(config);
    }

    @Override
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException{
        System.out.println("Méthode doGet() appelée");
    }

    @Override
    public  void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
        System.out.println("Méthode doPost() appelée");
    }

    @Override
    public void doPut(HttpServletRequest request , HttpServletResponse response) throws IOException{
        System.out.println("Méthode doPut() appelée");
    }

    @Override
    public void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException{
        System.out.println("Méthode doPatch() appelés");
    }
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
        System.out.println("Méthode doDelete() appelés");
    }
    @Override
    public void destroy() {
        System.out.println("Méthode destroy() appelée");
    }

}
