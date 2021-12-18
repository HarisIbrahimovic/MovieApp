package com.sirahi.movieapp.repository.fake

import androidx.lifecycle.MutableLiveData
import com.sirahi.movieapp.presentation.util.RegError
import com.sirahi.movieapp.presentation.util.RegistrationStatus
import com.sirahi.movieapp.repository.SignUpRepository

class FakeSignUpRepository:SignUpRepository {

    private val signInLiveData = MutableLiveData<RegistrationStatus>()

    override fun registerUser(username: String, email: String, password: String) {
        if(email=="admin@gmail.com"&&password=="admin123")
            signInLiveData.value=RegistrationStatus.Success
        else signInLiveData.value = RegistrationStatus.Failure(RegError.UnknownError())
    }

    override fun getRegistrationLiveData(): MutableLiveData<RegistrationStatus> {
        signInLiveData.value=RegistrationStatus.Pending
        return signInLiveData
    }

    override fun addUser(username: String, email: String, password: String) {

    }

    override fun loginUser(email: String, password: String) {
        if(email=="admin@gmail.com"&&password=="admin123")
            signInLiveData.value=RegistrationStatus.Success
        else signInLiveData.value = RegistrationStatus.Failure(RegError.UnknownError())
    }

    override fun checkUser() {
        signInLiveData.value=RegistrationStatus.Pending
    }

}