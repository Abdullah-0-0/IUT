package dao.old;

import java.util.List;

/**
 * Cette Interface décrie les méthodes que devra implémenter un Data Access Object
 * pour un type T choisi
 * @param <T>
 */
public interface Dao <T> {
    /**
     * Renvoie l'objet à partir de son identifiant
     * Léve une IllegalArgument Exception si l'objet ne peut-être retourné
     * @param id
     * @throws IllegalArgumentException si le produit ne peut-être obtenu.
     * @return l'objet si il existe
     */
    T get(long id);

    /**
     * Renvoie une liste qui est une copie des objets existants
     * @return
     */
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
