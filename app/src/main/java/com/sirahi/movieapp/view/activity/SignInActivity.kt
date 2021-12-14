package com.sirahi.movieapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.sirahi.movieapp.R
import com.sirahi.movieapp.databinding.ActivitySignInBinding
import com.sirahi.movieapp.presentation.SignUpViewModel
import com.sirahi.movieapp.presentation.util.RegistrationStatus
import com.sirahi.movieapp.presentation.util.SignUpFragmentStatus
import com.sirahi.movieapp.view.fragment.signup.LoginFragment
import com.sirahi.movieapp.view.fragment.signup.RegisterFragment
import com.sirahi.movieapp.view.fragment.signup.StartLoginFragment

class SignInActivity : AppCompatActivity() {

    private lateinit var viewModel: SignUpViewModel
    private lateinit var binding: ActivitySignInBinding
    private lateinit var status:SignUpFragmentStatus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.navigationBarColor = ContextCompat.getColor(applicationContext, R.color.black)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        observe()
    }

    private fun observe() {
        viewModel.fragmentValue.observe(this,{
            when(it){
                SignUpFragmentStatus.START->supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, StartLoginFragment()).commit()
                SignUpFragmentStatus.LOGIN->supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,LoginFragment()).commit()
                SignUpFragmentStatus.REGISTER->supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,RegisterFragment()).commit()
                else-> Unit
            }
            status=it
        })
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

    override fun onBackPressed() {
        if(status!=SignUpFragmentStatus.START)
            viewModel.setFragment(SignUpFragmentStatus.START)
        else finish()
    }
}