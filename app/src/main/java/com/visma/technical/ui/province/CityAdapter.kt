package com.visma.technical.ui.province

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.visma.technical.databinding.ItemCityBinding
import com.visma.technical.databinding.ItemProvinceBinding
import com.visma.technical.domain.model.City

class CityAdapter(private val cities: List<City>) :
    RecyclerView.Adapter<CityAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CityAdapter.MyViewHolder {

        val binding = ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: CityAdapter.MyViewHolder, position: Int) {

        holder.binding.city = cities[position]

    }


    inner class MyViewHolder(val binding: ItemCityBinding) :
        RecyclerView.ViewHolder(binding.root)


}