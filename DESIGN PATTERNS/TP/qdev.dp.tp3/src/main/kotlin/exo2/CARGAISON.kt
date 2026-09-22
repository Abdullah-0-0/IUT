package exo2

enum class CARGAISON(val limite : Double,val facteur: Int) {
    FLUVIALE(300.000,1),
    ROUTIER(38.000,4),
    AERIENNE(80.000,10)
}