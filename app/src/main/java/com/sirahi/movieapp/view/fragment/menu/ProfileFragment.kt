package com.sirahi.movieapp.view.fragment.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.sirahi.movieapp.R
import com.sirahi.movieapp.databinding.FragmentProfileBinding
import com.sirahi.movieapp.presentation.MenuViewModel
import com.sirahi.movieapp.view.adapters.MovieResultAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(),MovieResultAdapter.ClickListener {



    private lateinit var binding:FragmentProfileBinding
    private val viewModel:MenuViewModel by activityViewModels()
    private lateinit var adapter1:MovieResultAdapter
    private lateinit var adapter2:MovieResultAdapter
    private lateinit var adapter3:MovieResultAdapter
    private lateinit var adapter4:MovieResultAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter1 = MovieResultAdapter(requireContext(),this)
        adapter2 = MovieResultAdapter(requireContext(),this)
        adapter3 = MovieResultAdapter(requireContext(),this)
        adapter4 = MovieResultAdapter(requireContext(),this)
        binding.watchTvRecView.adapter=adapter1
        binding.watchlistMRecyclerView.adapter=adapter2
        binding.watchTvRecView.adapter=adapter3
        binding.watchTvRecView.adapter=adapter4

        viewModel.listWatch.observe(viewLifecycleOwner,{
            if(it!=null){
                val list = it.map { it.toMediaResult() }
                adapter1.setList(list)
                adapter2.setList(list)
                adapter3.setList(list)
                adapter4.setList(list)
                Toast.makeText(requireContext(),list.toString(),Toast.LENGTH_SHORT).show()
            }
        })


    }

    override fun onMovieClikced(id: Int) {


    }
}