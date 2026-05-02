@Entity(tableName = "companies")
data class CompanyEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val location: String,
    val phone: String,
    val logoUri: String? = null,
    val passwordHash: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
