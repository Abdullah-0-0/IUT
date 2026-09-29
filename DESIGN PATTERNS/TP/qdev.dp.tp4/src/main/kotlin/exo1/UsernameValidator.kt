package exo1

class UsernameValidator : Validator{
    override fun validate(value: String): Boolean {
        if (EmailValidator().validate(value)){
            return false
        }
        if (value.contains('@') or value.contains('.')){
            return false
        }
        if (value.isBlank() or value.isEmpty()){
            return false
        }

        return true
    }
}