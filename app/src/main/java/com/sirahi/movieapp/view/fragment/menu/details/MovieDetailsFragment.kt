package com.sirahi.movieapp.view.fragment.menu.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sirahi.movieapp.R
import com.sirahi.movieapp.databinding.FragmentMovieDetailsBinding
import com.sirahi.movieapp.presentation.MovieDetailsViewModel
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMovieCast
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMovieDetails
import com.sirahi.movieapp.view.adapters.MovieCastAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(),MovieCastAdapter.ActorClickListener{

    private var navController: NavController? = null
    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var popupMenu:PopupMenu

    private lateinit var castAdapter:MovieCastAdapter
    private var movieImageSrc:String = "No image"
    private var movieScore:Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_movie_details,container,false)
        binding.lifecycleOwner=this
        binding.viewModel=viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController =  Navigation.findNavController(view)
        arguments?.getInt("movieId")?.let { viewModel.init(it) }
        castAdapter = MovieCastAdapter(this,requireContext())
        binding.adapter = castAdapter
        setPopupMenu()
        observe()
        onClicks()
    }

    private fun onClicks() {
        binding.menuMovieDetails.setOnClickListener {
            popupMenu.show()
        }
    }

    private fun observe() {
        viewModel.movieDetails.observe(viewLifecycleOwner, {
            if (it is IncomingMovieDetails.Success) {
                movieImageSrc = it.data?.posterPath ?: "No Image"
                movieScore = it.data?.voteAverage ?: 0.0
            }
        })

        viewModel.movieCast.observe(viewLifecycleOwner,{incomingData->
            when(incomingData){
                is IncomingMovieCast.Success->castAdapter.setList(incomingData.data)
                is IncomingMovieCast.Failure->incomingData.data.let { castAdapter.setList(it) }
                else->Unit
            }
        })
    }

    private fun setPopupMenu() {
        popupMenu = PopupMenu(requireContext(),binding.menuMovieDetails)
        val inflater:MenuInflater = popupMenu.menuInflater
        inflater.inflate(R.menu.media_menu,popupMenu.menu)
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.addRating->{
                    val bundle = bundleOf("movieId" to arguments?.getInt("movieId"), "movieName" to binding.movieFullNameDetails.text.toString(), "type" to "Movies")
                    navController?.navigate(R.id.action_movieDetailsFragment_to_ratingFragment, bundle)
                    true
                }
                R.id.addToFavorites->{
                    addToFavorites()
                    true
                }
                R.id.addToWatchlist->{
                    addToWatchlist()
                    true
                }
                else-> false
            }
        }
    }

    private fun addToWatchlist() {
        viewModel.addMovieToWatchlist(arguments?.getInt("movieId") ?: 0, binding.movieFullNameDetails.text.toString(), movieScore, movieImageSrc)
        Toast.makeText(requireContext(),"Successfully added",Toast.LENGTH_SHORT).show()
    }

    private fun addToFavorites() {
        viewModel.addMovieToFavorites(arguments?.getInt("movieId") ?: 0, binding.movieFullNameDetails.text.toString(), movieScore, movieImageSrc)
        Toast.makeText(requireContext(),"Successfully added",Toast.LENGTH_SHORT).show()
    }


    override fun onActorClicked(id: Int) {
        val bundle = bundleOf("actorId" to id)
        navController?.navigate(R.id.action_movieDetailsFragment_to_actorDetailsFragment, bundle)
    }

}