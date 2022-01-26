package com.evilstan.starwarsuniverse.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.evilstan.starwarsuniverse.data.dictionary.cache.FilmCache
import com.evilstan.starwarsuniverse.data.dictionary.cache.PersonCache
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
        val dataSet: MutableList<PersonCache> = mutableListOf(
            PersonCache(
                "Hello",
                "170",
                "123",
                "red",
                "black",
                "red",
                "1999",
                "Male",
                "Earth",
                arrayListOf(FilmCache("Help me", 4))
            )
        )
        val adapter: SearchAdapter = SearchAdapter(dataSet, this, this)
        val recyclerView = binding.recycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCheckedChanged(checkBox: CompoundButton, checked: Boolean) {

    }

    override fun onClick(view: View) {
        TODO("Not yet implemented")
    }
}