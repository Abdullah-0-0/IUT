package exo2.strategy

import exo2.Foyer

class CalculImpotCelibataireAvecEnfants(foyer: Foyer) : CalculImpot(foyer) {
    override fun impotsSurLeRevenu(): Int {
        val res = foyer.revenuAnnuel - (0.12 * foyer.revenuAnnuel-1000) * (1/3 +foyer.nbEnfants)
        return  res.toInt()
    }

    override fun taxeHabitation(): Int {
        val res = foyer.loyerMensuel -  (75 * foyer.nbEnfants)
        return  res.toInt()
    }
}