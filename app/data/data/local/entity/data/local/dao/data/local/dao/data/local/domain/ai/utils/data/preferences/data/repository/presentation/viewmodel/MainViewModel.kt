class MainViewModel(
    private val repository: AppRepository,
    private val settings: SettingsManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState

    val companies = repository.companies.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun toggleDarkMode(enabled: Boolean) {
        settings.isDarkMode = enabled
        AppCompatDelegate.setDefaultNightMode(
            if (enabled) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    fun getCurrency() = settings.currency
    fun getAppName() = settings.appName

    suspend fun checkCompanyDuplicate(name: String): Boolean {
        return OfflineAiEngine.isDuplicate(name, companies.value)
    }

    suspend fun suggestPrice(name: String, brand: String, companyId: Long): Double? {
        val history = repository.getItems(companyId).first()
        return OfflineAiEngine.suggestPrice(name, brand, history)
    }

    sealed class UiState {
        object Idle : UiState()
        data class Success(val msg: String) : UiState()
        data class Error(val msg: String) : UiState()
    }
}
