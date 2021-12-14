package com.sirahi.movieapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.sirahi.movieapp.R
import com.sirahi.movieapp.data.local.LocalDatabase
import com.sirahi.movieapp.data.local.entity.people.ActorEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.navigationBarColor = ContextCompat.getColor(applicationContext, R.color.black)
        LocalDatabase.setInstance(applicationContext)
        lifecycleScope.launch{
            delay(1500)
            startActivity(Intent(applicationContext,SignInActivity::class.java))
            finish()
         }
    }
}