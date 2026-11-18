package hashtables

/**
 * une seconde implémentation d'une table de hachage,
 * avec gestion des collisions par chaînage externe
 *
 * @param <E> le type des éléments manipulés
 * @constructor construit une table de hachage
 * @param size la taille de la table de hachage
 * @param hash la fonction de hachage à utiliser
 */

class Hashtable<E>(
    size: Int,
    hash: (E) -> Int
) {

    private var table: Array<MutableList<E>>
    private var nbCollisions = 0

    init {
        table = emptyArray()
        for (i in 0 until size) {
            table += arrayOf(mutableListOf())
        }
    }

    //TODO


    companion object {
        /**
         * redimensionne une table de hachage
         * @param hashtable la table à redimensionner
         * @param newsize la nouvelle taille de la table de hash
         * @return une nouvelle table de hachage redimensionnée et
         * contenant tous les éléments contenus dans [hashtable]
         *
         */
        fun <E> resize(hashtable: Hashtable<E>, newsize: Int): IHashtable<E> {
            TODO("Not yet implemented")
        }
    }
}