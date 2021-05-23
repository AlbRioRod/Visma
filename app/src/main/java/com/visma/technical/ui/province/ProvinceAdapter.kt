package com.visma.technical.ui.province

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.visma.technical.databinding.ItemProvinceBinding
import com.visma.technical.domain.model.ProvinceDomain

class ProvinceAdapter (private val provinces: List<ProvinceDomain>):
        RecyclerView.Adapter<ProvinceAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProvinceAdapter.MyViewHolder {

        val binding = ItemProvinceBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return provinces.size
    }

    override fun onBindViewHolder(holder: ProvinceAdapter.MyViewHolder, position: Int) {

        holder.binding.province = provinces[position]
    }


    inner class MyViewHolder(val binding: ItemProvinceBinding):
            RecyclerView.ViewHolder(binding.root)

}