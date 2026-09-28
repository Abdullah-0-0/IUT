package dao.optional;

import java.util.List;
import java.util.Optional;

/**
 * Cette Interface décrie les méthodes que devra implémenter un Data Access Object
 * pour un type T choisi
 *
 * @param <T>
 */
public interface Dao<T> {
    /**
     * Recherche un élément à partir de son identifiant.
     * @param id identifiant de l'élément à rechercher
     * @return un {@link Optional} contenant l'élément s'il existe,
     * ou un {@link Optional#empty()} dans le cas contraire
     **/
    Optional<T> get(long id);

    /**
     * Récupère l'ensemble des éléments présents dans le DAO.
     * @return une liste contenant tous les éléments
     **/
    List<T> getAll();

    /**
     * Permet d'ajouter un Objet à la liste s'il n'est pas déjà présent.
     * @throws IllegalArgumentException si l'objet ne peut-être ajouté
     * @param t l'objet à ajouter
     */
    void save(T t);

    /** Met à jour un élément existant à partir des paramètres fournis.
     * @param t élément à mettre à jour
     * @param params paramètres utilisés pour modifier l'élément
     * @throws IllegalArgumentException si la mise à jour est impossible
     **/
    void update(T t, String[] params);

    /** Supprime un élément.
     * @param t élément à supprimer
     * @throws IllegalArgumentException si la suppression est impossible
     **/
    void delete(T t);
}
