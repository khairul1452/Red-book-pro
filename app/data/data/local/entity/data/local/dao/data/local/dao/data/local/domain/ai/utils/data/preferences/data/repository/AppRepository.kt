class AppRepository(
    private val companyDao: CompanyDao,
    private val itemDao: ItemDao,
    private val settings: SettingsManager
) {
    val companies = companyDao.getAll()
    fun getItems(companyId: Long) = itemDao.getByCompany(companyId)
    fun searchItems(companyId: Long, query: String) = itemDao.search(companyId, query)

    suspend fun addCompany(company: CompanyEntity): Long = companyDao.insert(company)
    suspend fun updateCompany(company: CompanyEntity) = companyDao.update(company)
    suspend fun deleteCompany(company: CompanyEntity) = companyDao.delete(company)

    suspend fun addItem(item: ItemEntity): Long = itemDao.insert(item)
    suspend fun updateItem(item: ItemEntity) = itemDao.update(item)
    suspend fun deleteItem(item: ItemEntity) = itemDao.delete(item)

    suspend fun getCompany(id: Long) = companyDao.getById(id)
    suspend fun getAllItemsForAi() = itemDao.getByCompany(0).first() // Placeholder, adjust as needed
}
