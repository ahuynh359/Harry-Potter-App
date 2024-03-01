package com.ahuynh.harrypotterapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ahuynh.harrypotterapp.databinding.ItemHouseBinding
import com.ahuynh.harrypotterapp.utils.HouseType

class MainAdapter(val action : (ImageView, HouseType) -> Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val houseList = mutableListOf<HouseType>(
        HouseType.Gryffindor,
        HouseType.Slytherin,
        HouseType.Ravenclaw,
        HouseType.Hufflepuff
    )

    inner class ViewHolder(private val binding: ItemHouseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                action(
                    binding.imvHouse,
                    houseList[adapterPosition]
                )
            }
        }

        fun bind(item: HouseType) {
            binding.apply {
                imvHouse.setImageResource(item.image)
                val color = ContextCompat.getColor(binding.root.context, item.color)
                tvHouse.setTextColor(color)
                tvHouse.text = item.name
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHouseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return houseList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(houseList[position])
    }
}