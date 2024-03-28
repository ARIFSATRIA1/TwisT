package com.example.twist.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.twist.R
import com.example.twist.databinding.FragmentConfirmPassBinding
import com.example.twist.databinding.FragmentUploadBinding


class UploadFragment : Fragment() {


    private lateinit var _binding : FragmentUploadBinding
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.uploadBtns.setOnClickListener {
            val intent = Intent(requireContext(), SignInActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUploadBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}