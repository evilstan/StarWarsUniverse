package com.evilstan.starwarsuniverse.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.evilstan.starwarsuniverse.data.dictionary.cache.PeopleCache
import com.evilstan.starwarsuniverse.databinding.FragmentSearchBinding

class SearchFragment : Fragment(),
View.OnClickListener,
    CompoundButton.OnCheckedChangeListener {

    private lateinit var searchViewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        val root: View = binding.root
        init()

        return root
    }

    private fun init() {
        val dataSet: MutableList<PeopleCache> = mutableListOf()
        val adapter:SearchAdapter = SearchAdapter(dataSet,this,this)
        val recyclerView = binding.recycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCheckedChanged(checkBox: CompoundButton, checked: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onClick(view: View) {
        TODO("Not yet implemented")
    }
}