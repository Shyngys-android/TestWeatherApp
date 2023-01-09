package com.example.testweatherapp.ui.city_info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.data_models.models.weather.DailyInfo
import com.example.testweatherapp.R
import com.example.testweatherapp.databinding.AdapterWeatherDailyBinding

class WeathersAdapter () : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<DailyInfo>() {
        override fun areItemsTheSame(oldItem: DailyInfo, newItem: DailyInfo): Boolean {
            return oldItem as DailyInfo == newItem as DailyInfo
        }

        override fun areContentsTheSame(oldItem: DailyInfo, newItem: DailyInfo): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(list: List<DailyInfo>) {
        differ.submitList(list)
    }

    companion object {
        private const val VIEW_TYPE_INFO = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_INFO -> {
                val binding: AdapterWeatherDailyBinding =
                    DataBindingUtil.inflate(
                        inflater,
                        R.layout.adapter_weather_daily,
                        parent,
                        false
                    )
                DailyInfoViewHolder(binding)
            }
            else -> {
                throw IllegalStateException("Incorrect ViewType found")
            }
        }
    }

    inner class DailyInfoViewHolder(var binding: AdapterWeatherDailyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun initContent(info: DailyInfo) {
            binding.info = info
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_INFO -> {
                val viewHolder = holder as DailyInfoViewHolder
                viewHolder.initContent(differ.currentList[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (differ.currentList[position]) {
            is DailyInfo -> VIEW_TYPE_INFO
            else -> throw IllegalStateException("Incorrect ViewType found")
        }
}