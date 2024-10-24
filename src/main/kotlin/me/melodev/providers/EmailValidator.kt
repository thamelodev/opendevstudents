package me.melodev.providers

interface EmailValidator {
    fun isInvalid(email: String): Boolean
}

/**
 * This email validator uses RFC-5322 regex for email validation
 */
class RFCEMailValidator : EmailValidator {
    override fun isInvalid(email: String): Boolean {
        return !Regex("^[a-zA-Z0-9_!#\$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+")
            .matches(email)
    }
}

