package com.sirahi.movieapp.view.fragment.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sirahi.movieapp.R
import com.sirahi.movieapp.databinding.FragmentLoginBinding
import com.sirahi.movieapp.presentation.SignUpViewModel
import com.sirahi.movieapp.presentation.util.Constants
import com.sirahi.movieapp.presentation.util.RegistrationStatus
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: SignUpViewModel by activityViewModels()
    private var _binding : FragmentLoginBinding? = null

    private val binding get() = _binding!!

    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        observe()
        onClicks()
    }

    private fun observe() {
        viewModel.getSignInData().observe(viewLifecycleOwner, { status ->
            when (status) {
                is RegistrationStatus.Loading -> {
                    binding.signInButton.text = ""
                    binding.progressBar.visibility = View.VISIBLE
                    binding.emailInputLayout.error=null
                    binding.passwordInputLayout.error=null
                    viewModel.setStatusToPending()
                }
                is RegistrationStatus.Failure->{
                    when(status.error?.field){
                        "email"-> binding.emailInputLayout.error=status.error.mess
                        "password" -> binding.passwordInputLayout.error=status.error.mess
                        else -> Toast.makeText(requireContext(), Constants.UNK_ERROR, Toast.LENGTH_SHORT).show()
                    }
                    binding.progressBar.visibility=View.GONE
                    binding.signInButton.text=getString(R.string.sign_up_no_caps)
                    viewModel.setStatusToPending()
                }
                else->Unit
            }
        })
    }

    private fun onClicks() {
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.signInButton.setOnClickListener {
            viewModel.loginUser(
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}