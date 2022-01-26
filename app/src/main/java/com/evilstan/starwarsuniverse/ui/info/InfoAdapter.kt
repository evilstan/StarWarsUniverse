package com.evilstan.starwarsuniverse.ui.info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.evilstan.starwarsuniverse.R
import com.evilstan.starwarsuniverse.data.dictionary.cache.PersonCache
import com.evilstan.starwarsuniverse.ui.search.SearchAdapter

class InfoAdapter (
    private val dataSet: MutableList<String>

) : RecyclerView.Adapter<InfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = dataSet[position]
        holder.setCharacter(character)
    }

    override fun getItemCount() = dataSet.size


    class ViewHolder(
        view: View) : RecyclerView.ViewHolder(view) {

        private var nameText: TextView = view.findViewById(R.id.recycler_item_name)


        fun setCharacter(name: String) {
            nameText.text = name
        }

    }
}