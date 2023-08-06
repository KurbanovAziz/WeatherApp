package com.example.weatherapp.presentation.ui.fragments.hours

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemListBinding
import com.example.weatherapp.domain.model.Hour
import com.example.weatherapp.presentation.utils.loadImage

class HoursAdapter(private val context: Context) : ListAdapter<Hour, HoursAdapter.ViewHolder>(
    DiffUtilItemCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HoursAdapter.ViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hour: Hour) {
            binding.tvDate.text = hour.time
            binding.tvTemp.text = context.getString(R.string.currentTemp, hour.temp_c)
            binding.tvCondition.text = hour.condition.text
            binding.im.loadImage("https:" + hour.condition.icon)
        }
    }

    private class DiffUtilItemCallback : DiffUtil.ItemCallback<Hour>() {

        override fun areItemsTheSame(oldItem: Hour, newItem: Hour): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Hour, newItem: Hour): Boolean {
            return oldItem == newItem
        }
    }
}

