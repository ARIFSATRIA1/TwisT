package com.example.twist.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.twist.model.data.di.Injection
import com.example.twist.model.data.repository.Repository
import com.example.twist.viewmodel.SignInViewModel
import com.example.twist.viewmodel.SignUpViewModel

class ViewModelFactory (private val repository: Repository): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignInViewModel::class.java) -> {
                SignInViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE : ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

}