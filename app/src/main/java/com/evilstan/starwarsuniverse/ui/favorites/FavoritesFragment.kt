package com.evilstan.starwarsuniverse.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.evilstan.starwarsuniverse.R
import com.evilstan.starwarsuniverse.databinding.FragmentFavoritesBinding
import com.evilstan.starwarsuniverse.domain.PersonBundle
import com.evilstan.starwarsuniverse.domain.cache.PersonCache
import com.evilstan.starwarsuniverse.ui.core.Adapter

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
        viewModel.personsFromDb.observe(viewLifecycleOwner) {
            adapter.update(it) }
    }

    override fun onPersonClick(person: PersonCache) {
        val personBundle = PersonBundle()
        Navigation.findNavController(binding.favoritesRecycler)
            .navigate(R.id.navi_info, personBundle.makeBundle(person))//TODO make through ViewModel
    }

    override fun onFavoriteClick(person: PersonCache, favorite: Boolean) {
        viewModel.makeFavorite(person,favorite)
    }
}