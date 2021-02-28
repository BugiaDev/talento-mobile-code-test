package com.bugiadev.talentomobile.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bugiadev.talentomobile.R
import com.bugiadev.talentomobile.databinding.FragmentChurchDetailBinding
import com.bugiadev.talentomobile.ui.fragment.BaseFragment

class ChurchDetailFragment : BaseFragment<FragmentChurchDetailBinding>() {
    private val args: ChurchDetailFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentChurchDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.name.text = args.church.name
        binding.confessionTextView.text = getString(R.string.catholic_confession)

        if (!args.church.description.isNullOrEmpty()) {
            binding.descriptionLayout.visibility = View.VISIBLE
            binding.descriptionTextView.text = args.church.description
        } else {
            binding.descriptionLayout.visibility = View.GONE
        }

        if (!args.church.address.isNullOrEmpty()) {
            binding.addressLayout.visibility = View.VISIBLE
            binding.addressTextView.text = buildFormattedAddress()
        } else {
            binding.addressLayout.visibility = View.GONE
        }

        handleBackButton {
            findNavController().navigateUp()
        }
    }

    private fun buildFormattedAddress(): String =
            "${args.church.address}\n${args.church.zipCode}\n${args.church.locality}"
}