package com.sirahi.movieapp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.sirahi.movieapp.R
import com.sirahi.movieapp.databinding.ActivityMenuBinding
import com.sirahi.movieapp.presentation.MenuViewModel
import com.sirahi.movieapp.presentation.util.MenuStatus
import com.sirahi.movieapp.view.fragment.menu.HomeFragment
import com.sirahi.movieapp.view.fragment.menu.SearchFragment

class MenuActivity : AppCompatActivity() {

    private lateinit var viewModel:MenuViewModel
    private lateinit var binding:ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.navigationBarColor = ContextCompat.getColor(applicationContext, R.color.black)
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        observe()
        setUpNavigation()
    }

    private fun observe() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_item -> viewModel.setFragmentStatus(MenuStatus.HOME)
                R.id.search_item -> viewModel.setFragmentStatus(MenuStatus.SEARCH)
                R.id.now_playing_item -> viewModel.setFragmentStatus(MenuStatus.NOW_PLAYING)
                R.id.profile_item -> viewModel.setFragmentStatus(MenuStatus.PROFILE)
            }
            true
        }
    }

    private fun setUpNavigation() {
        viewModel.fragmentStatus.observe(this,{
            when(it){
                MenuStatus.HOME-> supportFragmentManager.beginTransaction().replace(R.id.menuFrame,HomeFragment()).commit()
                MenuStatus.SEARCH-> supportFragmentManager.beginTransaction().replace(R.id.menuFrame,SearchFragment()).commit()
                MenuStatus.NOW_PLAYING-> supportFragmentManager.beginTransaction().replace(R.id.menuFrame,HomeFragment()).commit()
                MenuStatus.PROFILE-> supportFragmentManager.beginTransaction().replace(R.id.menuFrame,HomeFragment()).commit()
                else->Unit
            }
        })
    }

}