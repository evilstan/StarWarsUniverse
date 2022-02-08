package com.evilstan.starwarsuniverse.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.evilstan.starwarsuniverse.R
import com.evilstan.starwarsuniverse.cloud.core.App
import com.evilstan.starwarsuniverse.databinding.FragmentSearchBinding
import com.evilstan.starwarsuniverse.domain.PersonBundle
import com.evilstan.starwarsuniverse.domain.models.CharacterCache
import com.evilstan.starwarsuniverse.domain.usecase.SaveToFavoritesUseCase
import com.evilstan.starwarsuniverse.domain.usecase.SearchCharacterByIdUseCase
import com.evilstan.starwarsuniverse.domain.usecase.ShowCharacterDetailsUseCase
import com.evilstan.starwarsuniverse.ui.core.Adapter
import com.evilstan.starwarsuniverse.ui.core.ErrorMessage
import com.evilstan.starwarsuniverse.ui.core.Status
import com.evilstan.starwarsuniverse.domain.models.Character
import com.evilstan.starwarsuniverse.domain.models.CharacterUi


class SearchFragment : Fragment(),
    Adapter.OnPersonClickListener,
    Adapter.OnFavoriteClickListener {

    private lateinit var adapter: Adapter
    private lateinit var viewModel: SearchViewModel
    private var dataset = mutableListOf<CharacterCache>()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        viewModel = SearchViewModel()
        initComponents()
        observe()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initComponents() {
        adapter = Adapter(this, this)
        binding.searchRecycler.adapter = adapter
        binding.editText.addTextChangedListener(textWatcher)

    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        override fun afterTextChanged(p0: Editable) = Unit

        override fun onTextChanged(charSequence: CharSequence, p1: Int, p2: Int, p3: Int) {
            dataset.clear()
            viewModel.search(charSequence.toString())
        }
    }

    private fun observe() {
        viewModel.mappedPersons.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> onResponseLoading()
                Status.SUCCESS -> onResponseSuccess(it.data)
                Status.ERROR -> onResponseError(it.errorCode!!)
            }
        }
    }

    private fun onResponseLoading() {
        showProgressBar(true)
    }

    private fun onResponseSuccess(data: List<CharacterUi>?) {
        if (data != null) {
            adapter.update(data)
        }
        showProgressBar(false)
    }

    private fun onResponseError(errorCode: ErrorMessage.ErrorCode) {
        adapter.update(listOf())
        showProgressBar(false)
        Toast.makeText(
            requireContext(),
            ErrorMessage.message(errorCode),
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showProgressBar(visible: Boolean) {
        var visibility = ProgressBar.INVISIBLE
        if (visible) visibility = ProgressBar.VISIBLE
        binding.progressBar.visibility = visibility
    }

    override fun onPersonClick(character: Character) {
        viewModel.addFilms(character)
        viewModel.filmedPerson.observe(viewLifecycleOwner) {
            Navigation.findNavController(binding.editText)
                .navigate(R.id.navi_info, PersonBundle().makeBundle(character))
        }
    }

    override fun onFavoriteClick(character: CharacterUi, favorite: Boolean) {
        viewModel.addFilms(character)
        viewModel.filmedPerson.observe(viewLifecycleOwner) {
            viewModel.makeFavorite(character, favorite)
        }
    }

}

