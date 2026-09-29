package exo1

class PasswordValidator : Validator{
    override fun validate(value: String): Boolean {
        if (value.length<8){
            return false
        }
        return true
    }
}