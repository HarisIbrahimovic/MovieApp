package com.sirahi.movieapp.view.fragment.menu

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sirahi.movieapp.R
import com.sirahi.movieapp.databinding.AdvancedSearchBinding
import com.sirahi.movieapp.databinding.FragmentSearchBinding
import com.sirahi.movieapp.presentation.MenuViewModel
import com.sirahi.movieapp.presentation.util.incomingdata.IncomingMediaData
import com.sirahi.movieapp.view.adapters.GenreAdapter
import com.sirahi.movieapp.view.adapters.VerticalMediaAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(),GenreAdapter.ClickListener {

    private var _binding:FragmentSearchBinding?=null
    private val binding get()=_binding!!

    private val viewModel:MenuViewModel by activityViewModels()
    private lateinit var vAdapter:VerticalMediaAdapter
    private lateinit var gAdapter: GenreAdapter

    private val groupParent:ViewGroup?=null
    private lateinit var dialog: AlertDialog
    private lateinit var myView: View
    private lateinit var advancedSearchBinding: AdvancedSearchBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding= FragmentSearchBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.clearGenre()
        vAdapter = VerticalMediaAdapter(requireContext())
        gAdapter = GenreAdapter(requireContext(),this)
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
        binding.advancedSettings.setOnClickListener {
            openAdvancedSearch()
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
        viewModel.genreList.observe(viewLifecycleOwner,{
            gAdapter.setList(it)
        })
    }

    override fun onDestroy() {
        _binding=null
        viewModel.removeGenreSelection()
        super.onDestroy()
    }

    override fun genreClicked(position: Int) {
        viewModel.setAdvancedGenre(position)
    }

    private fun openAdvancedSearch() {
        val myDialog= AlertDialog.Builder(activity)
        val inflater = LayoutInflater.from(activity)

        myView = inflater.inflate(R.layout.advanced_search, groupParent)
        myDialog.setView(myView)
        dialog = myDialog.create()
        dialog.let {
            it.show()
            advancedSearchBinding= AdvancedSearchBinding.bind(myView)
            advancedSearchBinding.advancedRecview.apply {
                adapter=gAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

    }



}