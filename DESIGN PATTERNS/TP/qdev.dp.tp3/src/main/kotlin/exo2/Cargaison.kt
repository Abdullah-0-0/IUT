package exo2

import java.util.function.Consumer

abstract class Cargaison protected constructor (private val distance : Int) {


    private var encombrementActuel = 0.0
    private val marchandises = mutableListOf<Marchandise>()

    enum class CARGAISON (val limite : Double, val facteur : Int) {
        FLUVIALE(300_000.0, 1),
        ROUTIERE(38_000.0, 4),
        AERIENNE(80_000.0, 10)
    }

    fun ajouter(marchandise : Marchandise) : Boolean {
        if (encombrementActuel+encombrement(marchandise)<=limite()){
            marchandises.add(marchandise)
            return true
        }
        return false
    }

    fun cout() : Double {
        return encombrementActuel * distance*facteur()
    }
    fun rechercher(marchandise : Marchandise) : Int {
        for (i in 0 until  marchandises.size){
            if (marchandise == marchandises[i] ){
                return i
            }
        }
        return -1
    }

    open protected fun encombrement(marchandise : Marchandise) : Double {
        return marchandise.donnePoids()
    }

    abstract  protected fun limite() : Double

    open protected fun facteur() : Int {
        return CARGAISON.FLUVIALE.facteur
    }
    open fun iterator() : Iterator<Marchandise>{
        return marchandises.iterator()
    }

    class MarchandiseIterator(private val cargaison: Cargaison): Iterator<Marchandise>{
        private var nbTotalMarchandises : Int = cargaison.marchandises.size
        private var nbMarchandisesIteree : Int = 0

        override fun hasNext(): Boolean {
            TODO("Not yet implemented")
        }

        override fun next(): Marchandise {
            TODO()
        }

    }

    companion object {
        fun donne(distance : Int, type : CARGAISON, urgente : Boolean = false) : Cargaison {
//            if (type == CARGAISON.AERIENNE){
//                if (urgente){
//                    return CargaisonAerienneUrgente(distance)
//                }
//                else{
//                    return CargaisonAerienne(distance)
//                }
//            }
//            if (type == CARGAISON.FLUVIALE){
//                if (!urgente){
//                    return CargaisonFluviale(distance)
//                }
//                else{
//                    throw UrgentImpossibleException("")
//                }
//
//            }
            if (urgente ){
                if (type!=CARGAISON.AERIENNE){
                    throw UrgentImpossibleException("")
                }else{
                    return CargaisonAerienneUrgente(distance)
                }
            }

            return  when(type){
                CARGAISON.AERIENNE -> CargaisonAerienne(distance)
                CARGAISON.ROUTIERE -> CargaisonRoutiere(distance)
                CARGAISON.FLUVIALE -> CargaisonFluviale(distance)
            }

        }

    }

}