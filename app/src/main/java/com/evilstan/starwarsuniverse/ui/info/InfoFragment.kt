package com.evilstan.starwarsuniverse.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import com.evilstan.starwarsuniverse.databinding.FragmentInfoBinding
import com.evilstan.starwarsuniverse.domain.PersonBundle
import com.evilstan.starwarsuniverse.domain.cache.PersonCache

class InfoFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!
    private var dataSet = arrayListOf<String>()
    private lateinit var adapter: InfoAdapter
    private lateinit var viewModel: InfoViewModel
    private val personBundle = PersonBundle()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        viewModel = InfoViewModel(requireContext())
        setInfo(personBundle.makePerson(requireArguments()))
        adapter = InfoAdapter(dataSet)
        binding.recyclerFilms.adapter = adapter
        binding.favoriteCheckbox.setOnClickListener(this)
        return binding.root
    }

    private fun setInfo(person: PersonCache) {
        binding.nameValue.text = person.name
        binding.heightValue.text = person.height
        binding.weightValue.text = person.mass
        binding.hairColorValue.text = person.hair_color
        binding.skinColorValue.text = person.skin_color
        binding.eyeColorValue.text = person.eye_color
        binding.birtYearValue.text = person.birth_year
        binding.genderValue.text = person.gender
        dataSet.addAll(person.films)
        binding.favoriteCheckbox.isChecked = person.favorite
    }

    override fun onClick(view: View) {
        view as CheckBox
        viewModel.makeFavorite(personBundle.makePerson(requireArguments()), view.isChecked)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
