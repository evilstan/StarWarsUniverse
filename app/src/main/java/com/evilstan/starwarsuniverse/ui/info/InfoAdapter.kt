package com.evilstan.starwarsuniverse.ui.info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.evilstan.starwarsuniverse.R

class InfoAdapter(
    private var dataSet: MutableList<String>
) : RecyclerView.Adapter<InfoAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item_simple, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = dataSet[position]
        holder.setText(film)
    }

    override fun getItemCount() = dataSet.size


    inner class ViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        private var filmText: TextView = view.findViewById(R.id.recycler_item)

        fun setText(film: String) {
            filmText.text = film
        }
    }
}