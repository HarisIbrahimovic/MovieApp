package com.sirahi.movieapp.repository.default

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.sirahi.movieapp.data.firebase.Rating
import com.sirahi.movieapp.repository.RatingRepository
import javax.inject.Inject

class DefaultRatingRepository
@Inject constructor(
        private val firebaseAuth: FirebaseAuth
) :RatingRepository {

    private val movieRating  = MutableLiveData<List<Rating>>()
    private val userName  = MutableLiveData<String>()

    override fun getMovieRatings(id:Int): LiveData<List<Rating>> {
        setMovieRatings(id)
        return movieRating
    }

    override fun getUserName():LiveData<String>  {
        setUserName()
        return userName
    }

    override fun setUserName()  {
        val dbRef = FirebaseDatabase.getInstance().getReference("Users").child(firebaseAuth.currentUser!!.uid).child("username")
        dbRef.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userName.value=snapshot.getValue(String::class.java)
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    override fun addMovieRating(movieId: Int, rating: Rating) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Ratings").child("Movies").child(movieId.toString())
        dbRef.push().setValue(rating)
    }


    override fun setMovieRatings(id: Int) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Ratings").child("Movies").child(id.toString())
        dbRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val arrayList = ArrayList<Rating>()
                for(dataSnapshot in snapshot.children){
                    dataSnapshot.getValue(Rating::class.java)?.let { arrayList.add(it) }
                    movieRating.postValue(arrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}