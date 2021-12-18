package com.sirahi.movieapp.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.sirahi.movieapp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.navigationBarColor = ContextCompat.getColor(applicationContext, R.color.black)
        lifecycleScope.launch{
            delay(1500)
            startActivity(Intent(applicationContext,SignInActivity::class.java))
            finish()
         }
    }
}