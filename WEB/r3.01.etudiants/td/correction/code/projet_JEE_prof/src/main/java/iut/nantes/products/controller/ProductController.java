package iut.nantes.products.controller;

import iut.nantes.products.dao.Product;
import iut.nantes.products.dao.optional.Dao;
import iut.nantes.products.dao.optional.ProductMemoryDao;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
/*
 * Contrôleur de l'application de gestion des produits.
 *
 * Dans l'architecture MVC :
 * - Le modèle représente les données, ici les objets Product.
 * - La vue affiche les produits, ici products_view.jsp.
 * - Le contrôleur reçoit les requêtes et coordonne les traitements.
 *
 * Cette servlet permet d'afficher la liste des produits
 * et de modifier leurs quantités.
 */

@WebServlet("/products/products_controller")
public class ProductController extends HttpServlet {
    // Déclare le DAO utilisé pour accéder aux produits.
    // ProductMemoryDao conserve les données en mémoire.
    private Dao<Product> dao = new ProductMemoryDao();
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
       dao.save(new Product(1, "Ordinateur", 750.0, 10));
       dao.save(new Product(2, "Clavier", 35.0, 25));
       dao.save(new Product(3, "Souris", 20.0, 50));
       dao.save(new Product(4, "Écran", 180.0, 8));
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        // Récupère la liste de tous les produits présents dans le DAO
        List<Product> products = dao.getAll();
        // Transmet la liste des produits à la JSP grâce à un attribut
        // associé à la requête HTTP.
        request.setAttribute("products", products);
        /*
         * Transfère le traitement vers la page JSP.
         * La JSP récupère la liste avec request.getAttribute("products")
         * puis affiche les données dans un tableau HTML.
         *
         * forward() conserve les attributs de la requête courante.
         */
        request.getRequestDispatcher("/JSP/Products/products_view.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getParameter("id"));
            String action = req.getParameter("action");

            /*
             * Convertit le DAO générique en ProductMemoryDao
             * afin d'utiliser sa méthode get(id).
             *
             * Cette conversion suppose que dao est bien une instance
             * de ProductMemoryDao.
             *
             */

            ProductMemoryDao productDao = (ProductMemoryDao) dao;
            Optional<Product> product = productDao.get(id);

            if ("increment".equals(action)) {

                if (product.isPresent()) {
                    //Le debordement ne sera pas traite
                    product.get().setQuantity(product.get().getQuantity() + 1);
                    productDao.save(product.get());
                }
            } else if ("decrement".equals(action)) {
                if (product.isPresent()) {
                    Integer quantity = product.get().getQuantity() - 1;
                    if (quantity >= 0) {
                        product.get().setQuantity(quantity);
                        productDao.save(product.get());
                    }
                }
            }

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        /*
         * Redirige le navigateur vers le contrôleur après le traitement.
         * Cette technique s'appelle PRG (Post/Redirect/Get).
         *
         * Elle évite généralement de soumettre à nouveau le formulaire
         * si l'utilisateur actualise ensuite la page.
         */
        resp.sendRedirect(
                req.getContextPath() + "/products/products_controller"
        );
    }
}
