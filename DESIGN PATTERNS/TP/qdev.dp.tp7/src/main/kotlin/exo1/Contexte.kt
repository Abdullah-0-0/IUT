package exo1

class Contexte(c : Classe? = null, s :Saison? = null, d : Demande? =null) {
    val classe = c
    val saison = s
    val demande = d

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Contexte

        if (classe != other.classe) return false
        if (saison != other.saison) return false
        if (demande != other.demande) return false

        return true
    }

    override fun hashCode(): Int {
        var result = classe?.hashCode() ?: 0
        result = 31 * result + (saison?.hashCode() ?: 0)
        result = 31 * result + (demande?.hashCode() ?: 0)
        return result
    }


}