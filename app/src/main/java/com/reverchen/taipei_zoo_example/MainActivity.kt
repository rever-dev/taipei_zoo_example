package com.reverchen.taipei_zoo_example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.reverchen.taipei_zoo_example.Repository.RepositoryImpl
import com.reverchen.taipei_zoo_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    lateinit var repo: RepositoryImpl
    lateinit var vb: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        vb = ActivityMainBinding.inflate(layoutInflater)
    
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment?
        val navController = navHostFragment?.navController ?: return
        AppBarConfiguration(navController.graph)
    }
}