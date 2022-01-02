package com.sirahi.movieapp.view.fragment.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.sirahi.movieapp.R
import com.sirahi.movieapp.databinding.FragmentSearchBinding
import com.sirahi.movieapp.presentation.MenuViewModel
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaData
import com.sirahi.movieapp.view.adapters.VerticalMediaAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(),VerticalMediaAdapter.OnVerticalMediaClicked {

    private var _binding:FragmentSearchBinding?=null
    private val binding get()=_binding!!

    private var navController: NavController? = null
    private val viewModel:MenuViewModel by activityViewModels()
    private lateinit var vAdapter:VerticalMediaAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding= FragmentSearchBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vAdapter = VerticalMediaAdapter(requireContext(),this)
        navController = Navigation.findNavController(view)
        setUpView()
        observe()
    }

    private fun setUpView() {
        binding.searchRecyclerView.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter=vAdapter
        }
        binding.searchEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onSearch(text.toString())
        }

    }

    private fun observe(){
        viewModel.searchData.observe(viewLifecycleOwner, {
            when (it) {
                is IncomingMediaData.Loading -> binding.searchProgressBar.visibility = View.VISIBLE
                is IncomingMediaData.Success -> {
                    binding.searchProgressBar.visibility=View.GONE
                    vAdapter.setList(it.data)
                }
                is IncomingMediaData.Failure -> {
                    binding.searchProgressBar.visibility=View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    override fun onDestroy() {
        _binding=null
        super.onDestroy()
    }

    override fun onVerticalItemClicked(id: Int, type: String) {
        if(type!="tv"){
            val bundle = bundleOf("movieId" to id)
            navController?.navigate(R.id.action_searchFragment_to_movieDetailsFragment, bundle)
        }else{
            val bundle = bundleOf("tvId" to id)
            navController?.navigate(R.id.action_searchFragment_to_TVDetailsFragment, bundle)
        }
    }


}