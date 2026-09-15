package exo1

/**
 * Classe englobant des Personnes ou des Entreprises
 *
 * @property nom le nom de la personne ou de l'entreprise
 */
open class PersonneA(protected open val nom: String) {

    companion object {

        /**
         * Méthode permettant de retourner une Personne ou une Entreprise à partir
         * de l'analyse d'une seule chaine de caractères qui doit respecter une format précis
         *
         * @param texteSaisi le texte a analyser
         * @return une Personne ou une Entreprise
         * @throws PersonneException si le chaine [texteSaisi] le respecte pas l'un des formats attendus
         * et donc, ne permet pas de créer une Personne ou une Entreprise
         */
        fun donnePersonneSaisie(texteSaisi: String): PersonneA {
            var copi = texteSaisi
            var estEnt = false
            var nomEntr= false
            var prenomEntr = false
            var nom  : String =""
            var prenom : String =""
            if (texteSaisi.isEmpty() or texteSaisi.isBlank()){
                throw PersonneException("")
            }
            var idx = 0
            for (i in copi){
                if (i == ' '){
                    idx++
                    continue
                }
                if (idx==0){
                    nom += i
                }
                if (idx == 1){
                    prenom += i
                }
            }
            if (nom.isFullUpperCase() and prenom.isFullUpperCase() ){
                if (nom.length>1){
                    estEnt = true
                }
            }

            if (estEnt){
                if (CategorieEntreprise.entries.any() {it.name == nom}){

                    return Entreprise(prenom , CategorieEntreprise.valueOf(nom))
                }
                else{
                    //throw PersonneException("nom de societe incorrect")
                }
            }
            if (!nom.hasAnUppercaseLetterFirst() and !prenom.hasAnUppercaseLetterFirst()){
                throw PersonneException("")
            }
            if (nom.hasAnUppercaseLetterFirst() and prenom.isFullUpperCase() ){
                if (prenom.length >1){
                    val r = nom
                    nom = prenom
                    prenom = r
                }
            }

            return Personne(nom, prenom)
        }
    }
}