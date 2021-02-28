package com.bugiadev.talentomobile.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bugiadev.talentomobile.MainActivity
import com.bugiadev.talentomobile.R
import com.bugiadev.talentomobile.databinding.ActivitySplashBinding
import java.lang.IllegalStateException

class SplashActivity : AppCompatActivity() {
    private var _binding: ActivitySplashBinding? = null
    private var binding: ActivitySplashBinding
        get() = _binding
            ?: throw IllegalStateException("Binding is null, check the status of your Activity ${this::class}")
        set(value) {
            _binding = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_splash
        )

        binding.splashAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                //Do nothing
            }

            override fun onAnimationEnd(animation: Animator?) {
                Intent(applicationContext, MainActivity::class.java).apply {
                    startActivity(this)
                }
            }

            override fun onAnimationCancel(animation: Animator?) {
                //Do nothing
            }

            override fun onAnimationStart(animation: Animator?) {
                //Do nothing
            }

        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}