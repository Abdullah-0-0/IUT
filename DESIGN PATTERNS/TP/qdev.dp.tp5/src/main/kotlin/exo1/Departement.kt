package exo1

class Departement(private val elements : MutableList<Entite> = mutableListOf()) : Ajoutable , Entite() {

    override fun ajouter(entite: Entite) {
        elements.add(entite)
    }

    override fun supprimer(entite: Entite) {
        elements.remove(entite)
    }

    override fun salaire(): Double {
        var res = 0.0
        for (i in elements) {
            res+= i.salaire()
        }
        return res
    }

}