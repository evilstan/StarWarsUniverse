package com.evilstan.starwarsuniverse.ui.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.evilstan.starwarsuniverse.databinding.ListItemStarBinding
import com.evilstan.starwarsuniverse.domain.cache.PersonCache

class Adapter(
    private val onPersonClickListener: OnPersonClickListener,
    private val onFavoriteClickListener: OnFavoriteClickListener
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var _binding: ListItemStarBinding? = null
    private val binding get() = _binding!!
    private var dataSet = listOf<PersonCache>()

    fun update(data: List<PersonCache>) {
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

        fun setCharacter(person: PersonCache) {

            binding.recyclerItemStar.text = person.name
            binding.favoriteCheckbox.isChecked = person.favorite

            binding.favoriteCheckbox.setOnClickListener { view ->
                view as CheckBox
                onFavoriteClickListener.onFavoriteClick(person, view.isChecked)
            }
        }
    }

    interface OnPersonClickListener {
        fun onPersonClick(person: PersonCache)
    }

    interface OnFavoriteClickListener {
        fun onFavoriteClick(person: PersonCache, favorite: Boolean)
    }

}