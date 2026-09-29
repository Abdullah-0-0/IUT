package exo1

class NumericValidator : Validator{
    override fun validate(value: String): Boolean {
        if (value.all { it.isDigit() }){
            if (value.isEmpty()){
                return false
            }
            return true
        }
        return false
    }
}