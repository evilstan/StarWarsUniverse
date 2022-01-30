package com.evilstan.starwarsuniverse.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.evilstan.starwarsuniverse.R
import com.evilstan.starwarsuniverse.domain.cache.PersonCache

class SearchAdapter(
    private var dataSet: MutableList<PersonCache>,
    private val onPersonClickListener: OnPersonClickListener,

    ) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = dataSet[position]
        holder.setCharacter(person)
        holder.itemView.setOnClickListener { onPersonClickListener.onPersonClick(person) }

    }

    override fun getItemCount() = dataSet.size


    class ViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        private var nameText: TextView = view.findViewById(R.id.recycler_item_name)
        private var favoriteCheckBox: CheckBox = view.findViewById(R.id.favorite_checkbox)

        fun setCharacter(person:PersonCache) {
            nameText.text = person.name

            favoriteCheckBox.setOnCheckedChangeListener { checkbox, isChecked ->
                person.favorite = isChecked
            }

        }
    }

    interface OnPersonClickListener {
        fun onPersonClick(personCache: PersonCache)
    }

}