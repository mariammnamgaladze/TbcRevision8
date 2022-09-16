package com.example.tbcrevision8.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbcrevision8.data.model.Random
import com.example.tbcrevision8.databinding.ItemLayoutBinding
import com.example.tbcrevision8.extension.setImage

class RandomAdapter : ListAdapter<Random,RandomAdapter.MyViewHolder>(object : DiffUtil.ItemCallback<Random>() {

    override fun areItemsTheSame(oldItem: Random, newItem: Random): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Random, newItem: Random): Boolean {
        return oldItem == newItem
    }
}){
    inner class MyViewHolder( val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomAdapter.MyViewHolder {
        return MyViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }
    override fun onBindViewHolder(holder: RandomAdapter.MyViewHolder, position: Int) {

        holder.binding.apply {
            tvPrice.text = getItem(position).price
            tvTitle.text = getItem(position).title
            imageView.setImage(getItem(position).cover)
        }
    }
}