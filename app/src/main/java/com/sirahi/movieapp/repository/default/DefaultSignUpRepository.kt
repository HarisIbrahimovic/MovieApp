package com.sirahi.movieapp.repository.default

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.common.util.concurrent.ListenableFuture
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.sirahi.movieapp.presentation.util.RegError
import com.sirahi.movieapp.presentation.util.RegistrationStatus
import com.sirahi.movieapp.repository.SignUpRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

class DefaultSignUpRepository
@Inject
constructor(private val auth: FirebaseAuth) : SignUpRepository {

    override suspend fun registerUser(username: String, email: String, password: String): Boolean {
        val task = auth.createUserWithEmailAndPassword(email, password)
        task.await()
        return if (task.isSuccessful) {
            addUser(username, email, password)
            true
        } else false
    }

    override suspend fun loginUser(email: String, password: String): Boolean {
        val task = auth.signInWithEmailAndPassword(email, password)
        task.await()
        return task.isSuccessful
    }

    override suspend fun checkUser(): Boolean {
        return auth.currentUser != null
    }

    override suspend fun addUser(username: String, email: String, password: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Users")
        val map = HashMap<String, String>()
        map["id"] = auth.currentUser!!.uid
        map["username"] = username
        map["email"] = email
        map["password"] = password
        dbRef.child(auth.currentUser!!.uid).setValue(map)
    }

}