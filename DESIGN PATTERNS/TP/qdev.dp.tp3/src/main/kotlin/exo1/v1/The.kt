package exo1.v1

class The : BoissonEauChaude() {
    //    fun suivreRecette(supplément : Boolean)  : String {
//            var str = faireBouillirEau()
//            str += infuserThe()
//            str += verserDansTasse()
//            if (supplément)
//                str +=  ajouterCitron()
//            return str
//    }
    override fun preparer(): String {
        return "ébulitionInfusion du théRemplissage"
    }

    override fun ajouterSupplement(): String {
        return "Ajout du citron"
    }


    //    private fun faireBouillirEau() = "Portage de l'eau à ébulition"
//
//    private fun infuserThe() = "Infusion du thé"
//
//    private fun verserDansTasse()  = "Remplissage de la tasse"
//
//    private fun ajouterCitron()  = "Ajout du citron"
}