package com.bugiadev.talentomobile.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.bugiadev.talentomobile.databinding.FragmentChurchListBinding
import com.bugiadev.talentomobile.di.ChurchComponent
import com.bugiadev.talentomobile.di.ChurchInjection
import com.bugiadev.talentomobile.ui.fragment.BaseFragment
import com.bugiadev.talentomobile.ui.presentation.toDisplay
import com.bugiadev.talentomobile.ui.views.adapter.ChurchAdapter
import com.bugiadev.talentomobile.utils.viewModel

class ChurchListFragment : BaseFragment<FragmentChurchListBinding>() {
    private lateinit var churchComponent: ChurchComponent
    private val viewModel: ChurchViewModel by viewModel { churchComponent.churchViewModel }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChurchListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        viewModel.getCatholicChurches()
    }

    private fun initObservers() {
        viewModel.loading.observe(viewLifecycleOwner, { isLoading ->
            showLoader(isLoading)
        })

        viewModel.catholicChurches.observe(viewLifecycleOwner, { churches ->
            val churchDisplays = churches.map {
                it.toDisplay()
            }

            val adapter = ChurchAdapter(
                    onItemClick = {
                        viewModel.onChurchSelected(it)
                    }, churchDisplays
            )

            binding.churchesList.adapter = adapter
            binding.churchesList.addItemDecoration(
                    DividerItemDecoration(
                            requireContext(),
                            DividerItemDecoration.VERTICAL
                    )
            )
        })

        viewModel.selectedChurch.observe(viewLifecycleOwner, { selectedChurch ->
            findNavController().navigate(
                    ChurchListFragmentDirections.actionCatholicListToDetail(selectedChurch)
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