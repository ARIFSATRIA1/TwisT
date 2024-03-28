package com.example.twist.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.twist.R
import com.example.twist.databinding.ActivitySignInBinding
import com.example.twist.model.data.ResultState
import com.example.twist.model.data.preferences.TokenModel
import com.example.twist.viewmodel.SignInViewModel
import com.example.twist.viewmodel.factory.ViewModelFactory


class SignInActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var _binding : ActivitySignInBinding
    private val binding get() = _binding!!

    private val viewModel by viewModels<SignInViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignin.setOnClickListener {
            onClick(it)
        }

        binding.tvLogin.setOnClickListener {
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
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
        viewModel.loginUser(email, password).observe(this) {result ->
            if (result != null) {
                when (result) {
                    is ResultState.Loading -> {
                        showLoading(true)
                    }

                    is ResultState.Success -> {
                        showLoading(false)
                        viewModel.saveToken(TokenModel(result.data.data.token.token))
                        androidx.appcompat.app.AlertDialog.Builder(this).apply {
                            setTitle("Yeah")
                            setMessage("Login Successful")
                            setPositiveButton("Next") {_, _ ->
                                val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
                                startActivity(intent)

                            }
                        }
                            .create()
                            .show()
                        Toast.makeText(this, result.data.message , Toast.LENGTH_SHORT).show()
                    }
                    is ResultState.Error -> {
                        Toast.makeText(this, result.error , Toast.LENGTH_SHORT).show()
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