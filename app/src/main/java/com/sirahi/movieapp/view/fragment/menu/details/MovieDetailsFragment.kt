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
import com.sirahi.movieapp.view.adapters.MovieCastAdapter
import com.sirahi.movieapp.view.fragment.util.navigateTo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(), MovieCastAdapter.ActorClickListener {

    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var navController: NavController
    private lateinit var castAdapter: MovieCastAdapter
    private lateinit var popupMenu: PopupMenu

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        arguments?.getInt("movieId")?.let { viewModel.init(it) }
        castAdapter = MovieCastAdapter(this, requireContext())
        binding.adapter = castAdapter
        binding.fragment = this
        setPopupMenu()
    }

    fun showMenu() {
        popupMenu.show()
    }


    private fun setPopupMenu() {
        popupMenu = PopupMenu(requireContext(), binding.menuMovieDetails)
        val inflater: MenuInflater = popupMenu.menuInflater
        inflater.inflate(R.menu.media_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.addRating -> {
                    openRating()
                    true
                }
                R.id.addToFavorites -> {
                    addToFavorites()
                    true
                }
                R.id.addToWatchlist -> {
                    addToWatchlist()
                    true
                }
                else -> false
            }
        }
    }

    private fun openRating() {
        val bundle = bundleOf(
            "movieId" to arguments?.getInt("movieId"),
            "movieName" to viewModel.mDetails.title,
            "type" to "Movies"
        )
        navController.navigate(
            R.id.action_movieDetailsFragment_to_ratingFragment,
            bundle
        )
    }

    private fun addToWatchlist() {
        viewModel.addMovieToWatchlist()
        Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
    }

    private fun addToFavorites() {
        viewModel.addMovieToFavorites()
        Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
    }


    override fun onActorClicked(id: Int) {
        navigateTo(navController,R.id.action_movieDetailsFragment_to_actorDetailsFragment,"actorId",id)
    }

}