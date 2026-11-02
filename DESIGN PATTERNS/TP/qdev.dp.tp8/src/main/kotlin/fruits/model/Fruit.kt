package fruits.model

import kotlinx.serialization.Serializable


/**
 * Un fruit proposé à la vente.
 *
 * Les fruits sont naturellement ordonnés par leur [nom] (ordre alphabétique) ;
 * le prix et la quantité n'interviennent pas dans [compareTo].
 *
 * @property nom le nom du fruit
 * @property prix le prix du fruit
 * @property quantite la quantité en stock ; seule propriété modifiable
 */
@Serializable
data class Fruit(
    val nom: String,
    val prix: Double,
    var quantite: Int
) : Comparable<Fruit> {

    /**
     * Compare ce fruit à [other] selon l'ordre alphabétique de leurs noms.
     * Le prix et la quantité sont ignorés.
     *
     * @param other le fruit à comparer
     * @return un entier négatif, nul ou positif si le nom de ce fruit est
     * respectivement avant, égal ou après celui de [other]
     */
    override fun compareTo(other: Fruit): Int {
        TODO()
    }

    /**
     * Deux fruits sont égaux s'ils ont le même [nom], la même [quantite] et
     * des prix qui diffèrent d'au plus 0,01.
     *
     * @param other l'objet à comparer
     * @return `true` si [other] est un [Fruit] égal à celui-ci
     */
    override fun equals(other: Any?): Boolean {
        TODO()
    }

}
