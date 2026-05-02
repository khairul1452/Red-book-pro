object SecurityUtils {
    fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val md = java.security.MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

    fun verifyPassword(input: String, storedHash: String?): Boolean {
        if (storedHash.isNullOrEmpty()) return true // No password set
        return hashPassword(input) == storedHash
    }
}
