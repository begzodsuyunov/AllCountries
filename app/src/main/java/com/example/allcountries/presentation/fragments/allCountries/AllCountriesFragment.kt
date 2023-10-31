package com.example.allcountries.presentation.fragments.allCountries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.allcountries.R
import com.example.allcountries.databinding.FragmentAllCountriesBinding
import com.example.allcountries.presentation.fragments.allCountries.adapter.AllCountriesAdapter
import com.example.allcountries.presentation.viewModels.AllCountriesViewModel
import com.example.allcountries.presentation.viewModels.impl.AllCountriesViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AllCountriesFragment : Fragment(R.layout.fragment_all_countries) {
    private val viewBinding: FragmentAllCountriesBinding by viewBinding(
        FragmentAllCountriesBinding::bind
    )
    private val viewModel: AllCountriesViewModel by viewModels<AllCountriesViewModelImpl>()
    private val adapter: AllCountriesAdapter = AllCountriesAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.rv.adapter = adapter
        viewModel.countries.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewBinding.ivUpdate.setOnClickListener {

            viewModel.getAllCountries()
        }

        viewModel.loading.onEach {
            if (it.fullScreen) {
                viewBinding.progress.visibility = View.VISIBLE
                viewBinding.loadingView.visibility = View.VISIBLE
                delay(5000)
                viewBinding.progress.hide()
                viewBinding.loadingView.visibility = View.GONE
            } else {
                viewBinding.progress.visibility = View.GONE
                viewBinding.loadingView.visibility = View.GONE
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.hasConnection.onEach {
            if (it) Toast.makeText(requireContext(), "Yes Internet", Toast.LENGTH_SHORT).show()
            else Toast.makeText(requireContext(), "No Internet", Toast.LENGTH_SHORT).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.failureFlow.onEach {
            Toast.makeText(requireContext(), "$it - oops", Toast.LENGTH_SHORT).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            findNavController().navigate(
                AllCountriesFragmentDirections.actionAllCountriesFragmentToCountryInfoFragment(it)
            )
        }
    }

}