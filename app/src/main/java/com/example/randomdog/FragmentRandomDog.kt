package com.example.randomdog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.randomdog.model.Dog
import com.example.randomdog.network.Repository
import com.example.randomdog.viewmodel.MainViewModel
import com.example.randomdog.viewmodel.ViewModelFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentRandomDog : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var imgRandom: ImageView
    private lateinit var btnGenerateDog: Button
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_random_dog, container, false)
        imgRandom = view.findViewById(R.id.imgRandom)
        btnGenerateDog = view.findViewById(R.id.btnGenerateDog)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentRandomDog().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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