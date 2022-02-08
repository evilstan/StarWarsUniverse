package com.evilstan.starwarsuniverse.ui.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.evilstan.starwarsuniverse.databinding.ListItemStarBinding
import com.evilstan.starwarsuniverse.domain.models.Character

class Adapter(
    private val onPersonClickListener: OnPersonClickListener,
    private val onFavoriteClickListener: OnFavoriteClickListener
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var _binding: ListItemStarBinding? = null
    private val binding get() = _binding!!
    private var dataSet = listOf<Character>()

    fun update(data: List<Character>) {
        val diffUtilsCallback = DiffUtilsCallback(dataSet, data)
        val diffResult = DiffUtil.calculateDiff(diffUtilsCallback)
        dataSet = data
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ListItemStarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        val person = dataSet[position]

        holder.setCharacter(person)
        holder.itemView.setOnClickListener { onPersonClickListener.onPersonClick(person) }
    }

    override fun getItemCount() = dataSet.size

    inner class ViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        fun setCharacter(character: Character) {

            binding.recyclerItemStar.text = character.name
            binding.favoriteCheckbox.isChecked = character.favorite

            binding.favoriteCheckbox.setOnClickListener { view ->
                view as CheckBox
                onFavoriteClickListener.onFavoriteClick(character, view.isChecked)
            }
        }
    }

    interface OnPersonClickListener {
        fun onPersonClick(character: Character)
    }

    interface OnFavoriteClickListener {
        fun onFavoriteClick(character: Character, favorite: Boolean)
    }

}