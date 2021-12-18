package com.sirahi.movieapp.view.fragment.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sirahi.movieapp.R
import com.sirahi.movieapp.databinding.FragmentRegisterBinding
import com.sirahi.movieapp.presentation.SignUpViewModel
import com.sirahi.movieapp.presentation.util.Constants
import com.sirahi.movieapp.presentation.util.RegistrationStatus
import com.sirahi.movieapp.presentation.util.SignUpFragmentStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding:FragmentRegisterBinding?=null
    private val binding get() = _binding!!
    private val viewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
        onClick()
    }

    private fun onClick() {
        binding.signUpButton.setOnClickListener {
            viewModel.registerUser(
                binding.userNameEditText.text.toString().trim(),
                binding.emailEditText.text.toString().trim(),
                binding.passwordEditText.text.toString().trim()
            )
        }
        binding.signInTextRegister.setOnClickListener {
          //  viewModel.setFragment(SignUpFragmentStatus.LOGIN)
        }
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun observe() {
        viewModel.getSignInData().observe(viewLifecycleOwner, { status ->
            when (status) {
                is RegistrationStatus.Loading -> {
                    binding.signUpButton.text = ""
                    binding.progressBar.visibility = View.VISIBLE
                    binding.userNameInputLayout.error=null
                    binding.passwordInputLayout.error=null
                    binding.emailInputLayout.error=null
                    viewModel.setStatusToPending()
                }
                is RegistrationStatus.Failure->{
                    when(status.error?.field){
                        "username"-> binding.userNameInputLayout.error=status.error.mess
                        "email"-> binding.emailInputLayout.error=status.error.mess
                        "password" -> binding.passwordInputLayout.error=status.error.mess
                        else -> Toast.makeText(requireContext(),Constants.UNK_ERROR,Toast.LENGTH_SHORT).show()
                    }
                    binding.progressBar.visibility=View.GONE
                    binding.signUpButton.text=getString(R.string.sign_up_no_caps)
                    viewModel.setStatusToPending()
                }
                else->Unit
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}