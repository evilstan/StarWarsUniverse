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
    Adapter.OnPersonClickListener,
    Adapter.OnFavoriteClickListener {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: Adapter
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initComponents() {
        adapter = Adapter( this,this)
        binding.favoritesRecycler.adapter = adapter
        viewModel.personsFromDb.observe(viewLifecycleOwner) { adapter.submitList(it) }
    }

    override fun onPersonClick(personCache: PersonCache) {
        Navigation.findNavController(binding.favoritesRecycler)
            .navigate(R.id.navi_info, makeBundle(personCache))//TODO make through ViewModel
    }

    private fun makeBundle(personCache: PersonCache): Bundle { //TODO parcelable
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