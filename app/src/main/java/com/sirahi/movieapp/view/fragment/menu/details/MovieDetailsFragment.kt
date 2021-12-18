package com.sirahi.movieapp.view.fragment.menu.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sirahi.movieapp.R
import com.sirahi.movieapp.data.remote.util.ApiConstants
import com.sirahi.movieapp.databinding.FragmentMovieDetailsBinding
import com.sirahi.movieapp.databinding.FragmentRegisterBinding
import com.sirahi.movieapp.presentation.MovieDetailsViewModel
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMovieCast
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMovieDetails
import com.sirahi.movieapp.view.adapters.MovieCastAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var castAdapter:MovieCastAdapter
    private var _binding: FragmentMovieDetailsBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt("movieId")?.let { viewModel.init(it) }
        castAdapter = MovieCastAdapter(requireContext())
        binding.starsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL,false)
            adapter = castAdapter
        }


        viewModel.movieCast.observe(viewLifecycleOwner,{incomingData->
            when(incomingData){
                is IncomingMovieCast.Loading->{}
                is IncomingMovieCast.Success->{
                    castAdapter.setList(incomingData.data)
                }
                is IncomingMovieCast.Failure->{
                    incomingData.data.let {
                        castAdapter.setList(it)
                    }
                }
            }
        })

        viewModel.movieDetails.observe(viewLifecycleOwner,{incomingData->
            when(incomingData){
                is IncomingMovieDetails.Loading->{}
                is IncomingMovieDetails.Success->{
                    val data = incomingData.data
                    binding.movieTitleDetails.text=data.title
                    binding.movieFullNameDetails.text=data.title
                    binding.movieOverview.text = data.overview
                    binding.ratingBar.progress = data.voteAverage.toInt()
                    Glide.with(requireContext()).load(ApiConstants.URL_START+data.posterPath)
                        .apply(RequestOptions.centerCropTransform())
                        .into(binding.movieImage)
                    Glide.with(requireContext()).load(ApiConstants.URL_START+data.backdropPath)
                        .apply(RequestOptions.centerCropTransform())
                        .into(binding.backdropPathImage)
                }
                is IncomingMovieDetails.Failure->{
                    incomingData.data.let {
                        binding.movieTitleDetails.text=it?.title
                        binding.movieFullNameDetails.text=it?.title
                        binding.movieOverview.text = it?.overview
                        binding.ratingBar.progress = it?.voteAverage?.toInt() ?: 0
                        Glide.with(requireContext()).load(ApiConstants.URL_START+it?.posterPath)
                            .apply(RequestOptions.centerCropTransform())
                            .into(binding.movieImage)
                        Glide.with(requireContext()).load(ApiConstants.URL_START+it?.backdropPath)
                            .apply(RequestOptions.centerCropTransform())
                            .into(binding.backdropPathImage)
                    }
                    Toast.makeText(requireContext(),incomingData.error, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}