package exo2.strategy

import exo2.Foyer


class CalculImpotCoupleAvecEnfants(foyer: Foyer) : CalculImpot(foyer) {
    override fun impotsSurLeRevenu(): Int {
        val res = foyer.revenuAnnuel - (0.2 * foyer.revenuAnnuel - (750*foyer.nbEnfants)) * (1/(4 + foyer.nbEnfants))
        return  res.toInt()
    }

    override fun taxeHabitation(): Int {
        val res = 1.5 * foyer.loyerMensuel - (50*foyer.nbEnfants)
        return  res.toInt()
    }
}