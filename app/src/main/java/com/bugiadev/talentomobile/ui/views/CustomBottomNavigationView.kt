package com.bugiadev.talentomobile.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.bugiadev.talentomobile.R
import com.bugiadev.talentomobile.databinding.ViewCustomBottomBarBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class CustomBottomNavigationView
@JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding: ViewCustomBottomBarBinding =
            ViewCustomBottomBarBinding.inflate(
                    LayoutInflater.from(context), this, true
            )
    val bottomNavigationView: BottomNavigationView = binding.customBottomView

    init {
        this.setPadding(0, resources.getDimensionPixelSize(R.dimen.small_space), 0, 0)
        bottomNavigationView.itemIconTintList = null
        bottomNavigationView.foregroundGravity = Gravity.CENTER
        bottomNavigationView.setBackgroundColor(
                ContextCompat.getColor(
                        context,
                        android.R.color.transparent
                )
        )
    }
}