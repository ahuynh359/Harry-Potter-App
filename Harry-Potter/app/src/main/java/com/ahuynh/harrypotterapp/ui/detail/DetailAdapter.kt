package com.ahuynh.harrypotterapp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.ahuynh.harrypotterapp.R
import com.ahuynh.harrypotterapp.databinding.DialogDetailBinding
import com.ahuynh.harrypotterapp.databinding.ItemCharacterBinding
import com.ahuynh.harrypotterapp.data.model.CharacterItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class DetailAdapter : ListAdapter<CharacterItem, DetailAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CharacterItem>() {
            override fun areContentsTheSame(
                oldItem: CharacterItem,
                newItem: CharacterItem
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areItemsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
                return oldItem == newItem
            }
        }
    }


    inner class ViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                MaterialDialog(binding.root.context).show {
                    val dialogDetailBinding =
                        DialogDetailBinding.inflate(LayoutInflater.from(binding.root.context))
                    val character = getItem(adapterPosition)
                    dialogDetailBinding.apply {
                        Glide.with(context)
                            .load(character.image)
                            .apply(
                                RequestOptions().placeholder(R.drawable.ic_placeholder)
                                    .error(R.drawable.ic_error_user)
                            )
                            .into(imvDetailCharacter)
                        tvName.text = character.name
                        tvSpecies.text = character.species
                        tvGender.text = character.gender
                        tvHouse.text = character.house
                        tvDateOfBirth.text = character.dateOfBirth
                        tvYearOfBirth.text = character.yearOfBirth.toString()
                        tvAncestry.text = character.ancestry
                        tvPatronus.text = character.patronus
                        tvActor.text = character.actor
                        tvAlive.text = if (character.alive) "Alive" else "Dead"

                    }

                    customView(view = dialogDetailBinding.root)

                    cornerRadius(context.resources.getDimension(R.dimen._8dp))
                }
            }
        }

        fun bind(item: CharacterItem) {
            binding.apply {
                Glide.with(binding.root)
                    .load(item.image)
                    .apply(
                        RequestOptions().placeholder(R.drawable.ic_placeholder)
                            .error(R.drawable.ic_error_user)
                    )
                    .into(imvCharacter)

                tvName.text = item.name
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.constraint.layoutParams.height = (parent.width / 3) * 2
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}