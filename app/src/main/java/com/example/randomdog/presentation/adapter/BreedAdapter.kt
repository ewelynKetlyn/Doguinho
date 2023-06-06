package com.example.randomdog.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.randomdog.R

class BreedAdapter(private var breedList: List<String>) :
    RecyclerView.Adapter<BreedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.breed_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val breedName = breedList[position]
        holder.bind(breedName)
    }

    override fun getItemCount(): Int {
        return breedList.size
    }

    fun updateBreedList(newBreedsList: List<String>){
        breedList = newBreedsList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val breedNameTextView: TextView = itemView.findViewById(R.id.breedNameTextView)

        fun bind(breedName: String){
            breedNameTextView.text = breedName
        }
    }
}