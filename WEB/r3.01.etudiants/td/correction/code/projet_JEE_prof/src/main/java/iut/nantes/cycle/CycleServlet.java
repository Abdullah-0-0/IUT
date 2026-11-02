package iut.nantes.cycle;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
/*
 * Servlet permettant d'observer son cycle de vie
 * et les méthodes appelées selon le type de requête HTTP reçu.
 *
 * Le cycle de vie comprend principalement :
 * 1. L'initialisation avec init()
 * 2. Le traitement des requêtes HTTP
 * 3. La destruction avec destroy()
 */

// Déclare la servlet et définit l'URL permettant d'y accéder
@WebServlet(name = "cycleServlet", value = "/cycle-servlet")
public class CycleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("----------------- doGet ----------------");
        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("--------------- doPost ----------------");
        super.doPost(req, resp);
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("--------------- doPut ---------------");
        super.doPut(req, resp);
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("--------------- doDelete ---------------");
        super.doDelete(req, resp);
    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("--------------- doPatch ----------------");
        super.doPatch(req, resp);
    }

    @Override
    public void init() throws ServletException {
        System.out.println("--------------- init ---------------");
        super.init();
    }
    /*
     * Méthode d'initialisation recevant la configuration de la servlet.
     * Le conteneur appelle normalement init(ServletConfig) pour initialiser
     * la servlet. L'implémentation de HttpServlet transmet ensuite
     * l'initialisation à init().
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("--------------- init config---------------");
        super.init(config);
    }
    /*
     * Méthode appelée lorsque le conteneur retire la servlet du service,
     * par exemple lors de l'arrêt ou du redéploiement de l'application.
     */
    @Override
    public void destroy() {
        System.out.println("--------------- destroy ----------------");
        super.destroy();
    }
}
