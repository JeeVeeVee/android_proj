package android.example.artGallery

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.example.artGallery.databinding.ActivityMainBinding
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        setSupportActionBar(binding.myToolbar)

        //BottomNavBar
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = this.findNavController(R.id.myNavHostFragment)

        bottomNavigationView.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeKunstFragment, R.id.artistListFragment, R.id.favoritesFragment))
       setupActionBarWithNavController(navController, appBarConfiguration)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.Dutch -> {Toast.makeText(this,getString(R.string.taalveranderdnederlands),Toast.LENGTH_SHORT).show()
                setAppLocale("nl",this)
                recreate()}
            R.id.English -> {Toast.makeText(this,getString(R.string.taalveranderdengels),Toast.LENGTH_SHORT).show()
                setAppLocale("en",this)
                recreate()}
        }

        return super.onOptionsItemSelected(item)
    }

    fun setAppLocale(languageFromPreference: String?, context: Context)
    {

        if (languageFromPreference != null) {

            val resources: Resources = context.resources
            val dm: DisplayMetrics = resources.displayMetrics
            val config: Configuration = resources.configuration
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                config.setLocale(Locale(languageFromPreference.lowercase(Locale.ROOT)))
            } else {
                config.setLocale(Locale(languageFromPreference.lowercase(Locale.ROOT)))
            }
            resources.updateConfiguration(config, dm)
        }
    }
}