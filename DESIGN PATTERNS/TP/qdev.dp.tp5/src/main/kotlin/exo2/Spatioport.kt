package exo2

/**
 * classe implémentant un spatioport.
 * @param nbPlaces le nombre de places dans le spatioport.
 * @throws Spatioport si le nombre de places est incorrect.
 */
class Spatioport(nbPlaces : Int) : UsageSpatioport {

    private var dock = Dock(nbPlaces, elements = Array(nbPlaces) { LIBRE })
    override fun nbPlacesTotales(): Int {
        return dock.size
    }

    override fun nbPlacesLibres(): Int {
        var res = 0
        for (i in 0 until dock.size) {
            if (dock.get(i) is LIBRE){
                res++
            }
        }
        return res
    }

    override fun emplacementInutilisable(place: Int) {
        if (place<0 || place>dock.size){
            throw PlaceInexistanteException("emplacementInutilisable")
        }
        if(dock.get(place) is LIBRE){
            dock.set(place, INUTILISABLE)
        }
        else if(dock.get(place) is Vaisseau){
            throw PlaceInutilisableException("emplacementInutilisable")
        }
        else{
            throw PlaceOccupeeException("emplacement déjà occupée")
        }

    }

    override fun reparerEmplacement(place: Int) {
        if (place<0 || place>dock.size){
            throw PlaceInexistanteException("emplacement inexistante")
        }
        when (dock.get(place)) {
            //INUTILISABLE -> throw PlaceInutilisableException("emplacementInutilisable")
            LIBRE -> throw PlaceLibreException("emplacement libre")
            is Vaisseau -> throw PlaceOccupeeException("emplacement occupe")
        }
    }

    override fun nbPlacesInutilisables(): Int {
        var res = 0
        for (i in 0 until dock.size) {
            if (dock.get(i) is INUTILISABLE){
                res++
            }
        }
        return res
    }

    override fun estAmarreA(vaisseau: Vaisseau): Int {
        for (i in 0 until dock.size) {
            if (dock.get(i) is Vaisseau){
                if (dock.get(i) == vaisseau){
                    return i
                }
            }
        }
        return -1
    }

    override fun amarrer(vaisseau: Vaisseau) {
        for (i in 0 until dock.size) {
            if (dock.get(i) is LIBRE){
                dock.set(i,vaisseau)
                break
            }
        }
    }

    override fun nbPlacesOccupees(): Int {
        var res = 0
        for (i in 0 until dock.size) {
            if (dock.get(i) is Vaisseau){
                res++
            }
        }
        return res
    }

    override fun tonnagesTotalAmarres(): Int {
        var res = 0
        for (i in 0 until dock.size){
            var vaisseau = dock.get(i)
            if (vaisseau is Vaisseau){
                res += vaisseau.tonnage()
            }
        }
        return res
    }

    override fun desamarrer(vaisseau: Vaisseau) {
        if (estAmarreA(vaisseau) == -1){
            throw VaisseauDejaAmarreException("")
        }
        else{
            dock.set(estAmarreA(vaisseau), LIBRE)
        }
    }

    override fun amarrerA(vaisseau: Vaisseau, place: Int) {
        if (place<0 || place>dock.size){
            throw PlaceInexistanteException("emplacement inexistante")
        }
        for (i in 0 until dock.size){
            if (dock.get(i) == vaisseau){
                throw VaisseauDejaAmarreException("")
            }
            when (dock.get(i) ) {
                is Vaisseau -> throw PlaceOccupeeException("emplacement occupe")
                INUTILISABLE -> throw PlaceInutilisableException("emplacement inutilisable")
            }
        }
        dock.set(place,vaisseau)
    }

    override fun desamarrerDe(place: Int): Vaisseau {
        if (place<0 || place>dock.size){
            throw PlaceInexistanteException("emplacement inexistante")
        }
        val v = dock.get(place)
        when (v) {
            LIBRE ->throw PlaceLibreException("emplacement libre")
            INUTILISABLE -> throw  PlaceInutilisableException("emplacement inutilisable")
        }
        dock.set(place, LIBRE)
        return  v as Vaisseau
    }

    override fun amarrerAuPlusPresDe(vaisseau: Vaisseau, place: Int) {
//        if (place<0 || place>dock.size){
//            throw PlaceInexistanteException("emplacement inexistante")
//        }
//        if (nbPlacesLibres()==0){
//            throw SpatioportPleinException(" aucun place libre")
//        }
//        if (estAmarreA(vaisseau) != -1){
//            throw VaisseauDejaAmarreException("deja amarrée")
//        }
//        if (dock.get(place) is Vaisseau || dock.get(place) is INUTILISABLE){
//            if ((dock.get(place+1) is Vaisseau || dock.get(place+1) is INUTILISABLE)){
//                if((dock.get(place-1) is Vaisseau || dock.get(place-1) is INUTILISABLE))
//            }
//            else{
//                dock.set(place, vaisseau)
//            }
//        }
//        else{
//            dock.set(place, vaisseau)
//        }
        TODO()
    }

    override fun vaisseauxTriesParNom(): List<Vaisseau> {
        TODO("Not yet implemented")
    }

    override fun vaisseauxTriesParTonnageDecroissantPuisNom(): List<Vaisseau> {
        TODO("Not yet implemented")
    }
}