package com.bugiadev.talentomobile.ui.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bugiadev.talentomobile.R
import com.bugiadev.talentomobile.databinding.ChurchItemViewBinding
import com.bugiadev.talentomobile.ui.presentation.ChurchDisplay
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ChurchAdapter(
        private val onItemClick: ((ChurchDisplay) -> Unit)? = null,
        private val items: List<ChurchDisplay>
) : RecyclerView.Adapter<ChurchAdapter.ChurchItemViewHolder>() {

    class ChurchItemViewHolder(
            private val binding: ChurchItemViewBinding,
            private val onClickListener: ((ChurchDisplay) -> Unit)? = null
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ChurchDisplay) {
            binding.churchRow.setOnClickListener {
                onClickListener?.let {
                    it(data)
                }
            }

            binding.churchNameTextView.text = data.name
            Glide.with(binding.churchImage.context)
                    .load(getImageUrl(data.isCatholic))
                    .placeholder(R.drawable.image_placeholder)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.churchImage)
        }

        private fun getImageUrl(isCatholic: Boolean): String =
                if (isCatholic) "https://www.clipartkey.com/mpngs/m/196-1964749_transparent-catholic-clipart-bible-clip-art.png"
                else "https://thumbs.dreamstime.com/b/la-mandala-cielo-abierto-ornamento-circular-elegante-con-om-el-aum-ohmio-firma-adentro-centro-126463760.jpg"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChurchItemViewHolder =
        ChurchItemViewHolder(
                ChurchItemViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                ), onItemClick
        )


    override fun onBindViewHolder(holder: ChurchItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}