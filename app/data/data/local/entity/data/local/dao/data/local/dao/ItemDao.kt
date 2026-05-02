@Dao
interface ItemDao {
    @Query("SELECT * FROM items WHERE companyId = :companyId ORDER BY createdAt DESC")
    fun getByCompany(companyId: Long): Flow<List<ItemEntity>>

    @Query("SELECT * FROM items WHERE companyId = :companyId AND (name LIKE '%' || :query || '%' OR brand LIKE '%' || :query || '%')")
    fun search(companyId: Long, query: String): Flow<List<ItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ItemEntity): Long

    @Update
    suspend fun update(item: ItemEntity)

    @Delete
    suspend fun delete(item: ItemEntity)
}
