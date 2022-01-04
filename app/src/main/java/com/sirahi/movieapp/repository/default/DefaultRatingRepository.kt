package com.sirahi.movieapp.repository.default

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.sirahi.movieapp.data.firebase.Rating
import com.sirahi.movieapp.repository.RatingRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DefaultRatingRepository
@Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : RatingRepository {

    val movieRating = MutableLiveData<List<Rating>>()
    private val userName = MutableLiveData<String>()

    override fun getMovieRatings(id: Int, type: String): LiveData<List<Rating>> {
        return movieRating
    }

    override suspend fun getUserName(): LiveData<String> {
        setUserName()
        return userName
    }

    override fun setUserName() {
        val dbRef = FirebaseDatabase.getInstance().getReference("Users")
            .child(firebaseAuth.currentUser!!.uid).child("username")
        dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userName.value = snapshot.getValue(String::class.java)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    override suspend fun addMovieRating(movieId: Int, rating: Rating, type: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Ratings").child(type)
            .child(movieId.toString()).push()
        val task = dbRef.setValue(rating)
        task.await()
        if (task.isSuccessful) Log.d("SUCCESS", "USPJESNO")
    }


    override fun setMovieRatings(id: Int, type: String) {
        val dbRef =
            FirebaseDatabase.getInstance().getReference("Ratings").child(type).child(id.toString())
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val arrayList = ArrayList<Rating>()
                for (dataSnapshot in snapshot.children) {
                    dataSnapshot.getValue(Rating::class.java)?.let { arrayList.add(it) }
                    movieRating.postValue(arrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}