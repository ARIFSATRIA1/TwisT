package com.example.twist.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.twist.R
import com.example.twist.databinding.FragmentSignInBinding
import com.example.twist.model.data.ResultState
import com.example.twist.model.data.preferences.TokenModel
import com.example.twist.viewmodel.SignInViewModel
import com.example.twist.viewmodel.factory.ViewModelFactory


class SignInFragment : Fragment(),View.OnClickListener {

    private lateinit var _binding : FragmentSignInBinding
    private val binding get() = _binding!!

    private val viewModel by viewModels<SignInViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignin.setOnClickListener {
            onClick(it)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onClick(view: View) {

        if (view.id == R.id.btnSignin) {
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            if (email.isEmpty()) {
                binding.edtEmail.error = FIELD_REQUIRED
            }

            if (!isEmailValid(email)) {
                binding.edtEmail.error = FIELD_IS_INVALID
            }

            if (password.isEmpty()) {
                binding.edtEmail.error = FIELD_REQUIRED
            }

            loginUser(email, password)
        }



    }

    private fun loginUser(email: String, password: String) {
        viewModel.loginUser(email, password).observe(viewLifecycleOwner) {result ->
            if (result != null) {
                when (result) {
                    is ResultState.Loading -> {
                        showLoading(true)
                    }

                    is ResultState.Success -> {
                        showLoading(false)
                        viewModel.saveToken(TokenModel(result.data.data.token.token))
                        androidx.appcompat.app.AlertDialog.Builder(requireContext()).apply {
                            setTitle("Yeah")
                            setMessage("Login Successful")
                            setPositiveButton("Next") {_, _ ->
                                val intent = Intent(requireContext(), HomeFragment::class.java)
                                startActivity(intent)

                            }
                        }
                            .create()
                            .show()
                        Toast.makeText(requireContext(), result.data.message , Toast.LENGTH_SHORT).show()
                    }
                    is ResultState.Error -> {
                        Toast.makeText(requireContext(), result.error , Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.proggressbar.visibility = View.VISIBLE
        } else {
            binding.proggressbar.visibility = View.GONE
        }
    }

    private fun isEmailValid(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    }
    companion object {
        const val FIELD_REQUIRED = "Must be Completed"
        const val FIELD_IS_INVALID = "Email is not Valid"
    }

}