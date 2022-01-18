package com.sirahi.movieapp.view.fragment.signup

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
import com.sirahi.movieapp.databinding.FragmentStartLoginBinding
import com.sirahi.movieapp.presentation.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StartLoginFragment : Fragment() {

    private val viewModel:SignUpViewModel by activityViewModels()
    private lateinit var binding: FragmentStartLoginBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_start_login, container, false)
        binding.fragment=this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel
        navController = Navigation.findNavController(view)
    }

    fun registerNavigate(){
        navController.navigate(R.id.action_startLoginFragment_to_registerFragment)
    }

    fun loginNavigation(){
        navController.navigate(R.id.action_startLoginFragment_to_loginFragment)
    }

}