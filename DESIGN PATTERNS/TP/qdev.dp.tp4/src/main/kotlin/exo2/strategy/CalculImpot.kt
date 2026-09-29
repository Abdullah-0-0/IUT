package exo2.strategy

import exo2.Foyer
import exo2.Impot
import exo2.SITUATION

abstract class CalculImpot  protected constructor (val foyer: Foyer) : Impot{
    companion object{
        fun donneCalculImpotStrategy(foyer: Foyer) : CalculImpot {
            if (foyer.nbEnfants>=1){
                when(foyer.situation){
                    SITUATION.Celibataire -> return CalculImpotCelibataire(foyer)
                    SITUATION.Couple -> return CalculImpotCoupleAvecEnfants(foyer)
                }
            }else{
                if (foyer.situation == SITUATION.Couple){
                    return CalculImpotCouple(foyer)
                }
            }
            return CalculImpotCelibataireAvecEnfants(foyer)
        }
    }
}
