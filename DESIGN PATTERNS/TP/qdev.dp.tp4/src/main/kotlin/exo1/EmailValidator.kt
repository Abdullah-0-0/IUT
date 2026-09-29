package exo1

class EmailValidator : Validator{
    override fun validate(value: String): Boolean {
        if (value.contains('@') and value.contains('.')){
            return true
        }
        return false
    }
}

