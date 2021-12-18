package com.sirahi.movieapp.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.sirahi.movieapp.R
import com.sirahi.movieapp.databinding.ActivitySignInBinding
import com.sirahi.movieapp.presentation.SignUpViewModel
import com.sirahi.movieapp.presentation.util.RegistrationStatus
import com.sirahi.movieapp.presentation.util.SignUpFragmentStatus
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        window.navigationBarColor = ContextCompat.getColor(applicationContext, R.color.black)
        observe()
    }

    private fun observe() {

        viewModel.getSignInData().observe(this,{
            when(it){
                is RegistrationStatus.Success->{
                    viewModel.setStatusToPending()
                    startActivity(Intent(applicationContext,MenuActivity::class.java))
                    finish()
                }
                else->Unit
            }
        })
    }


}