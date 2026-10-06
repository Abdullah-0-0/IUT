package exo1

class Employe(nom : String , salaire : Double) : Entite() {
    private var nom : String
    private var salaire : Double
    init {
        this.nom = nom
        this.salaire = salaire
    }
    override fun salaire(): Double {
        return salaire
    }
}