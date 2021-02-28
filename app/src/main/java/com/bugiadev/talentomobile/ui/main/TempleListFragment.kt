package com.bugiadev.talentomobile.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.bugiadev.talentomobile.databinding.FragmentTempleListBinding
import com.bugiadev.talentomobile.di.ChurchComponent
import com.bugiadev.talentomobile.di.ChurchInjection
import com.bugiadev.talentomobile.ui.fragment.BaseFragment
import com.bugiadev.talentomobile.ui.presentation.toDisplay
import com.bugiadev.talentomobile.ui.views.adapter.ChurchAdapter
import com.bugiadev.talentomobile.utils.viewModel

class TempleListFragment: BaseFragment<FragmentTempleListBinding>() {
    private lateinit var churchComponent: ChurchComponent
    private val viewModel: ChurchViewModel by viewModel { churchComponent.churchViewModel }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentTempleListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        viewModel.getNonCatholicChurches()
    }

    private fun initObservers() {
        viewModel.loading.observe(viewLifecycleOwner, { isLoading ->
            showLoader(isLoading)
        })

        viewModel.nonCatholicChurches.observe(viewLifecycleOwner, { churches ->
            val templeDisplays = churches.map {
                it.toDisplay()
            }

            val adapter = ChurchAdapter(
                onItemClick = {
                    viewModel.onTempleSelected(it)
                }, templeDisplays
            )

            binding.templeList.adapter = adapter
            binding.templeList.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        })

        viewModel.selectedTemple.observe(viewLifecycleOwner, { selectedTemple ->
            findNavController().navigate(
                TempleListFragmentDirections.actionNonCatholicListToDetail(selectedTemple)
            )
        })

        viewModel.error.observe(viewLifecycleOwner, { error ->
            showErrorState(error)
        })
    }

    override fun createDaggerComponent() {
        super.createDaggerComponent()
        churchComponent = (activity as ChurchInjection).getChurchComponent()
        churchComponent.inject(this)
    }
}