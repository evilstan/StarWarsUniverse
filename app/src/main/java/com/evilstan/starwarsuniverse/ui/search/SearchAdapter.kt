package com.evilstan.starwarsuniverse.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.evilstan.starwarsuniverse.R
import com.evilstan.starwarsuniverse.data.dictionary.cache.PeopleCache
import java.lang.Exception

class SearchAdapter(
    private val dataSet: MutableList<PeopleCache>,
    private val onClickListener: View.OnClickListener,
    private val onCheckedChangeListener: CompoundButton.OnCheckedChangeListener

) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view, onCheckedChangeListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = dataSet[position]
        holder.setCharacter(character.name)
        holder.itemView.setOnClickListener(onClickListener)
    }

    override fun getItemCount() = dataSet.size


    class ViewHolder(
        view: View,
        onCheckedChangeListener: CompoundButton.OnCheckedChangeListener
    ) : RecyclerView.ViewHolder(view) {

        private var nameText: TextView = view.findViewById(R.id.recycler_item_name)
        private var favoriteCheckBox: CheckBox = view.findViewById(R.id.favorite_checkbox)

        init {
            favoriteCheckBox.setOnCheckedChangeListener(onCheckedChangeListener)
        }

        fun setCharacter(name: String) {
            nameText.text = name
        }

        }
    }
}