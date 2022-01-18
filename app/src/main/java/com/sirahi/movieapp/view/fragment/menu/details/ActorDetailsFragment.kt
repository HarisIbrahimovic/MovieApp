package com.sirahi.movieapp.view.fragment.menu.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sirahi.movieapp.R
import com.sirahi.movieapp.databinding.FragmentActorDetailsBinding
import com.sirahi.movieapp.presentation.ActorDetailsViewModel
import com.sirahi.movieapp.view.adapters.ActorMovieCreditsAdapter
import com.sirahi.movieapp.view.adapters.MovieResultAdapter
import com.sirahi.movieapp.view.fragment.util.navigateTo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActorDetailsFragment : Fragment(), MovieResultAdapter.ClickListener {

    private val viewModel: ActorDetailsViewModel by viewModels()
    private lateinit var binding: FragmentActorDetailsBinding
    private lateinit var adapter: ActorMovieCreditsAdapter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_actor_details, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("actorId")?.let { viewModel.setActorData(it) }
        adapter = ActorMovieCreditsAdapter(requireContext(), this)
        binding.movieAdapter = adapter
        binding.fragment = this
        navController = Navigation.findNavController(view)
    }


    override fun onMovieClikced(id: Int) {
        navigateTo(navController,R.id.action_actorDetailsFragment_to_movieDetailsFragment,"movieId",id)
    }

}