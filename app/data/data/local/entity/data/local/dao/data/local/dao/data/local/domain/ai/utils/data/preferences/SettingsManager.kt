class SettingsManager(context: Context) {
    private val prefs = context.getSharedPreferences("ratebook_settings", Context.MODE_PRIVATE)

    var appName: String
        get() = prefs.getString("app_name", "Rate Book Pro") ?: "Rate Book Pro"
        set(value) = prefs.edit().putString("app_name", value).apply()

    var currency: String
        get() = prefs.getString("currency", "৳") ?: "৳"
        set(value) = prefs.edit().putString("currency", value).apply()

    var isDarkMode: Boolean
        get() = prefs.getBoolean("dark_mode", false)
        set(value) = prefs.edit().putBoolean("dark_mode", value).apply()

    var adminPasswordHash: String?
        get() = prefs.getString("admin_hash", null)
        set(value) = prefs.edit().putString("admin_hash", value).apply()
}
