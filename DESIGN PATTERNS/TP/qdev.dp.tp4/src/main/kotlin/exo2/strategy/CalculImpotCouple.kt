package exo2.strategy

import exo2.Foyer


class CalculImpotCouple(foyer : Foyer) : CalculImpot(foyer) {
    override fun impotsSurLeRevenu(): Int {
        val res = foyer.revenuAnnuel - (0.2 * foyer.revenuAnnuel) * (1/4)
        return  res.toInt()
    }

    override fun taxeHabitation(): Int {
        return  (2*foyer.loyerMensuel).toInt()
    }
}