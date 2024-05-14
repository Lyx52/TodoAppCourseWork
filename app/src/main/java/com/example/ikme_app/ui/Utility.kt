package com.example.ikme_app.ui

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

class Utility {
    companion object {
        @JvmStatic
        fun hashPassword(password: String): String {
            val digest: MessageDigest = MessageDigest.getInstance("SHA-512")
            digest.update(password.toByteArray(StandardCharsets.UTF_8))
            return digest.digest().toString(StandardCharsets.UTF_8)
        }
    }
}