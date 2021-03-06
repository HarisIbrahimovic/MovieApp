package com.sirahi.movieapp.view.fragment.menu

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
import com.sirahi.movieapp.databinding.FragmentSearchBinding
import com.sirahi.movieapp.presentation.MenuViewModel
import com.sirahi.movieapp.view.adapters.VerticalMediaAdapter
import com.sirahi.movieapp.view.fragment.util.navigateTo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), VerticalMediaAdapter.OnVerticalMediaClicked {

    private val viewModel: MenuViewModel by activityViewModels()
    private lateinit var navController: NavController
    private lateinit var binding: FragmentSearchBinding
    private lateinit var vAdapter: VerticalMediaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vAdapter = VerticalMediaAdapter(requireContext(), this)
        navController = Navigation.findNavController(view)
        binding.adapter = vAdapter
        observe()
    }

    private fun observe() {
        viewModel.searchData.observe(viewLifecycleOwner, {
            it.data?.let {list -> vAdapter.setList(list) }
        })
    }

    override fun onVerticalItemClicked(id: Int, type: String) {
        if (type != "tv") navigateTo(navController,R.id.action_searchFragment_to_movieDetailsFragment,"movieId",id)
        else navigateTo(navController,R.id.action_searchFragment_to_TVDetailsFragment,"tvId",id)
    }

}