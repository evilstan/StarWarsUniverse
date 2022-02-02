package com.evilstan.starwarsuniverse.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import com.evilstan.starwarsuniverse.databinding.FragmentInfoBinding
import com.evilstan.starwarsuniverse.domain.cache.PersonCache

class InfoFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!
    private var dataSet = arrayListOf<String>()
    private lateinit var adapter: InfoAdapter
    private lateinit var viewModel: InfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        viewModel = InfoViewModel(requireContext())
        parseBundle(requireArguments())
        adapter = InfoAdapter(dataSet)
        binding.recyclerFilms.adapter = adapter
        binding.favoriteCheckbox.setOnClickListener(this)
        return binding.root
    }

    private fun parseBundle(bundle: Bundle) {
        binding.nameValue.text = bundle.getString("name")
        binding.heightValue.text = bundle.getString("height")
        binding.weightValue.text = bundle.getString("mass")
        binding.hairColorValue.text = bundle.getString("hair_color")
        binding.skinColorValue.text = bundle.getString("skin_color")
        binding.eyeColorValue.text = bundle.getString("eye_color")
        binding.birtYearValue.text = bundle.getString("birth_year")
        binding.genderValue.text = bundle.getString("gender")
        dataSet.addAll(bundle.getStringArrayList("films")!!)
        binding.favoriteCheckbox.isChecked = bundle.getBoolean("favorite")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View) {
        view as CheckBox
        viewModel.makeFavorite(getPerson(), view.isChecked)
    }

    private fun getPerson(): PersonCache {
        val bundle = requireArguments()
        return PersonCache(
            bundle.getString("name")!!,
            bundle.getString("height")!!,
            bundle.getString("mass")!!,
            bundle.getString("hair_color")!!,
            bundle.getString("skin_color")!!,
            bundle.getString("eye_color")!!,
            bundle.getString("birth_year")!!,
            bundle.getString("gender")!!,
            bundle.getStringArrayList("films")!!,
            bundle.getBoolean("favorite")
        )
    }
}
