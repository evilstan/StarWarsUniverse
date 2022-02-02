package com.evilstan.starwarsuniverse.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.evilstan.starwarsuniverse.R
import com.evilstan.starwarsuniverse.databinding.FragmentFavoritesBinding
import com.evilstan.starwarsuniverse.domain.cache.PersonCache
import com.evilstan.starwarsuniverse.ui.Adapter

class FavoritesFragment : Fragment(),
    Adapter.OnPersonClickListener, Adapter.OnFavoriteClickListener {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: Adapter
    private var dataSet = mutableListOf<PersonCache>()
    private lateinit var viewModel: FavoritesViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        viewModel = FavoritesViewModel(requireContext())
        initComponents()

        return binding.root
    }

    private fun initComponents() {
        adapter = Adapter(dataSet, this,this)
        binding.favoritesRecycler.adapter = adapter
        viewModel.personsFromDb.observe(viewLifecycleOwner) { loadDataSet(it) }
    }

    private fun loadDataSet(data: List<PersonCache>) {
        dataSet.clear()
        for (personCache in data) {
            dataSet.add(personCache)
        }
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onPersonClick(personCache: PersonCache) {
        Navigation.findNavController(binding.favoritesRecycler)
            .navigate(R.id.navi_info, bundle(personCache))//TODO make through ViewModel
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
        bundle.putBoolean("favorite",personCache.favorite)
        return bundle
    }

    override fun onFavoriteClick(person: PersonCache, favorite: Boolean) {
        viewModel.makeFavorite(person,favorite)
    }


}