package com.sirahi.movieapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sirahi.movieapp.presentation.util.Constants
import com.sirahi.movieapp.presentation.util.RegError
import com.sirahi.movieapp.presentation.util.RegistrationStatus
import com.sirahi.movieapp.presentation.util.SignUpFragmentStatus
import com.sirahi.movieapp.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class SignUpViewModel @Inject constructor(private var repository: SignUpRepository) :ViewModel(){



    private var _signInLiveData:MutableLiveData<RegistrationStatus> = repository.getRegistrationLiveData()

    fun getSignInData():LiveData<RegistrationStatus> = _signInLiveData


    init {
        repository.checkUser()
    }

    fun registerUser(username:String, email:String, password:String){
        _signInLiveData.value=RegistrationStatus.Loading
        if(checkEmpty(username,email,password)) return
        if(checkLength(username, password)) return
        else repository.registerUser(username, email, password)
    }

    fun loginUser(email: String, password: String) {
        _signInLiveData.value=RegistrationStatus.Loading
        if(checkEmpty(email = email,password = password))
            return
        repository.loginUser(email, password)

    }

    fun checkLength(username: String, password: String): Boolean {
        if(username.length<=Constants.UP_MIN)
            _signInLiveData.value=RegistrationStatus.Failure(RegError.TooShort(field = "username"))
        if(password.length<=Constants.UP_MIN)
            _signInLiveData.value=RegistrationStatus.Failure(RegError.TooShort(field = "password"))
        if(username.length>=Constants.UP_MAX)
            _signInLiveData.value=RegistrationStatus.Failure(RegError.TooLong(field = "username"))
        if(password.length>=Constants.UP_MAX)
            _signInLiveData.value=RegistrationStatus.Failure(RegError.TooLong(field = "password"))
        if(username.length<Constants.UP_MIN||username.length>Constants.UP_MAX||password.length<Constants.UP_MIN||password.length>Constants.UP_MAX) {
            RegistrationStatus.Pending
            return true
        }
        return false
    }

    fun checkEmpty(username: String="username", email: String, password: String):Boolean {
        if(username.isEmpty())
            _signInLiveData.value=RegistrationStatus.Failure(RegError.EmptyField(field = "username"))
        if(email.isEmpty())
            _signInLiveData.value=RegistrationStatus.Failure(RegError.EmptyField(field = "email"))
        if(password.isEmpty())
            _signInLiveData.value=RegistrationStatus.Failure(RegError.EmptyField(field = "password"))
        if(username.isEmpty()||password.isEmpty()||email.isEmpty()){
          // setStatusToPending()
            return true
        }
        return false
    }

    fun setStatusToPending() {
        _signInLiveData.value=RegistrationStatus.Pending
    }

}