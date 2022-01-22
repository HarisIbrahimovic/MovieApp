package com.sirahi.movieapp.view.fragment.menu

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sirahi.movieapp.R
import com.sirahi.movieapp.databinding.FragmentHomeBinding
import com.sirahi.movieapp.presentation.MenuViewModel
import com.sirahi.movieapp.view.adapters.GenreAdapter
import com.sirahi.movieapp.view.adapters.MovieResultAdapter
import com.sirahi.movieapp.view.adapters.TvResultAdapter
import com.sirahi.movieapp.view.adapters.VerticalMediaAdapter
import com.sirahi.movieapp.view.fragment.util.navigateTo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), MovieResultAdapter.ClickListener, GenreAdapter.ClickListener,
    TvResultAdapter.TvClickListener, VerticalMediaAdapter.OnVerticalMediaClicked {

    private val viewModel: MenuViewModel by activityViewModels()
    private lateinit var navController: NavController
    private lateinit var binding: FragmentHomeBinding
    private lateinit var discoverAdapter: VerticalMediaAdapter
    private lateinit var mAdapter: MovieResultAdapter
    private lateinit var tvAdapter: TvResultAdapter
    private lateinit var genreAdapter: GenreAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setAdapters()
        observe()
    }

    private fun setAdapters() {
        discoverAdapter = VerticalMediaAdapter(requireContext(), this)
        tvAdapter = TvResultAdapter(requireContext(), this)
        mAdapter = MovieResultAdapter(requireContext(), this)
        genreAdapter = GenreAdapter(requireContext(), this)
        binding.discoverAdapter = discoverAdapter
        binding.movieResultAdapter = mAdapter
        binding.tvResultAdapter = tvAdapter
        binding.genreAdapter = genreAdapter
        binding.fragment = this
    }

    private fun observe() {
        viewModel.genreList.observe(viewLifecycleOwner, {
            genreAdapter.setList(it)
        })

        viewModel.discoverData.observe(viewLifecycleOwner, {
            it.data?.let { list -> discoverAdapter.setList(list) }
        })
    }

    fun openCredits(){
        val myDialog = AlertDialog.Builder(activity)
        val inflater = LayoutInflater.from(activity)
        val myView = inflater.inflate(R.layout.credits_custom_view, null)
        myDialog.setView(myView)
        val dialog: AlertDialog = myDialog.create()
        dialog.show()
    }

    override fun genreClicked(id: Int) {
        viewModel.setSelectedGenre(id)
    }

    override fun onMovieClikced(id: Int) {
        navigateTo(navController, R.id.action_homeFragment_to_movieDetailsFragment, "movieId", id)
    }

    override fun onTvClicked(id: Int) {
        navigateTo(navController, R.id.action_homeFragment_to_TVDetailsFragment, "tvId", id)
    }

    override fun onVerticalItemClicked(id: Int, type: String) {
        navigateTo(navController, R.id.action_homeFragment_to_movieDetailsFragment, "movieId", id)
    }
}
