package com.evilstan.starwarsuniverse.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.evilstan.starwarsuniverse.R
import com.evilstan.starwarsuniverse.ui.Status
import com.evilstan.starwarsuniverse.databinding.FragmentSearchBinding
import com.evilstan.starwarsuniverse.domain.cache.PersonCache
import com.evilstan.starwarsuniverse.ui.Adapter
import com.evilstan.starwarsuniverse.ui.ErrorMessage

class SearchFragment : Fragment(),
    Adapter.OnPersonClickListener, Adapter.OnFavoriteClickListener {


    private var dataSet: MutableList<PersonCache> = mutableListOf()
    private lateinit var adapter: Adapter
    private lateinit var viewModel: SearchViewModel
    private lateinit var textView: TextView

    private var _viewBinding: FragmentSearchBinding? = null

    private val binding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentSearchBinding.inflate(inflater, container, false)
        viewModel = SearchViewModel(requireContext())
        initComponents()
        observeGetPosts()
        return binding.root
    }

    private fun initComponents() {
        adapter = Adapter(dataSet, this, this)
        binding.searchRecycler.adapter = adapter
        textView = binding.errorText
        binding.editText.addTextChangedListener(textWatcher)
    }

    private fun observeGetPosts() {
        viewModel.personsMapped.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> onResponseLoading()
                Status.SUCCESS -> onResponseSuccess(it.data)
                Status.ERROR -> onResponseError()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(charSequence: CharSequence, p1: Int, p2: Int, p3: Int) {

            if (charSequence.isNotEmpty()) {
                viewModel.search(charSequence.toString())
            } else {
                clearRecycler()
            }
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

    private fun clearRecycler() {
        dataSet.clear()
        switchProgressBar(false)
        switchText(false)
        adapter.notifyDataSetChanged()
    }

    private fun onResponseLoading() {
        switchProgressBar(true)
    }

    private fun onResponseSuccess(data: ArrayList<PersonCache>?) {
        dataSet.clear()

        if (data != null) {
            for (personCache in data) {
                dataSet.add((personCache))
            }
        }

        switchText(dataSet.isEmpty())
        switchProgressBar(false)
        adapter.notifyDataSetChanged() //TODO use DiffUtil

    }
    private fun onResponseError() {
        switchProgressBar(false)
        switchText(true, ErrorMessage.ErrorCode.NO_INTERNET)
    }

    private fun switchProgressBar(visible: Boolean) {
        if (visible) binding.progressBar.visibility = ProgressBar.VISIBLE
        else binding.progressBar.visibility = ProgressBar.INVISIBLE
    }

    private fun switchText(
        visible: Boolean,
        errorCode: ErrorMessage.ErrorCode = ErrorMessage.ErrorCode.CHARACTER_NOT_FOUND
    ) {
        textView.text = ErrorMessage.message(errorCode)
        if (visible) {
            textView.visibility = TextView.VISIBLE
        } else {
            textView.visibility = TextView.INVISIBLE
        }
    }

    override fun onPersonClick(personCache: PersonCache) {
        viewModel.addFilm(personCache)
        viewModel.filmedPerson.observe(viewLifecycleOwner) {
            Navigation.findNavController(binding.editText)
                .navigate(R.id.navi_info, bundle(it))//TODO make through ViewModel
        }

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
        viewModel.makeFavorite(person, favorite)
    }
}

