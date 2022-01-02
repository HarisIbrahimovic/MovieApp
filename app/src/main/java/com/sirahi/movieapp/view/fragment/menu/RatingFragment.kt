package com.sirahi.movieapp.view.fragment.menu

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sirahi.movieapp.R
import com.sirahi.movieapp.databinding.FragmentRatingBinding
import com.sirahi.movieapp.databinding.RatingCustomViewBinding
import com.sirahi.movieapp.presentation.RatingViewModel
import com.sirahi.movieapp.view.adapters.RatingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RatingFragment : Fragment() {

    private val viewModel: RatingViewModel by viewModels()

    private lateinit var submitRatingBinding: RatingCustomViewBinding
    private lateinit var binding: FragmentRatingBinding
    private lateinit var ratingAdapter: RatingAdapter

    private lateinit var dialog: AlertDialog
    private lateinit var myView: View
    private val groupParent:ViewGroup?=null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_rating,container,false)
        binding.lifecycleOwner=this
        binding.movieName = arguments?.getString("movieName")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("movieId")?.let { viewModel.initData(it) }
        setUpRecyclerView()
        setRatingWindow()
        onClicks()
    }

    private fun onClicks() {
        binding.addRatingButton.setOnClickListener {
            dialog.let {
                it.show()
                addRatingViewOnClicks()
            }
        }
    }

    private fun setUpRecyclerView() {
        ratingAdapter = RatingAdapter(requireContext())
        binding.ratingRecView.adapter=ratingAdapter
        viewModel.movieRating.observe(viewLifecycleOwner,{
            if(it!=null)
                ratingAdapter.setList(it)
        })
    }

    private fun setRatingWindow(){
        val myDialog= AlertDialog.Builder(activity)
        val inflater = LayoutInflater.from(activity)
        myView = inflater.inflate(R.layout.rating_custom_view, groupParent)
        myDialog.setView(myView)
        dialog = myDialog.create()
    }

    private fun addRatingViewOnClicks() {
        submitRatingBinding= RatingCustomViewBinding.bind(myView)
        submitRatingBinding.cancelButton.setOnClickListener { dialog.dismiss() }
        submitRatingBinding.submitRatingButton.setOnClickListener {
            arguments?.getInt("movieId")?.let { movieId ->
                viewModel.addRating(
                        movieId,
                        submitRatingBinding.ratingCommentEditText.text.toString(),
                        submitRatingBinding.ratingBar.progress.toDouble()
                )
                dialog.dismiss()
            }
        }
    }
}