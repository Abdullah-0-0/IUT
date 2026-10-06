package exo2

class Vaisseau(nom : String ,val type : TypeVaisseau ) : Dockable {
    var nom : String
    init {
        this.nom = nom
    }
    fun renommer(nouveauNom : String){
        nom = nouveauNom
    }
    fun tonnage() : Int {
        when(type){
            TypeVaisseau.CHASSEUR -> return TypeVaisseau.CHASSEUR.tonnage
            TypeVaisseau.CARGO -> return TypeVaisseau.CARGO.tonnage
            TypeVaisseau.FREGATE -> return TypeVaisseau.FREGATE.tonnage
            TypeVaisseau.CORVETTE -> return TypeVaisseau.CORVETTE.tonnage
            TypeVaisseau.CROISEUR -> return TypeVaisseau.CROISEUR.tonnage
        }
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Vaisseau) nom == other.nom  else false
    }
}



