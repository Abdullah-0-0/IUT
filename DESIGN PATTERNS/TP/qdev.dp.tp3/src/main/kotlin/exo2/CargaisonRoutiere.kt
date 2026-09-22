package exo2

class CargaisonRoutiere(distance :Int): Cargaison(distance) {
    override fun limite(): Double {
        return CARGAISON.ROUTIERE.limite
    }

    override fun facteur(): Int {
        return CARGAISON.ROUTIERE.facteur
    }
}