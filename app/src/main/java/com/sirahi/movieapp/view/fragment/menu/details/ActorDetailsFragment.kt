package com.sirahi.movieapp.view.fragment.menu.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sirahi.movieapp.R
import com.sirahi.movieapp.databinding.FragmentActorDetailsBinding
import com.sirahi.movieapp.databinding.FragmentMovieDetailsBinding
import com.sirahi.movieapp.presentation.ActorDetailsViewModel
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingActorMovieCredits
import com.sirahi.movieapp.view.adapters.ActorMovieCreditsAdapter
import com.sirahi.movieapp.view.adapters.MovieResultAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActorDetailsFragment : Fragment(),MovieResultAdapter.ClickListener {

    private val viewModel : ActorDetailsViewModel by viewModels()
    private lateinit var binding: FragmentActorDetailsBinding
    private lateinit var adapter: ActorMovieCreditsAdapter

    private var navController: NavController? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_actor_details,container,false)
        binding.lifecycleOwner=this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        adapter = ActorMovieCreditsAdapter(requireContext(),this)
        binding.movieAdapter = adapter
        arguments?.getInt("actorId")?.let { viewModel.setActorData(it) }


        viewModel.actorCredits.observe(viewLifecycleOwner,{
            when(it){
                is IncomingActorMovieCredits.Success->adapter.setList(it.data)
                is IncomingActorMovieCredits.Failure->if(it.data!=null)adapter.setList(it.data)
                else -> Unit
            }
        })

    }

    override fun onMovieClikced(id: Int) {
        val bundle = bundleOf("movieId" to id)
        navController?.navigate(R.id.action_actorDetailsFragment_to_movieDetailsFragment, bundle)
    }

}