package com.evilstan.starwarsuniverse.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.evilstan.starwarsuniverse.databinding.FragmentInfoBinding
import java.util.*
import kotlin.collections.ArrayList

class InfoFragment() : Fragment() {

    private var _binding: FragmentInfoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root
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
        filmsToString(bundle.getStringArrayList("films")!!)
    }

    private fun filmsToString(films: ArrayList<String>) {
        binding.filmsValue.text = films.joinToString(", ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {

    }
}
