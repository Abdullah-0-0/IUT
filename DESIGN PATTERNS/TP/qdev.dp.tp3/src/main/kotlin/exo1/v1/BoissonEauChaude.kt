package exo1.v1

abstract class BoissonEauChaude {
    fun suivreRecette(supplément : Boolean): String{
        var str = faireBouillirEau()
         str += preparer()
         str += verserDansTaser()
         if (supplément)
                  str += ajouterSupplement()
        return str
    }
    private fun faireBouillirEau(): String = "Portage de l'eau à "
    abstract fun preparer(): String
    private fun verserDansTaser(): String = " de la tasse"
    abstract fun ajouterSupplement(): String

}