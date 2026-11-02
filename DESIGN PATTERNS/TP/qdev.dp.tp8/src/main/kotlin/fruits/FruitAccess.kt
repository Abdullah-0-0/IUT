package fruits

import fruits.exceptions.FruitDejaPresentException
import fruits.model.Fruit

/**
 * Accès à une collection de fruits : consultation (filtre, itérateurs triés)
 * et modification en mémoire, indépendamment de l'origine des fruits.
 */
interface FruitAccess {

    /**
     * Relit tous les fruits depuis leur source ; les ajouts et suppressions faits
     * en mémoire sont perdus.
     * Si la lecture échoue, le contenu actuel est conservé.
     *
     * @exception fruits.exceptions.ImportFruitsException si la lecture des données échoue
     */
    fun recharge()

    /**
     * Ajoute [fruit] (en mémoire uniquement).
     *
     * @param fruit le fruit à ajouter
     *
     * @exception FruitDejaPresentException si un fruit de même nom est déjà présent
     */
    fun ajouteUnFruit(fruit: Fruit)

    /**
     * Supprime (en mémoire uniquement) le fruit nommé [nom].
     *
     * @param nom le nom du fruit à supprimer
     * @return `true` si le fruit a été supprimé, `false` si aucun fruit ne porte ce nom
     */
    fun supprimeUnFruit(nom: String): Boolean

    /**
     * Donne les fruits dont la quantité est strictement supérieure à [quantite].
     *
     * @param quantite le seuil de quantité (exclu)
     * @return les fruits concernés ; vide si aucun fruit ne convient
     */
    fun donneLesFruitsQuantiteSupp(quantite: Int): List<Fruit>

    /**
     * Donne un itérateur sur les fruits triés par ordre alphabétique de leur nom.
     * La collection n'est pas modifiée.
     *
     * @return un itérateur sur tous les fruits, du premier au dernier nom
     */
    fun iterateurFruitsAlphabetique(): Iterator<Fruit>

    /**
     * Donne un itérateur sur les fruits triés du plus cher au moins cher.
     * La collection n'est pas modifiée.
     *
     * @return un itérateur sur tous les fruits, du prix le plus élevé au plus bas
     */
    fun iterateurFruitsPlusAuMoinCher(): Iterator<Fruit>
}
