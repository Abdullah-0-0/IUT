package exo2

class CargaisonFluviale(distance : Int): Cargaison(distance) {
    override fun limite(): Double {
        return CARGAISON.FLUVIALE.limite
    }

}