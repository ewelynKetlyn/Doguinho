package com.example.randomdog.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.randomdog.R
import com.example.randomdog.databinding.FragmentRandomDogBinding
import com.example.randomdog.model.Dog
import com.example.randomdog.network.Repository
import com.example.randomdog.viewmodel.MainViewModel
import com.example.randomdog.viewmodel.ViewModelFactory

class FragmentRandomDog : Fragment() {
    private lateinit var binding: FragmentRandomDogBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRandomDogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imgRandom: ImageView = binding.imgRandom
        val btnGenerateDog: Button = binding.btnGenerateDog

        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.response.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                val dog: Dog? = response.body()
                dog?.let {
                    Glide.with(requireContext())
                        .load(dog.message)
                        .placeholder(R.drawable.load_image)
                        .error(R.drawable.error_image)
                        .into(imgRandom)
                }
            } else {
                println("Deu ruim")
            }
        })

        btnGenerateDog.setOnClickListener {
            viewModel.getRandomDog()
        }
    }
}