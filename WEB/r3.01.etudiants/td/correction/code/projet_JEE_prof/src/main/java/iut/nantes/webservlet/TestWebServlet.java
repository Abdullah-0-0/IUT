package iut.nantes.webservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
/*
 * Déclaration de la servlet TestWebServlet.
 *
 * Elle est accessible depuis deux URL :
 * /webservlet1 et /webservlet2.
 *
 * Elle possède également deux paramètres d'initialisation :
 * - valeurInit : valeur initiale du compteur.
 * - pas : valeur ajoutée au compteur lors d'une requête GET.
 */
@WebServlet(
        // Définit les URL permettant d'accéder à cette servlet
        urlPatterns={"/webservlet1","/webservlet2"},
        // Définit les paramètres d'initialisation de la servlet
        initParams = {
                @WebInitParam(name="valeurInit",value = "0"),
                @WebInitParam(name="pas",value = "2")
        }
)

public class TestWebServlet extends HttpServlet {
    /*
     * Compteur conservé dans l'attribut de la servlet.
     * Il est initialisé au démarrage de la servlet.
     */
    private Integer valeur;
    // Valeur ajoutée au compteur lors d'une requête GET
    private Integer pas;

    public TestWebServlet() {}
    @Override
    public void init() throws ServletException {
        super.init();
        // Récupère le paramètre "valeurInit", initialement égal à "0",
        // puis convertit cette chaîne en entier.
        valeur = Integer.valueOf(getInitParameter("valeurInit"));
        // Récupère le paramètre "pas", égal à "2",
        // puis convertit cette chaîne en entier.
        pas = Integer.valueOf(getInitParameter("pas"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("-----------doGet-----------");
        req.getParameterMap().forEach((key, values) ->
                System.out.println(key + " = " + Arrays.toString(values))
        );
        // Ajoute le pas au compteur.
        // Avec les valeurs initiales, le compteur augmente de 2
        // à chaque requête GET.
        valeur+=pas;
        System.out.println("valeur : "+valeur);
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("-----------doPost-----------");
        req.getParameterMap().forEach((key, values) ->
                System.out.println(key + " = " + Arrays.toString(values))
        );
        System.out.println("valeur : "+valeur);
        super.doPost(req, resp);
    }
}
