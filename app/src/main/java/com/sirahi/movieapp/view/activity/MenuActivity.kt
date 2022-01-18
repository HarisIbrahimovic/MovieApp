package com.sirahi.movieapp.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.sirahi.movieapp.R
import com.sirahi.movieapp.databinding.ActivityMenuBinding
import com.sirahi.movieapp.presentation.MenuViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuActivity : AppCompatActivity() {

    private val viewModel: MenuViewModel by viewModels()
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel
        window.navigationBarColor = ContextCompat.getColor(applicationContext, R.color.black)
        val navController = Navigation.findNavController(this, R.id.menuFrame)
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }

}