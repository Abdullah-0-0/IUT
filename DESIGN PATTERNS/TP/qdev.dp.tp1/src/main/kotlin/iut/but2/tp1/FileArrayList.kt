package iut.but2.tp1

class FileArrayList<E> : File<E> {

    private val list : MutableList<E> = ArrayList()
    override fun insererEnQueue(element: E) {
        list.add(element)
    }

    override fun supprimerEnTete() {
        list.removeFirst()
    }

    override fun listerDepuisDebut(): List<E> {
        return list
    }

    override fun taille(): Int {
        return list.size
    }

    override fun consulter(position: Int): E {
        return list[position]
    }

    override fun listerDepuisFin(): List<E> {
        return list.reversed()
    }

    override fun iterator(): Iterator<E> {
        TODO("Not yet implemented")
    }

}