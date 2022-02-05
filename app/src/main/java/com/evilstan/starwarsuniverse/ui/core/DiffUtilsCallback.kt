package com.evilstan.starwarsuniverse.ui.core

import androidx.recyclerview.widget.DiffUtil
import com.evilstan.starwarsuniverse.domain.cache.PersonCache

class DiffUtilsCallback(
    private val oldList: List<PersonCache>,
    private val newList: List<PersonCache>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].name == newList[newItemPosition].name


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

}