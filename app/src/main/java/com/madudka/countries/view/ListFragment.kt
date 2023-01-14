package com.madudka.countries.view

import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter.*
import com.madudka.countries.R
import com.madudka.countries.databinding.FragmentListBinding
import com.madudka.countries.model.CountryModel
import com.madudka.countries.utils.showDialog
import com.madudka.countries.view.adapter.CountriesListAdapter
import com.madudka.countries.view.adapter.OnItemClickListener
import com.madudka.countries.viewmodel.CountriesViewModel

class ListFragment : BaseFragment<List<CountryModel>>() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val countriesViewModel: CountriesViewModel by activityViewModels()
    private val countriesListAdapter = CountriesListAdapter()
    private val clickListener = object : OnItemClickListener<CountryModel> {
        override fun onItemClick(item: CountryModel, position: Int) {
            val action = ListFragmentDirections.actionListFragmentToInfoFragment(position)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOptionMenuActions()

        val manager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val animation =
            AnimationUtils.loadLayoutAnimation(requireContext(), R.anim.layout_anim_fall_down)
        countriesListAdapter.apply {
            this.clickListener = this@ListFragment.clickListener
            stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
        binding.rvCountries.apply {
            adapter = countriesListAdapter
            layoutManager = manager
            layoutAnimation = animation
            setHasFixedSize(true)
        }

        countriesViewModel.getCountries().observe(viewLifecycleOwner) {
            setData(it)
            updateView()
        }

        countriesViewModel.getError().observe(viewLifecycleOwner) {
            it?.let {
                showDialog(requireContext(), it)
            }
        }
    }

    override fun updateView() {
        listData?.let { list ->
            countriesListAdapter.updateData(list)
            binding.rvCountries.scheduleLayoutAnimation()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setOptionMenuActions() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_refresh -> {
                        countriesViewModel.refreshData()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}