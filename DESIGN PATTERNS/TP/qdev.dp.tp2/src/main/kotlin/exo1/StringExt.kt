package exo1

/**
 * méthode indiquant si la chaine de caractère est uniquement composée de
 * lettres en majuscules
 *
 * @return [true] si la chaine est en majuscule
 */
fun String.isFullUpperCase(): Boolean {
    val listeInt = arrayOf(0,1,2,3,4,5,6,7,8,9)
    if (this.isEmpty()){
        return false
    }
    for ( i in listeInt){
        if (this.contains(i.toString())){
            return false
        }
    }

    return this.uppercase() == this
}

/**
 * Méthode indiquant si la chaine de caractères est composée d'un premier
 * caractère en majuscule, suivi des autres caractères en minuscules
 *
 * @return [true] si la chaine respecte le format
 */
fun String.hasAnUppercaseLetterFirst(): Boolean {
    if (this[0].isUpperCase()){
        for (i in 1 until length){
            if (this[i].isUpperCase()){
                return false
            }
        }
        return true
    }
    return false
}

