package com.evilstan.starwarsuniverse.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.evilstan.starwarsuniverse.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

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
        val root: View = binding.root
        viewModel = InfoViewModel(requireContext())

        init()
        parseBundle(requireArguments())

        return root
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

    private fun init() {
        adapter = InfoAdapter(dataSet)
        binding.recyclerFilms.adapter = adapter
    }
}
