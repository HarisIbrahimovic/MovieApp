package com.sirahi.movieapp.view.fragment.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sirahi.movieapp.R
import com.sirahi.movieapp.databinding.FragmentHomeBinding
import com.sirahi.movieapp.presentation.MenuViewModel
import com.sirahi.movieapp.view.adapters.GenreAdapter
import com.sirahi.movieapp.view.adapters.MovieResultAdapter
import com.sirahi.movieapp.view.adapters.TvResultAdapter
import com.sirahi.movieapp.view.adapters.VerticalMediaAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), MovieResultAdapter.ClickListener, GenreAdapter.ClickListener,
    TvResultAdapter.TvClickListener, VerticalMediaAdapter.OnVerticalMediaClicked {

    private val viewModel: MenuViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding
    private var navController: NavController? = null

    private lateinit var discoverAdapter: VerticalMediaAdapter
    private lateinit var mAdapter: MovieResultAdapter
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var tvAdapter: TvResultAdapter

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

        lifecycleScope.launchWhenStarted {
            viewModel.popularMoviesFlow.collect {
                it.data?.let { list -> mAdapter.setList(list) }
            }
        }
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
    }

    private fun observe() {
        //viewModel.popularMoviesData.observe(viewLifecycleOwner, { if(it?.data != null)mAdapter.setList(it.data) })
        viewModel.popularTvData.observe(
            viewLifecycleOwner,
            { if (it?.data != null) tvAdapter.setList(it.data) })
        viewModel.discoverData.observe(
            viewLifecycleOwner,
            { if (it?.data != null) discoverAdapter.setList(it.data) })
        viewModel.genreList.observe(viewLifecycleOwner, { genreAdapter.setList(it) })
    }

    override fun onMovieClikced(id: Int) {
        val bundle = bundleOf("movieId" to id)
        navController?.navigate(R.id.action_homeFragment_to_movieDetailsFragment, bundle)
    }

    override fun genreClicked(id: Int) {
        viewModel.setSelectedGenre(id)
    }

    override fun onTvClicked(id: Int) {
        val bundle = bundleOf("tvId" to id)
        navController?.navigate(R.id.action_homeFragment_to_TVDetailsFragment, bundle)
    }

    override fun onVerticalItemClicked(id: Int, type: String) {
        val bundle = bundleOf("movieId" to id)
        navController?.navigate(R.id.action_homeFragment_to_movieDetailsFragment, bundle)
    }
}
