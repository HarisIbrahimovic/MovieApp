package com.sirahi.movieapp.view.fragment.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sirahi.movieapp.R
import com.sirahi.movieapp.databinding.FragmentStartLoginBinding
import com.sirahi.movieapp.presentation.SignUpViewModel
import com.sirahi.movieapp.presentation.util.SignUpFragmentStatus
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StartLoginFragment : Fragment() {

    private val viewModel:SignUpViewModel by activityViewModels()
    private var _binding: FragmentStartLoginBinding?=null
    private val binding get()= _binding!!
    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        onClicks()
    }

    private fun onClicks() {
        binding.registerNowButton.setOnClickListener {
            navController?.navigate(R.id.action_startLoginFragment_to_registerFragment)
        }
        binding.signInText.setOnClickListener {
            navController?.navigate(R.id.action_startLoginFragment_to_loginFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}