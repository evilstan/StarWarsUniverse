package com.evilstan.starwarsuniverse.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.evilstan.starwarsuniverse.R
import com.evilstan.starwarsuniverse.databinding.ListItemStarBinding
import com.evilstan.starwarsuniverse.domain.cache.PersonCache


class Adapter(
    private val onPersonClickListener: OnPersonClickListener,
    private val onFavoriteClickListener: OnFavoriteClickListener
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var _binding: ListItemStarBinding? = null
    private val binding get() = _binding!!

    private val differ: AsyncListDiffer<PersonCache> = AsyncListDiffer(this, DIFF_CALLBACK)

    fun submitList(list: List<PersonCache>) {
        differ.submitList(list)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<PersonCache> =
            object : DiffUtil.ItemCallback<PersonCache>() {
                override fun areItemsTheSame(
                    oldItem: PersonCache,
                    newItem: PersonCache
                ) = oldItem.name == newItem.name

                override fun areContentsTheSame(
                    oldItem: PersonCache,
                    newItem: PersonCache
                ) = oldItem == newItem
            }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ListItemStarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = differ.currentList[position]

        holder.setCharacter(person)
        holder.itemView.setOnClickListener { onPersonClickListener.onPersonClick(person) }

    }

    override fun getItemCount() = differ.currentList.size

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
        fun onPersonClick(personCache: PersonCache)
    }

    interface OnFavoriteClickListener {
        fun onFavoriteClick(person: PersonCache, favorite: Boolean)
    }

}