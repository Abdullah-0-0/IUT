package exo2

class CargaisonAerienneUrgente(distance : Int): CargaisonAerienne(distance) {

    override fun facteur(): Int {
        return super.facteur()*2
    }
}