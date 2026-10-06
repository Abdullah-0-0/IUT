package exo1

class Entreprise(private val principal: Departement = Departement()) : Ajoutable , Salariable{

    override fun ajouter(entite: Entite) {
        principal.ajouter(entite)
    }

    override fun supprimer(entite: Entite) {
        principal.supprimer(entite)
    }

    override fun salaire(): Double {
        return principal.salaire()
    }
}
