package com.example.twist.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.twist.R
import com.example.twist.databinding.ActivitySignUpBinding
import com.example.twist.model.data.ResultState
import com.example.twist.viewmodel.SignUpViewModel
import com.example.twist.viewmodel.factory.ViewModelFactory


class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var _binding : ActivitySignUpBinding

    private val binding get() = _binding

    private val viewModel by viewModels<SignUpViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            onClick(it)
        }

    }



    fun registerUser(fullname: String, email: String, password: String, confirm_pass: String) {
        viewModel.registerUser(
            fullname,email,password,confirm_pass
        ).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is ResultState.Loading -> {
                        showLoading(true)
                    }

                    is ResultState.Success -> {
                        showLoading(false)
                        androidx.appcompat.app.AlertDialog.Builder(this).apply {
                            setTitle("Yeah")
                            setMessage("Register Account Successfull")
                            setPositiveButton("Next") {_, _ ->
                                val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                                startActivity(intent)
                            }
                        }
                            .create()
                            .show()
                        Toast.makeText(this, result.data.message , Toast.LENGTH_SHORT).show()
                    }

                    is ResultState.Error -> {
                        showLoading(false)
                        Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.btnSignUp) {
            val fullname = binding.edtUsername.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            val confirm_pass = binding.edtForgotPass.text.toString().trim()

            if (fullname.isEmpty()) {
                binding.edtUsername.error = FIELD_REQUIRED
            }

            if (email.isEmpty()) {
                binding.edtEmail.error = FIELD_REQUIRED
            }

            if (!isEmailValid(email)) {
                binding.edtEmail.error = FIELD_IS_INVALID
            }

            if (password.isEmpty()) {
                binding.edtPassword.error = FIELD_REQUIRED
            }

            if (confirm_pass.isEmpty()) {
                binding.edtPassword.error = FIELD_REQUIRED
            }

            registerUser(fullname, email, password, confirm_pass)
        }
    }

    private fun isEmailValid(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.proggressbar.visibility = View.VISIBLE
        } else {
            binding.proggressbar.visibility = View.GONE
        }
    }

    companion object {
        const val FIELD_REQUIRED = "Must be Completed"
        const val FIELD_IS_INVALID = "Email is not Valid"
    }
}