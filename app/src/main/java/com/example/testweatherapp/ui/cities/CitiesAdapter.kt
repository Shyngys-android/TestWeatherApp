package com.example.testweatherapp.ui.cities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.data_models.models.city.City
import com.example.testweatherapp.R
import com.example.testweatherapp.databinding.AdapterCityBinding
import com.example.testweatherapp.ui.common.callbacks.RecyclerViewItemClickCallback

class CitiesAdapter (
    private val recyclerViewItemClickCallback: RecyclerViewItemClickCallback
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem as City == newItem as City
        }

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem.city_name == newItem.city_name
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(list: List<City>) {
        differ.submitList(list)
    }

    companion object {
        private const val VIEW_TYPE_CITY = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_CITY -> {
                val binding: AdapterCityBinding =
                    DataBindingUtil.inflate(
                        inflater,
                        R.layout.adapter_city,
                        parent,
                        false
                    )
                CityViewHolder(binding)
            }
            else -> {
                throw IllegalStateException("Incorrect ViewType found")
            }
        }
    }

    inner class CityViewHolder(var binding: AdapterCityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun initContent(city: City) {
            binding.city = city
            binding.recyclerViewItemClickCallback = recyclerViewItemClickCallback
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_CITY -> {
                val viewHolder = holder as CityViewHolder
                viewHolder.initContent(differ.currentList[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (differ.currentList[position]) {
            is City -> VIEW_TYPE_CITY
            else -> throw IllegalStateException("Incorrect ViewType found")
        }
}