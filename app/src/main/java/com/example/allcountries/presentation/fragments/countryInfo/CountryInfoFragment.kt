package com.example.allcountries.presentation.fragments.countryInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.allcountries.R
import com.example.allcountries.databinding.FragmentCountryInfoBinding
import com.example.allcountries.presentation.viewModels.CountryInfoViewModel
import com.example.allcountries.presentation.viewModels.impl.CountryInfoViewModelImpl
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryInfoFragment : Fragment(R.layout.fragment_country_info) {

    private val viewBinding: FragmentCountryInfoBinding by viewBinding(
        FragmentCountryInfoBinding::bind
    )
    private val viewModel: CountryInfoViewModel by viewModels<CountryInfoViewModelImpl>()
    private val args: CountryInfoFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.apply {
            Picasso.get()
                .load(args.data.flags?.png)
                .placeholder(R.drawable.placeholder)
                .into(ivFlag)
            tvCountryName.text =
                getString(R.string.country_name) + args.data.name
            tvCapitalCity.text =
                getString(R.string.capital_city) + args.data.capital
            tvRegion.text = getString(R.string.region) + args.data.region
            tvCurrency.text = args.data.currencies?.first().toString()
            tvTimezone.text =
                getString(R.string.timezone) + args.data.timezones?.first()

            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }

        }
    }
}