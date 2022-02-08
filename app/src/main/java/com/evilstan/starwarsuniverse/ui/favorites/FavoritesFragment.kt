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
import com.evilstan.starwarsuniverse.domain.models.Character
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
        viewModel = FavoritesViewModel()
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

    override fun onPersonClick(character: Character) {
        val personBundle = PersonBundle()
        Navigation.findNavController(binding.favoritesRecycler)
            .navigate(R.id.navi_info, personBundle.makeBundle(character))
    }

    override fun onFavoriteClick(character: CharacterUi, favorite: Boolean) {
        viewModel.makeFavorite(character,favorite)
    }
}