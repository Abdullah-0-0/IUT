package exo2.strategy

import exo2.Foyer

class CalculImpotCelibataire( foyer : Foyer) : CalculImpot(foyer) {
    override fun impotsSurLeRevenu(): Int {
        val res   = foyer.revenuAnnuel - (0.1 * foyer.revenuAnnuel) * (1/3)
        return  res.toInt()
    }

    override fun taxeHabitation(): Int {
        return  (2* foyer.loyerMensuel + 100).toInt()
    }
}