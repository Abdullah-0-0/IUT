package iut.but2.tp1

import java.text.CollationElementIterator
import java.text.FieldPosition
import java.util.IllegalFormatCodePointException

class FileChainee<E> : File<E>{
    private var debut : Cellule<E>?
    private var fin : Cellule<E>?
    init {
        this.debut = null
        this.fin = null
    }

    override fun insererEnQueue(element: E) {
        if (debut != null){
            fin!!.modifieSuivant(Cellule(element))
            fin = fin?.suivant()
        }
        else{
            debut = Cellule(element)
            fin = debut
        }

    }

    override fun supprimerEnTete() {
        if (debut == null){
            throw NoSuchElementException()
        }
        debut = debut?.suivant()


    }

    override fun listerDepuisDebut(): List<E> {
        var liste = arrayListOf<E>()
        var d = debut
        if (d != null){
            liste.add(d!!.valeur())
            while (d?.suivant() != null){
                d = d?.suivant()
                liste.add(d!!.valeur())
            }
        }
        return liste
    }

    override fun taille(): Int {
//        var compteur = 0
//        var deb = debut
//        while (deb!=null){
//            compteur++
//            deb = deb.suivant()
//        }
//        return  compteur
        return listerDepuisFin().size
    }

    override fun consulter(position: Int): E {
        val liste = listerDepuisDebut()
        if (position>liste.size){
            throw  IndexOutOfBoundsException()
        }
        return  liste[position]
    }

    override fun listerDepuisFin(): List<E> {
        val liste = arrayListOf<E>()
        val listeOrigine = listerDepuisDebut()
        for (i in listeOrigine.size-1 downTo 0 ){
            liste.add(listeOrigine[i])
        }
//        for (i in listeOrigine.size until 0){
//            liste.add(consulter(i))
//        }
        return liste
    }

    override fun iterator(): Iterator<E> {
        TODO("Not yet implemented")
    }
}