package com.madudka.countries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.madudka.countries.R
import com.madudka.countries.databinding.FragmentInfoBinding
import com.madudka.countries.utils.glide.loadImage
import com.madudka.countries.viewmodel.CountriesViewModel

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!
    private val countriesViewModel: CountriesViewModel by activityViewModels()
    private val args: InfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countriesViewModel.getCountryByPosition(args.position)?.let {
            binding.apply {
                ivFlag.loadImage(requireContext(), it.flags.png)
                tvCapital.text = requireContext()
                    .getString(R.string.country_capital, it.capital ?: "—")
                tvCurrencies.text = requireContext().getString(R.string.country_currencies,
                        it.currencies?.joinToString("\n") ?: "—")
                tvName.text = requireContext()
                    .getString(R.string.country_name, it.name, it.alpha2Code, it.alpha3Code)
                tvRegion.text = requireContext()
                    .getString(R.string.country_region, it.region, it.subregion)
                tvTimezones.text = requireContext()
                    .getString(R.string.country_timezones,
                        it.timezones.mapIndexed { index, s ->
                            if (index % 3 == 0) '\n' + s else s
                        }.joinToString(", "))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}