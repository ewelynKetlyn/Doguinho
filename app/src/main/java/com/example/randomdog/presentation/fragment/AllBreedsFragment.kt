package com.example.randomdog.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.randomdog.databinding.FragmentAllBreedsBinding
import com.example.randomdog.model.BreedsResponse
import com.example.randomdog.network.Repository
import com.example.randomdog.presentation.adapter.BreedAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllBreedsFragment : Fragment() {
    private lateinit var binding: FragmentAllBreedsBinding
    private lateinit var breedAdapter: BreedAdapter
    private val repository = Repository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllBreedsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val breedRecyclerView: RecyclerView = binding.breedRecyclerview
        breedRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        breedAdapter = BreedAdapter(emptyList())
        breedRecyclerView.adapter = breedAdapter
        fetchAllBreeds()

        binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                breedAdapter.filter(newText)
                return true
            }
        })
    }

    private fun fetchAllBreeds() {
        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                repository.getAllBreeds()
            }
            if (response.isSuccessful) {
                val breedsResponse: BreedsResponse? = response.body()
                breedsResponse?.let {
                    breedAdapter.updateBreedList(it.message.keys.toList())
                }
            } else {
                // Handle error
                println("Error fetching dog breeds")
            }
        }
    }
}