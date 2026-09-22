package exo2

open class CargaisonAerienne(distance : Int) : Cargaison(distance){
    override fun encombrement(marchandise: Marchandise): Double {
        return marchandise.donneVolume()
    }

    override fun limite(): Double {
        return CARGAISONENUM.AERIENNE.limite
    }

    override fun facteur(): Int {
        return CARGAISONENUM.AERIENNE.facteur
    }
}