package com.sirahi.movieapp.presentation.util.bindingadapters

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.sirahi.movieapp.R
import com.sirahi.movieapp.presentation.util.RegError
import com.sirahi.movieapp.presentation.util.RegistrationStatus

@BindingAdapter("checkSignUpStatus")
fun checkSignUpStatus(view: Button, regStatus: RegistrationStatus){
    when(regStatus){
        is RegistrationStatus.Loading->view.text=""
        else->view.text= view.context.getString(R.string.sign_in)
    }
}

@BindingAdapter("checkSignUpStatus")
fun checkSignUpStatus(view:ProgressBar,regStatus: RegistrationStatus){
    when(regStatus){
        is RegistrationStatus.Loading->view.visibility= View.VISIBLE
        else->view.visibility = View.GONE
    }
}

@BindingAdapter("checkErrorEmail")
fun checkErrorEmail(view: TextInputLayout,regStatus: RegistrationStatus){
    checkErrorInField(view,regStatus,"email")
}

@BindingAdapter("checkErrorUserName")
fun checkErrorUserName(view: TextInputLayout,regStatus: RegistrationStatus){
    checkErrorInField(view,regStatus,"username")
}

@BindingAdapter("checkErrorPassword")
fun checkErrorPassword(view: TextInputLayout,regStatus: RegistrationStatus){
    checkErrorInField(view,regStatus,"password")
}

fun checkErrorInField(view: TextInputLayout,regStatus: RegistrationStatus,field:String){
    when(regStatus){
        is RegistrationStatus.Failure->{
            if(regStatus.error?.field==field){
                view.error = regStatus.error.mess
            }
        }
        else -> Unit
    }
}