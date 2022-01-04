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
import com.sirahi.movieapp.databinding.FragmentTVDetailsBinding
import com.sirahi.movieapp.presentation.TvDetailsViewModel
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMovieCast
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingTvDetails
import com.sirahi.movieapp.view.adapters.MovieCastAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TVDetailsFragment : Fragment(), MovieCastAdapter.ActorClickListener {

    private val viewModel: TvDetailsViewModel by viewModels()
    private lateinit var binding: FragmentTVDetailsBinding
    private var navController: NavController? = null
    private lateinit var popupMenu: PopupMenu

    private lateinit var castAdapter: MovieCastAdapter

    private var tvShowImage: String = "No image"
    private var tvScore: Double = 0.0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_t_v_details, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        castAdapter = MovieCastAdapter(this, requireContext())
        binding.adapter = castAdapter
        navController = Navigation.findNavController(view)
        arguments?.getInt("tvId")?.let { viewModel.initData(it) }
        observe()
        setPopupMenu()
        onClicks()
    }

    private fun onClicks() {
        binding.tvDetailsMenu.setOnClickListener {
            popupMenu.show()
        }
    }

    private fun observe() {

        viewModel.tvCastDetails.observe(viewLifecycleOwner, {
            when (it) {
                is IncomingMovieCast.Success -> castAdapter.setList(it.data)
                is IncomingMovieCast.Failure -> castAdapter.setList(it.data)
                else -> Unit
            }
        })

        viewModel.tvDetails.observe(viewLifecycleOwner, {
            when (it) {
                is IncomingTvDetails.Success -> {
                    tvShowImage = it.data?.posterPath ?: ""
                    tvScore = it.data?.voteAverage ?: 0.0
                }
                is IncomingTvDetails.Failure -> {
                    if (it.data != null) {
                        tvShowImage = it.data.posterPath
                        tvScore = it.data.voteAverage
                    }
                }
                else -> Unit
            }
        })
    }


    private fun setPopupMenu() {
        popupMenu = PopupMenu(requireContext(), binding.tvDetailsMenu)
        val inflater: MenuInflater = popupMenu.menuInflater
        inflater.inflate(R.menu.media_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.addRating -> {
                    val bundle = bundleOf(
                        "movieId" to arguments?.getInt("tvId"),
                        "movieName" to binding.movieFullNameDetails.text.toString(),
                        "type" to "Tv"
                    )
                    navController?.navigate(R.id.action_TVDetailsFragment_to_ratingFragment, bundle)
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

    private fun addToWatchlist() {
        viewModel.addToWatchList(
            arguments?.getInt("tvId") ?: 0,
            binding.movieFullNameDetails.text.toString(),
            tvScore,
            tvShowImage
        )
        Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
    }

    private fun addToFavorites() {
        viewModel.addToFavorites(
            arguments?.getInt("tvId") ?: 0,
            binding.movieFullNameDetails.text.toString(),
            tvScore,
            tvShowImage
        )
        Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
    }

    override fun onActorClicked(id: Int) {
        val bundle = bundleOf("actorId" to id)
        navController?.navigate(R.id.action_TVDetailsFragment_to_actorDetailsFragment, bundle)
    }
}