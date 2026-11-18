package hashtables

/**
 * Une premiere implémentation d'une table de hachage,
 * sans gestion des collisions
 *
 * @param <E> le type des éléments manipulés
 * @constructor construit une table de hachage
 * @param size la taille de la table de hachage
 * @param hash la fonction de hachage à utiliser
 */
class SimpleHashtable<E>(
    size: Int,
    hash: (E) -> Int
)  : HashtableA<E>(size, hash){

    private var table: Array<Any?>

    init {
        table = arrayOfNulls<Any?>(size)
    }

    @Suppress("UNCHECKED_CAST")
    private fun get(index: Int): E {
        requireNotNull(table[index])
        return (table[index] as E)
    }

    override fun add(element: E): Boolean {
        if (element != null){
            if (table.contains(element)){
                return false
            }
            else{
                var modulo : Int = 0;
                if (element is Int){
                    modulo = element  % this.size
                }


                if (table[modulo]==null){
                    table[modulo] = element
                    return true
                }
                else{
                    throw CollisionException("")
                }

            }

        }
        return false
    }

    override fun contains(element: E): Boolean {
        return if (get(element) == null) {
            false
        }
        else{
            true
        }
    }

    override fun get(element: E): E? {
        for (i in table){
            if (i == element){
                return  i as E
            }
        }
        return null
    }

    override fun nbElements(): Int {
        var c : Int = 0
        for (i in table){
            println(i)
           if(i != null){
               c++
           }
        }
        return c
    }

    override fun remove(element: E): Boolean {
        if (element == null){
            return false
        }
        if (table.contains(element)){
            for (i in 0 until size ){
                if (table[i] == element){
                    println("ancien valeur : ${table[i]}")
                    table[i] = null
                    println(" valeur actuel:  ${table[i]}")
                    return true
                }
            }
        }
        return false
    }
}