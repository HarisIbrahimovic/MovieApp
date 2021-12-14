package com.sirahi.movieapp.view.fragment.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.sirahi.movieapp.databinding.FragmentStartLoginBinding
import com.sirahi.movieapp.presentation.SignUpViewModel
import com.sirahi.movieapp.presentation.util.SignUpFragmentStatus


class StartLoginFragment : Fragment() {

    private lateinit var viewModel:SignUpViewModel
    private var _binding: FragmentStartLoginBinding?=null
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(requireActivity()).get(SignUpViewModel::class.java)
        onClicks()
    }

    private fun onClicks() {
        binding.registerNowButton.setOnClickListener {
            viewModel.setFragment(SignUpFragmentStatus.REGISTER)
        }
        binding.signInText.setOnClickListener {
            viewModel.setFragment(SignUpFragmentStatus.LOGIN)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}