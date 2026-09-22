package exo1.v2

abstract class BoissonChaude {
    fun suivreRecette(supplément : Boolean) : String {
        var str = faireBouillir()
        str += preparer()
        str += verserDansTasse()
        if (supplément)
            str += ajouterSupplement()
        return str
    }
    abstract fun faireBouillir(): String
    abstract fun preparer(): String
    private fun verserDansTasse(): String = "Remplissage de la tasse"
    abstract fun ajouterSupplement(): String
}