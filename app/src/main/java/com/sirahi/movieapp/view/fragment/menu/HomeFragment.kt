package com.sirahi.movieapp.view.fragment.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sirahi.movieapp.databinding.FragmentHomeBinding
import com.sirahi.movieapp.presentation.MenuViewModel
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaData
import com.sirahi.movieapp.view.activity.MovieDetailsActivity
import com.sirahi.movieapp.view.adapters.GenreAdapter
import com.sirahi.movieapp.view.adapters.MovieResultAdapter
import com.sirahi.movieapp.view.adapters.TvResultAdapter
import com.sirahi.movieapp.view.adapters.VerticalMediaAdapter


class HomeFragment : Fragment(),MovieResultAdapter.ClickListener,GenreAdapter.ClickListener {

    private lateinit var viewModel : MenuViewModel
    private var _binding  : FragmentHomeBinding?=null
    private val binding get() = _binding!!

    private lateinit var mAdapter:MovieResultAdapter
    private lateinit var discoverAdapter:VerticalMediaAdapter
    private lateinit var tvAdapter:TvResultAdapter
    private lateinit var genreAdapter:GenreAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MenuViewModel::class.java)
        mAdapter = MovieResultAdapter(requireContext(),this)
        tvAdapter = TvResultAdapter(requireContext())
        genreAdapter = GenreAdapter(requireContext(),this)
        discoverAdapter = VerticalMediaAdapter(requireContext())
        setUpRecyclerViews()
        observe()
    }

    private fun setUpRecyclerViews() {
        binding.popularMoviesRec.apply {
            adapter=mAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        }
        binding.popularTvShowsRecyclerView.apply {
            adapter = tvAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        }
        binding.recyclerViewGenreSelect.apply {
            adapter= genreAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        }
        binding.discoverRecyclerView.apply {
            adapter= discoverAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }

    }

    private fun observe() {

        viewModel.popularMoviesData.observe(viewLifecycleOwner, {
            when (it) {
                is IncomingMediaData.Loading ->
                    binding.firstProgressBar.visibility = View.VISIBLE
                is IncomingMediaData.Success -> {
                    binding.firstProgressBar.visibility = View.GONE
                    mAdapter.setList(it.data)
                }
                is IncomingMediaData.Failure -> {
                    binding.firstProgressBar.visibility = View.GONE
                    if (it.data != null) mAdapter.setList(it.data)
                }
            }
        })

        viewModel.popularTvData.observe(viewLifecycleOwner, {
            when (it) {
                is IncomingMediaData.Loading ->
                    binding.secondProgressBar.visibility = View.VISIBLE
                is IncomingMediaData.Success -> {
                    binding.secondProgressBar.visibility = View.GONE
                    tvAdapter.setList(it.data)
                }
                is IncomingMediaData.Failure -> {
                    binding.secondProgressBar.visibility = View.GONE
                    if (it.data != null) tvAdapter.setList(it.data)
                }
            }
        })

        viewModel.discoverData.observe(viewLifecycleOwner, {
            when (it) {
                is IncomingMediaData.Loading ->
                    binding.thirdProgressBar.visibility = View.VISIBLE
                is IncomingMediaData.Success -> {
                    binding.thirdProgressBar.visibility = View.GONE
                    discoverAdapter.setList(it.data)
                }
                is IncomingMediaData.Failure -> {
                    binding.thirdProgressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                    if (it.data != null) discoverAdapter.setList(it.data)
                }
            }
        })

        viewModel.genreList.observe(viewLifecycleOwner,{
            genreAdapter.setList(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onMovieClikced(id: Int) {
        val intent = Intent(requireActivity(),MovieDetailsActivity::class.java)
        intent.putExtra("id",id)
        startActivity(intent)
    }

    override fun genreClicked(id: Int) {
        viewModel.setSelectedGenre(id)
    }

}