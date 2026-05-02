@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val companyId: Long,
    val name: String,
    val brand: String,
    val price: Double,
    val createdAt: Long = System.currentTimeMillis()
)
