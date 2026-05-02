object OfflineAiEngine {
    // Fuzzy search using Levenshtein distance threshold
    fun fuzzyMatch(query: String, target: String, threshold: Int = 2): Boolean {
        if (query.isEmpty() || target.isEmpty()) return false
        val q = query.lowercase().trim()
        val t = target.lowercase().trim()
        if (t.contains(q)) return true
        return levenshtein(q, t) <= threshold
    }

    private fun levenshtein(a: String, b: String): Int {
        val matrix = Array(a.length + 1) { IntArray(b.length + 1) }
        for (i in 0..a.length) matrix[i][0] = i
        for (j in 0..b.length) matrix[0][j] = j
        for (i in 1..a.length) {
            for (j in 1..b.length) {
                val cost = if (a[i - 1] == b[j - 1]) 0 else 1
                matrix[i][j] = minOf(
                    matrix[i - 1][j] + 1,
                    matrix[i][j - 1] + 1,
                    matrix[i - 1][j - 1] + cost
                )
            }
        }
        return matrix[a.length][b.length]
    }

    // Auto-suggest price based on historical median
    fun suggestPrice(itemName: String, brand: String, history: List<ItemEntity>): Double? {
        val similar = history.filter {
            fuzzyMatch(itemName, it.name, 3) || fuzzyMatch(brand, it.brand, 2)
        }
        if (similar.isEmpty()) return null
        val prices = similar.map { it.price }.sorted()
        return if (prices.size % 2 == 0) {
            (prices[prices.size / 2 - 1] + prices[prices.size / 2]) / 2.0
        } else {
            prices[prices.size / 2]
        }
    }

    // Duplicate company detection
    fun isDuplicate(newName: String, existing: List<CompanyEntity>): Boolean {
        return existing.any { it.name.trim().equals(newName.trim(), ignoreCase = true) ||
                fuzzyMatch(newName, it.name, 2) }
    }
}
