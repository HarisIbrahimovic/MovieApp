package com.sirahi.movieapp.view.fragment.menu.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sirahi.movieapp.R
import com.sirahi.movieapp.databinding.FragmentTVDetailsBinding
import com.sirahi.movieapp.presentation.TvDetailsViewModel
import com.sirahi.movieapp.view.adapters.MovieCastAdapter
import com.sirahi.movieapp.view.fragment.util.navigateTo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TVDetailsFragment : Fragment(), MovieCastAdapter.ActorClickListener {

    private val viewModel: TvDetailsViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var binding: FragmentTVDetailsBinding
    private lateinit var castAdapter: MovieCastAdapter
    private lateinit var popupMenu: PopupMenu


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
        binding.fragment = this
        navController = Navigation.findNavController(view)
        arguments?.getInt("tvId")?.let { viewModel.initData(it) }
        setPopupMenu()
    }

    fun showMenu() {
        popupMenu.show()
    }


    private fun setPopupMenu() {
        popupMenu = PopupMenu(requireContext(), binding.tvDetailsMenu)
        val inflater: MenuInflater = popupMenu.menuInflater
        inflater.inflate(R.menu.media_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.addRating -> {
                    addRating()
                    true
                }
                R.id.addToFavorites -> {
                    viewModel.addToFavorites()
                    true
                }
                R.id.addToWatchlist -> {
                    viewModel.addToWatchList()
                    true
                }
                else -> false
            }
        }
    }

    private fun addRating() {
        val bundle = bundleOf(
            "movieId" to arguments?.getInt("tvId"),
            "movieName" to binding.movieFullNameDetails.text.toString(),
            "type" to "Tv"
        )
        navController.navigate(R.id.action_TVDetailsFragment_to_ratingFragment, bundle)
    }


    override fun onActorClicked(id: Int) {
        navigateTo(
            navController,
            R.id.action_TVDetailsFragment_to_actorDetailsFragment,
            "actorId",
            id
        )
    }
}