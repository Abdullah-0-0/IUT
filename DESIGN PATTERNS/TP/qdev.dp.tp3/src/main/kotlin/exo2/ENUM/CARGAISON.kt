package exo2.ENUM

enum class CARGAISON (val limite : Double, val facteur : Int) {
    FLUVIALE(300_000.0, 1),
    ROUTIERE(38_000.0, 4),
    AERIENNE(80_000.0, 10)
}