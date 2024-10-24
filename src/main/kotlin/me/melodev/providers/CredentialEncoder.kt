package me.melodev.providers

import org.springframework.security.crypto.bcrypt.BCrypt

interface CredentialEncoder {
    fun encode(credential: String): String
}

class BCryptEncoder : CredentialEncoder {
    // @Todo: Change this to read from the environment variables
    companion object {
        private const val HASH_COST = 12
    }

    override fun encode(credential: String): String {
        return BCrypt.hashpw(credential, BCrypt.gensalt(HASH_COST))
    }
}