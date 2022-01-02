package com.sirahi.movieapp.view.fragment.menu.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sirahi.movieapp.R
import com.sirahi.movieapp.databinding.FragmentMovieDetailsBinding
import com.sirahi.movieapp.databinding.FragmentTVDetailsBinding
import com.sirahi.movieapp.presentation.MovieDetailsViewModel
import com.sirahi.movieapp.presentation.TvDetailsViewModel
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMovieCast
import com.sirahi.movieapp.view.adapters.MovieCastAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TVDetailsFragment : Fragment(),MovieCastAdapter.ActorClickListener {

    private val viewModel: TvDetailsViewModel by viewModels()
    private lateinit var binding: FragmentTVDetailsBinding
    private var navController: NavController? = null
    private lateinit var popupMenu: PopupMenu

    private lateinit var castAdapter: MovieCastAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_t_v_details,container,false)
        binding.viewModel= viewModel

        binding.lifecycleOwner=this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        castAdapter = MovieCastAdapter(this,requireContext())
        binding.adapter=castAdapter
        navController =  Navigation.findNavController(view)
        arguments?.getInt("tvId")?.let { viewModel.initData(it) }
        viewModel.tvCastDetails.observe(viewLifecycleOwner, {
            if (it is IncomingMovieCast.Success) castAdapter.setList(it.data)
            if (it is IncomingMovieCast.Failure) castAdapter.setList(it.data)
        })
    }

    override fun onActorClicked(id: Int) {
        val bundle = bundleOf("actorId" to id)
        navController?.navigate(R.id.action_TVDetailsFragment_to_actorDetailsFragment, bundle)
    }
}