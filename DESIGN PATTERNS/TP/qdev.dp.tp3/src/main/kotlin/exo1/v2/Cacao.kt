package exo1.v2

class Cacao : BoissonChaude() {
    override fun faireBouillir(): String = "Portage du lait à ébulition"

    override fun preparer(): String = "Mélange du lait et du cacao"

    override fun ajouterSupplement(): String = "Ajout de chantilly"
}