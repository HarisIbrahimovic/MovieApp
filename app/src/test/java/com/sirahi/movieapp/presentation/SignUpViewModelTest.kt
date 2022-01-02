package com.sirahi.movieapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sirahi.movieapp.repository.fake.FakeSignUpRepository
import com.sirahi.movieapp.presentation.util.RegistrationStatus
import com.sirahi.movieapp.presentation.util.Constants
import com.sirahi.movieapp.presentation.util.RegError
import com.google.common.truth.Truth.assertThat
import java.lang.StringBuilder
import org.junit.Before
import org.junit.Rule
import org.junit.Test



class SignUpViewModelTest{

    private lateinit var viewModel: SignUpViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        viewModel = SignUpViewModel(FakeSignUpRepository())
    }

    @Test
    fun checkEmptyFieldUsername(){
        viewModel.checkEmpty("","email","admin123")
        val value = viewModel.getSignInData().getOrAwaitValueTest()
        val compValue = RegError.EmptyField(field = "username")
        assertThat(value.error?.field).isEqualTo(compValue.field)
        assertThat(value.error?.mess).isEqualTo(compValue.mess)
    }

    @Test
    fun checkEmptyFieldEmail(){
        viewModel.checkEmpty("username","","admin123")
        val value = viewModel.getSignInData().getOrAwaitValueTest()
        val compValue = RegError.EmptyField(field = "email")
        assertThat(value.error?.field).isEqualTo(compValue.field)
        assertThat(value.error?.mess).isEqualTo(compValue.mess)
    }

    @Test
    fun checkEmptyFieldPassword(){
        viewModel.checkEmpty("username","admin@gmail.com","")
        val value = viewModel.getSignInData().getOrAwaitValueTest()
        val compValue = RegError.EmptyField(field = "password")
        assertThat(value.error?.field).isEqualTo(compValue.field)
        assertThat(value.error?.mess).isEqualTo(compValue.mess)
    }

    @Test
    fun checkLengthPasswordShort(){
        val password = StringBuilder()
        for(i in 0 until Constants.UP_MIN){
            password.append("c")
        }
        viewModel.checkLength("adminadmin",password.toString())
        val value = viewModel.getSignInData().getOrAwaitValueTest()
        val compValue = RegError.TooShort(field = "password")
        assertThat(value.error?.field).isEqualTo(compValue.field)
        assertThat(value.error?.mess).isEqualTo(compValue.mess)
    }

    @Test
    fun checkLengthPasswordLong(){
        val password = StringBuilder()
        for(i in 0 until Constants.UP_MAX){
            password.append("c")
        }
        viewModel.checkLength("adminadmin",password.toString())
        val value = viewModel.getSignInData().getOrAwaitValueTest()
        val compValue = RegError.TooLong(field = "password")
        assertThat(value.error?.field).isEqualTo(compValue.field)
        assertThat(value.error?.mess).isEqualTo(compValue.mess)
    }

    @Test
    fun checkLengthUsernameShort(){
        val username = StringBuilder()
        for(i in 0 until Constants.UP_MIN){
            username.append("c")
        }
        viewModel.checkLength(username.toString(),"admin123")
        val value = viewModel.getSignInData().getOrAwaitValueTest()
        val compValue = RegError.TooShort(field = "username")
        assertThat(value.error?.field).isEqualTo(compValue.field)
        assertThat(value.error?.mess).isEqualTo(compValue.mess)
    }

    @Test
    fun checkLengthUsernameLong(){
        val username = StringBuilder()
        for(i in 0 until Constants.UP_MAX){
            username.append("c")
        }
        viewModel.checkLength(username.toString(),"admin123")
        val value = viewModel.getSignInData().getOrAwaitValueTest()
        val compValue = RegError.TooLong(field = "username")
        assertThat(value.error?.field).isEqualTo(compValue.field)
        assertThat(value.error?.mess).isEqualTo(compValue.mess)
    }

    @Test
    fun loginEmptyField(){
        viewModel.loginUser("","admin123")
        val value = viewModel.getSignInData().getOrAwaitValueTest()
        val compValue = RegError.EmptyField(field = "email")
        assertThat(value.error?.field).isEqualTo(compValue.field)
        assertThat(value.error?.mess).isEqualTo(compValue.mess)
    }

    @Test
    fun loginSuccess(){
        viewModel.loginUser("admin@gmail.com","admin123")
        val value = viewModel.getSignInData().getOrAwaitValueTest()
        assertThat(value).isEqualTo(RegistrationStatus.Success)
    }

    @Test
    fun loginFailed(){
        viewModel.loginUser("admin@gmail.com","wrongPassword")
        val compValue = RegError.UnknownError()
        val value = viewModel.getSignInData().getOrAwaitValueTest()
        assertThat(value.error?.mess).isEqualTo(compValue.mess)
    }

    @Test
    fun registerEmptyField(){
        viewModel.registerUser("","admin@gmail.com","wrongPassword")
        val compValue = RegError.EmptyField(field = "username")
        val value = viewModel.getSignInData().getOrAwaitValueTest()
        assertThat(value.error?.field).isEqualTo(compValue.field)
        assertThat(value.error?.mess).isEqualTo(compValue.mess)
    }

    @Test
    fun registerShortInput(){
        val username = StringBuilder()
        for(i in 0 until Constants.UP_MIN-1){
            username.append("c")
        }
        viewModel.registerUser(username.toString(),"admin@gmail.com","admin123")
        val compValue = RegError.TooShort(field = "username")
        val value = viewModel.getSignInData().getOrAwaitValueTest()
        assertThat(value.error?.field).isEqualTo(compValue.field)
        assertThat(value.error?.mess).isEqualTo(compValue.mess)
    }

    @Test
    fun registerLongInput(){
        val username = StringBuilder()
        for(i in 0 until Constants.UP_MAX+1){
            username.append("c")
        }
        viewModel.registerUser(username.toString(),"admin@gmail.com","admin123")
        val compValue = RegError.TooLong(field = "username")
        val value = viewModel.getSignInData().getOrAwaitValueTest()
        assertThat(value.error?.field).isEqualTo(compValue.field)
        assertThat(value.error?.mess).isEqualTo(compValue.mess)
    }


    @Test
    fun registerSuccess(){
        viewModel.registerUser("admin123","admin@gmail.com","admin123")
        val value = viewModel.getSignInData().getOrAwaitValueTest()
        assertThat(value).isEqualTo(RegistrationStatus.Success)
    }
}

