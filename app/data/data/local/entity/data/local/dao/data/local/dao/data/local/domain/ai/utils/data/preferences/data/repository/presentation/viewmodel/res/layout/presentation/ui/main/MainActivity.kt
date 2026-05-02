class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            AppRepository(AppDatabase.getInstance(this).companyDao, AppDatabase.getInstance(this).itemDao, SettingsManager(this)),
            SettingsManager(this)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.navHostFragment)
        binding.navView.setupWithNavController(navController)
        ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.open, R.string.close).apply {
            syncState()
        }

        binding.fab.setOnClickListener {
            // Navigate to add screen based on current destination
            when (navController.currentDestination?.id) {
                R.id.companyListFragment -> navController.navigate(R.id.action_companyListFragment_to_addCompanyDialog)
                R.id.itemListFragment -> navController.navigate(R.id.action_itemListFragment_to_addItemDialog)
            }
        }

        // Apply dark mode on launch
        if (SettingsManager(this).isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}
