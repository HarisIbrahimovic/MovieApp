package com.sirahi.movieapp.view.fragment.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sirahi.movieapp.R
import com.sirahi.movieapp.databinding.FragmentSearchBinding
import com.sirahi.movieapp.presentation.MenuViewModel
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaData
import com.sirahi.movieapp.view.adapters.VerticalMediaAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), VerticalMediaAdapter.OnVerticalMediaClicked {

    private val viewModel: MenuViewModel by activityViewModels()
    private lateinit var binding: FragmentSearchBinding
    private var navController: NavController? = null

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
        onTextChanged()
        observe()
    }

    private fun onTextChanged() {
        binding.searchEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onSearch(text.toString())
        }
    }

    private fun observe() {
        viewModel.searchData.observe(viewLifecycleOwner, {
            when (it) {
                is IncomingMediaData.Success -> it.data?.let { list -> vAdapter.setList(list) }
                is IncomingMediaData.Failure -> Toast.makeText(
                    requireContext(),
                    it.error,
                    Toast.LENGTH_SHORT
                ).show()
                else -> Unit
            }
        })
    }


    override fun onVerticalItemClicked(id: Int, type: String) {
        if (type != "tv") {
            val bundle = bundleOf("movieId" to id)
            navController?.navigate(R.id.action_searchFragment_to_movieDetailsFragment, bundle)
        } else {
            val bundle = bundleOf("tvId" to id)
            navController?.navigate(R.id.action_searchFragment_to_TVDetailsFragment, bundle)
        }
    }

}