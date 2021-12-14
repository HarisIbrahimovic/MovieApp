package com.sirahi.movieapp.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sirahi.movieapp.R
import com.sirahi.movieapp.data.remote.util.ApiConstants
import com.sirahi.movieapp.databinding.ActivityMovieDetailsBinding
import com.sirahi.movieapp.presentation.MovieDetailsViewModel
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMovieCast
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMovieDetails

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.navigationBarColor = ContextCompat.getColor(applicationContext, R.color.black)
        viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        viewModel.init(intent.getIntExtra("id",-1))


        viewModel.movieCast.observe(this,{incomingData->
            when(incomingData){
                is IncomingMovieCast.Loading->{}
                is IncomingMovieCast.Success->{
                }
                is IncomingMovieCast.Failure->{
                    incomingData.data.let {
                        Toast.makeText(applicationContext,it.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        viewModel.movieDetails.observe(this,{incomingData->
            when(incomingData){
                is IncomingMovieDetails.Loading->{}
                is IncomingMovieDetails.Success->{
                    val data = incomingData.data
                    binding.movieTitleDetails.text=data.title
                    binding.overviewMovieDetails.text = data.overview
                    binding.ratingBarMD.progress = data.voteAverage.toInt()
                    Glide.with(applicationContext).load(ApiConstants.URL_START+data.posterPath)
                        .apply(RequestOptions.centerCropTransform())
                        .into(binding.detailedMovieImage)
                }
                is IncomingMovieDetails.Failure->{
                    incomingData.data.let {
                        binding.movieTitleDetails.text=it?.title
                        binding.overviewMovieDetails.text = it?.overview
                        binding.ratingBarMD.progress = it?.voteAverage?.toInt() ?: 0
                        Glide.with(applicationContext).load(ApiConstants.URL_START+it?.posterPath)
                            .apply(RequestOptions.centerCropTransform())
                            .into(binding.detailedMovieImage)
                    }
                    Toast.makeText(applicationContext,incomingData.error,Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}