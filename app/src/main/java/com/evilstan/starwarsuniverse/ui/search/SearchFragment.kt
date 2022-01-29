package com.evilstan.starwarsuniverse.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.evilstan.starwarsuniverse.data.dictionary.cache.FilmCache
import com.evilstan.starwarsuniverse.data.dictionary.cache.PeopleCache
import com.evilstan.starwarsuniverse.databinding.FragmentSearchBinding


class SearchFragment : Fragment(),
    View.OnClickListener,
    CompoundButton.OnCheckedChangeListener, TextWatcher {


    private val dataSetOrigin: MutableList<PeopleCache> = mutableListOf()
    private var dataSet: MutableList<PeopleCache> = mutableListOf()
    lateinit var adapter: SearchAdapter
    lateinit var recyclerView: RecyclerView

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
        initTestDataset()

        return root
    }


    private fun init() {
        adapter = SearchAdapter(dataSet, this, this)

        recyclerView = binding.recycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.editText.addTextChangedListener(this)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //TODO delete method!
    private fun initTestDataset() {
        val luke = PeopleCache(
            "Luke Skywalker",
            "175",
            "55",
            "brown",
            "blue",
            "red",
            "2331",
            "Female",
            "Earth",
            arrayListOf(FilmCache("HomeWorld", 4), FilmCache("Go Home", 5))
        )
        val dart = PeopleCache(
            "Dart Weider",
            "175",
            "55",
            "brown",
            "blue",
            "red",
            "2331",
            "Female",
            "Earth",
            arrayListOf(FilmCache("HomeWorld", 4), FilmCache("Go Home", 5))
        )
        val yoda = PeopleCache(
            "Master Yoda",
            "175",
            "55",
            "brown",
            "blue",
            "red",
            "2331",
            "Female",
            "Earth",
            arrayListOf(FilmCache("HomeWorld", 4), FilmCache("Go Home", 5))
        )
        dataSetOrigin.add(luke)
        dataSetOrigin.add(dart)
        dataSetOrigin.add(yoda)
    }

    override fun onCheckedChanged(checkBox: CompoundButton, checked: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onClick(view: View) {
        TODO("Not yet implemented")
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(charSequence: CharSequence, p1: Int, p2: Int, p3: Int) {
        filter(charSequence.toString().lowercase())
    }

    override fun afterTextChanged(editable: Editable) {
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun filter(text: String) {
        dataSet.clear()

        if (text.isNotEmpty()) {
            for (peopleCache in dataSetOrigin) {
                if (peopleCache.name.lowercase().contains(text)) {
                    dataSet.add(peopleCache)
                }
            }
        }
        adapter.notifyDataSetChanged()
    }
}