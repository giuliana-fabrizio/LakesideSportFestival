package com.example.sae_s4

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.viewpager.widget.ViewPager
import com.example.sae_s4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout

//    // On below line we are creating variable for view pager, viewpager adapter and the image list.
    private lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
        // Get the drawer layout
        drawerLayout = binding.drawerLayout

        // Get the view pager
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupWithNavController(binding.menuView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        // initializing variables of below line with their id.
        viewPager = findViewById(R.id.idViewPager)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    override fun onPause() {
        super.onPause()
        // Récupérer la position actuelle du slide et la stocker dans les préférences partagées
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = prefs.edit()
        editor.putInt("current_slide_position", viewPager.currentItem)
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        // Récupérer la dernière position du slide à partir des préférences partagées et la définir sur le slide
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val position = prefs.getInt("current_slide_position", 0)
        viewPager.setCurrentItem(position, false)
    }

    override fun onStop() {
        super.onStop()
        // Récupérer la dernière position du slide à partir des préférences partagées et la définir sur le slide
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val position = prefs.getInt("current_slide_position", 0)
        viewPager.setCurrentItem(position, false)
    }
}
