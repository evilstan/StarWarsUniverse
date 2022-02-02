package com.evilstan.starwarsuniverse.ui.info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evilstan.starwarsuniverse.R
import com.evilstan.starwarsuniverse.databinding.ListItemSimpleBinding

class InfoAdapter(
    private var dataSet: MutableList<String>
) : RecyclerView.Adapter<InfoAdapter.ViewHolder>() {

    private var _binding: ListItemSimpleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ListItemSimpleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = dataSet[position]
        holder.setText(film)
    }

    override fun getItemCount() = dataSet.size

    inner class ViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        fun setText(film: String) {
            binding.recyclerItemSimple.text = film
        }
    }
}