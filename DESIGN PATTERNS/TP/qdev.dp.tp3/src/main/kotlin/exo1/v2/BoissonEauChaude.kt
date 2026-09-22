package exo1.v2

abstract class BoissonEauChaude : BoissonChaude(){
    override fun faireBouillir(): String {
        return "Portage de l'eau à ébulition"
    }
}