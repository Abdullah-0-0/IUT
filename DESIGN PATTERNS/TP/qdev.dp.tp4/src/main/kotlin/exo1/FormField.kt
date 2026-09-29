package exo1

import java.awt.Color
import javax.swing.JTextField
import javax.swing.event.DocumentListener

class FormField(
    validator: Validator,
    text: String = ""
) : JTextField(text,15) {

    /* NE PAS MODIFIER */
     private val validator : Validator
    init {
        this.getDocument().addDocumentListener(FormFieldObserver())
        this.validator = validator
    }

    fun checkText() {
        if (isTextValid()) {
            println("'$text' is valid")
            foreground = java.awt.Color.BLACK
        } else {
            println("***** '$text' is NOT valid")
            foreground = Color.RED
        }
    }

    fun getInfo() : String = "FormField with value $text"

    inner class FormFieldObserver : DocumentListener {
        override fun insertUpdate(e: javax.swing.event.DocumentEvent?) {
            checkText()
        }

        override fun removeUpdate(e: javax.swing.event.DocumentEvent?) {
            checkText()
        }

        override fun changedUpdate(e: javax.swing.event.DocumentEvent?) {
            checkText()
        }
    }
    /* FIN NE PAS MODIFIER */
    fun isTextValid() : Boolean {
        return validator.validate(this.text)
    }

    companion object {
        fun buildUsernameField(value: String = "") : FormField {
            return FormField(UsernameValidator(),value)
        }
        fun buildPasswordField(value: String = "") : FormField {
            return FormField(PasswordValidator(),value)
        }

        fun buildNumericField(value: String = "") : FormField {
            return FormField(NumericValidator(),value)
        }

        fun buildEmailField(value: String = "") : FormField {
            return FormField(EmailValidator(),value)
        }

    }
}