package com.example.shopping.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.Job
import kotlin.study.shopping.R

abstract class BaseActivity<VM: BaseViewModel, VB: ViewBinding>(
    private val transitionMode: TransitionMode = TransitionMode.NONE
): AppCompatActivity() {

    abstract val viewModel: VM

    protected lateinit var binding: VB

    abstract fun getViewBinding(): VB

    private lateinit var fetch: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when (transitionMode) {
            TransitionMode.HORIZON -> overridePendingTransition(R.anim.slide_from_right, R.anim.slide_none)
            TransitionMode.VERTICAL -> overridePendingTransition(R.anim.slide_vertical_enter, R.anim.slide_none)
            else -> Unit
        }

        binding = getViewBinding()
        setContentView(binding.root)

        fetch = viewModel.fetch()
        observeData()
    }

    abstract fun observeData()

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun finish() {
        super.finish()

        when (transitionMode) {
            TransitionMode.HORIZON -> overridePendingTransition(R.anim.slide_none, R.anim.slide_to_right)
            TransitionMode.VERTICAL -> overridePendingTransition(R.anim.slide_none, R.anim.slide_vertical_exit)
            else -> Unit
        }
    }

    enum class TransitionMode {
        NONE,
        HORIZON,
        VERTICAL
    }
}