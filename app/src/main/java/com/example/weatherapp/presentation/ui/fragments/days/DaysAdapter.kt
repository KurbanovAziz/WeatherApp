package com.example.weatherapp.presentation.ui.fragments.days

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemListBinding
import com.example.weatherapp.domain.model.Forecastday
import com.example.weatherapp.presentation.utils.loadImage

class DaysAdapter(private val context: Context) : ListAdapter<Forecastday, DaysAdapter.ViewHolder>(
    DiffUtilItemCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DaysAdapter.ViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(forecastDay: Forecastday) {
            binding.tvDate.text = forecastDay.date
            binding.tvCondition.text = forecastDay.day.condition.text
            binding.im.loadImage("https:" + forecastDay.day.condition.icon)
            binding.tvTemp.text = context.getString(
                R.string.maxMin,
                forecastDay.day.mintemp_c,
                forecastDay.day.maxtemp_c
            )
        }
    }

    private class DiffUtilItemCallback : DiffUtil.ItemCallback<Forecastday>() {

        override fun areItemsTheSame(oldItem: Forecastday, newItem: Forecastday): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Forecastday, newItem: Forecastday): Boolean {
            return oldItem == newItem
        }
    }
}

