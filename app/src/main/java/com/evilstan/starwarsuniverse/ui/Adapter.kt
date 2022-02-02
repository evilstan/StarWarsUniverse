package com.evilstan.starwarsuniverse.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.evilstan.starwarsuniverse.R
import com.evilstan.starwarsuniverse.domain.cache.PersonCache

class Adapter(
    private var dataSet: MutableList<PersonCache>,
    private val onPersonClickListener: OnPersonClickListener,
    private val onFavoriteClickListener: OnFavoriteClickListener
) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item_star, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = dataSet[position]
        holder.setCharacter(person)
        holder.itemView.setOnClickListener { onPersonClickListener.onPersonClick(person) }

    }

    override fun getItemCount() = dataSet.size


    inner class ViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        private var nameText: TextView = view.findViewById(R.id.recycler_item)
        private var favoriteCheckBox: CheckBox = view.findViewById(R.id.favorite_checkbox)

        fun setCharacter(person: PersonCache) {
            nameText.text = person.name
            favoriteCheckBox.isChecked = person.favorite

            favoriteCheckBox.setOnClickListener { view ->
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