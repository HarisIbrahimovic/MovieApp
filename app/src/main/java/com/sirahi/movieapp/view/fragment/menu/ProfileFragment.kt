package com.sirahi.movieapp.view.fragment.menu

import android.content.Intent
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
import com.sirahi.movieapp.databinding.FragmentProfileBinding
import com.sirahi.movieapp.presentation.MenuViewModel
import com.sirahi.movieapp.view.activity.SignInActivity
import com.sirahi.movieapp.view.adapters.UserListAdapter
import com.sirahi.movieapp.view.fragment.util.navigateTo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(), UserListAdapter.OnUserListListener {


    private val viewModel: MenuViewModel by activityViewModels()
    private lateinit var binding: FragmentProfileBinding
    private lateinit var watchListAdapter: UserListAdapter
    private lateinit var favListAdapter: UserListAdapter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        watchListAdapter = UserListAdapter(requireContext(), 2, this)
        favListAdapter = UserListAdapter(requireContext(), 1, this)
        binding.viewModel = viewModel
        binding.favListAdapter = favListAdapter
        binding.watchListAdapter = watchListAdapter
    }

    override fun onFavUserListClickListener(id: Int, type: String) {
        if (type == "tv")
            navigateTo(navController, R.id.action_profileFragment_to_TVDetailsFragment, "tvId", id)
        else
            navigateTo(
                navController,
                R.id.action_profileFragment_to_movieDetailsFragment,
                "movieId",
                id
            )
    }

    override fun onWatchUserListClickListener(id: Int, type: String) {
        if (type == "tv")
            navigateTo(navController, R.id.action_profileFragment_to_TVDetailsFragment, "tvId", id)
        else
            navigateTo(
                navController,
                R.id.action_profileFragment_to_movieDetailsFragment,
                "movieId",
                id
            )
    }

    fun signOut() {
        startActivity(Intent(requireContext(), SignInActivity::class.java))
        viewModel.signOut()
        activity?.finish()
    }

}