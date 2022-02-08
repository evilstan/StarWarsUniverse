package com.evilstan.starwarsuniverse.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import com.evilstan.starwarsuniverse.databinding.FragmentInfoBinding
import com.evilstan.starwarsuniverse.domain.PersonBundle
import com.evilstan.starwarsuniverse.domain.models.CharacterCache
import com.evilstan.starwarsuniverse.domain.models.CharacterUi

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
        viewModel = InfoViewModel()
        setInfo(personBundle.makePerson(requireArguments()))
        adapter = InfoAdapter(dataSet)
        binding.recyclerFilms.adapter = adapter
        binding.favoriteCheckbox.setOnClickListener(this)
        return binding.root
    }

    private fun setInfo(character: CharacterUi) {
        binding.nameValue.text = character.name
        binding.heightValue.text = character.height
        binding.weightValue.text = character.mass
        binding.hairColorValue.text = character.hairColor
        binding.skinColorValue.text = character.skinColor
        binding.eyeColorValue.text = character.eyeColor
        binding.birtYearValue.text = character.birthYear
        binding.genderValue.text = character.gender
        dataSet.addAll(character.films)
        binding.favoriteCheckbox.isChecked = character.favorite
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
