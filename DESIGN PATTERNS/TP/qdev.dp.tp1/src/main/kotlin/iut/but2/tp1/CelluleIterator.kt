package iut.but2.tp1

class CelluleIterator<E> : Iterator<E> {
    var debut : Cellule<E>?
    var suivant : Cellule<E>?
    init {
        this.debut = null
        suivant = debut?.suivant()
    }
    override fun hasNext(): Boolean {
        if (suivant == null){
            return false
        }
        return true
    }

    override fun next(): E {
        return  debut?.suivant()!!.valeur()

    }


}