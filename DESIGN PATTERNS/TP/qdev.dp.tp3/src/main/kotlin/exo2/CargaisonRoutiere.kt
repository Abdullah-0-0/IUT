package exo2

class CargaisonRoutiere(distance :Int): Cargaison(distance) {
    override fun limite(): Double {
        return CARGAISONENUM.ROUTIERE.limite
    }

    override fun facteur(): Int {
        return CARGAISONENUM.ROUTIERE.facteur
    }
}