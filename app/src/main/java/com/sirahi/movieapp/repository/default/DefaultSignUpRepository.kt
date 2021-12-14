package com.sirahi.movieapp.repository.default

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.sirahi.movieapp.presentation.util.RegError
import com.sirahi.movieapp.presentation.util.RegistrationStatus
import com.sirahi.movieapp.repository.SignUpRepository

class DefaultSignUpRepository :SignUpRepository{

    private val auth = FirebaseAuth.getInstance()
    private val signInLiveData = MutableLiveData<RegistrationStatus>()
    override fun getRegistrationLiveData()=signInLiveData

    override fun registerUser(username: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
            if(it.isSuccessful){
                addUser(username,email,password)
            }
            else signInLiveData.value=RegistrationStatus.Failure(RegError.UnknownError())
        }
    }

    override fun addUser(username: String, email: String, password: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Users")
        val map = HashMap<String,String>()
        map["id"] = auth.currentUser!!.uid
        map["username"]=username
        map["email"]=email
        map["password"]=password
        dbRef.child(auth.currentUser!!.uid).setValue(map)
        signInLiveData.value=RegistrationStatus.Success
    }

    override fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
            if(it.isSuccessful)
                signInLiveData.value=RegistrationStatus.Success
            else signInLiveData.value=RegistrationStatus.Failure(RegError.UnknownError("Incorrect email or password"))
        }
    }

    override fun checkUser() {
        if(auth.currentUser!=null)signInLiveData.value=RegistrationStatus.Success
    }

}