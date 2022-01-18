package com.sirahi.movieapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirahi.movieapp.model.LoginUiState
import com.sirahi.movieapp.presentation.usecases.signup.SignUpUseCase
import com.sirahi.movieapp.presentation.util.RegistrationStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel
@Inject
constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private var signInLiveData = MutableLiveData<RegistrationStatus>()
    fun getSignInData(): LiveData<RegistrationStatus> = signInLiveData

    val loginUiState = LoginUiState()

    init {
        checkUser()
    }

    private fun checkUser() = viewModelScope.launch(Dispatchers.IO) {
        signInLiveData.postValue(
            signUpUseCase.checkUser()
        )
    }

    fun registerUser() = viewModelScope.launch(Dispatchers.IO) {
        signInLiveData.postValue(RegistrationStatus.Loading)
        signInLiveData.postValue(
            signUpUseCase.registerUser(loginUiState.email,loginUiState.username, loginUiState.password)
        )
    }

    fun loginUser() = viewModelScope.launch(Dispatchers.IO) {
        signInLiveData.postValue(RegistrationStatus.Loading)
        signInLiveData.postValue(
            signUpUseCase.loginUser(loginUiState.email, loginUiState.password)
        )
    }

    fun resetValues() {
        loginUiState.setValues()
        signInLiveData.postValue(RegistrationStatus.Pending)
    }

}

