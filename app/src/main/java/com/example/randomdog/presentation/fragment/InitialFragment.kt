package com.example.randomdog.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.randomdog.R
import com.example.randomdog.databinding.FragmentInitialBinding

class InitialFragment : Fragment() {
    private lateinit var binding: FragmentInitialBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInitialBinding.inflate(inflater, container, false)

        binding.buttonPrevious.setOnClickListener {
            findNavController().navigate(R.id.action_initialFragment_to_fragmentRandomDog)
        }

        return binding.root
    }
}