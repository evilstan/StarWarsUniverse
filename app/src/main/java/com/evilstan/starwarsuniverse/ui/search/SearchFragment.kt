package com.evilstan.starwarsuniverse.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.evilstan.starwarsuniverse.R
import com.evilstan.starwarsuniverse.data.core.CloudController
import com.evilstan.starwarsuniverse.databinding.FragmentSearchBinding
import com.evilstan.starwarsuniverse.domain.DatabaseManager
import com.evilstan.starwarsuniverse.domain.cache.PersonCache
import com.evilstan.starwarsuniverse.domain.callback.PeopleCallBack
import com.evilstan.starwarsuniverse.domain.mapper.PersonMapper
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SearchFragment : Fragment(),
    TextWatcher,
    SearchAdapter.OnPersonClickListener {


    private var dataSet: MutableList<PersonCache> = mutableListOf()
    private lateinit var adapter: SearchAdapter
    private lateinit var cloudController: CloudController
    private lateinit var searchCallBack: PeopleCallBack
    private lateinit var databaseManager: DatabaseManager

    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        val root: View = binding.root
        initComponents()
        initData()
        return root
    }

    private fun initComponents() {
        adapter = SearchAdapter(dataSet, this)
        val recyclerView = binding.recycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.editText.addTextChangedListener(this)
    }

    private fun initData() {
        cloudController = CloudController()
        searchCallBack = PeopleCallBack()

        //databaseManager = DatabaseManager(requireContext())
        // TODO: Problem - "AppDatabase_Impl does not exist". Room not working
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(charSequence: CharSequence, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(editable: Editable) {
        if (editable.isNotEmpty()) findPerson(editable.toString())
    }

    private fun findPerson(name: String) = runBlocking {
        val job = launch { cloudController.search(name, searchCallBack) }
        job.join()
        if (searchCallBack.success) {
            dataSet.clear()
            val personList: MutableList<PersonCache> = mutableListOf()

            val personMapper = PersonMapper()
            for (personCloud in searchCallBack.peopleCloud.results) {
                personList.add(personMapper.map(personCloud))
                dataSet.add(personMapper.map(personCloud))
            }

            adapter.notifyDataSetChanged() //TODO learn DiffUtil

        }
    }

    override fun onPersonClick(personCache: PersonCache) {
//        databaseManager.personDao.insert(personCache)
        Navigation.findNavController(binding.editText)
            .navigate(R.id.navi_info, bundle(personCache))
    }

    private fun bundle(personCache: PersonCache): Bundle {
        val bundle = Bundle()
        bundle.putString("name", personCache.name)
        bundle.putString("height", personCache.height)
        bundle.putString("mass", personCache.mass)
        bundle.putString("hair_color", personCache.hair_color)
        bundle.putString("skin_color", personCache.skin_color)
        bundle.putString("eye_color", personCache.eye_color)
        bundle.putString("birth_year", personCache.birth_year)
        bundle.putString("gender", personCache.gender)
        bundle.putStringArrayList("films", personCache.films)
        return bundle
    }
}

