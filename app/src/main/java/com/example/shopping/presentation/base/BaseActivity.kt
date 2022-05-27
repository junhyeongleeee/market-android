package com.example.shopping.presentation.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.example.shopping.model.type.StatusBarColorType
import com.example.shopping.model.type.TransitionModeType
import kotlinx.coroutines.Job
import kotlin.study.shopping.R

abstract class BaseActivity<VM: BaseViewModel, VB: ViewBinding>(
    private val transitionMode: TransitionModeType = TransitionModeType.NONE,
    private val statusBarColorType: StatusBarColorType = StatusBarColorType.WHITE_STATUS_BAR
): AppCompatActivity() {

    abstract val viewModel: VM

    protected lateinit var binding: VB

    abstract fun getViewBinding(): VB

    private lateinit var fetch: Job

    protected val imm: InputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when (transitionMode) {
            TransitionModeType.HORIZON -> overridePendingTransition(R.anim.slide_from_right, R.anim.slide_none)
            TransitionModeType.VERTICAL -> overridePendingTransition(R.anim.slide_vertical_enter, R.anim.slide_none)
            else -> Unit
        }

        setStatusBarColor()

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
            TransitionModeType.HORIZON -> overridePendingTransition(R.anim.slide_none, R.anim.slide_to_right)
            TransitionModeType.VERTICAL -> overridePendingTransition(R.anim.slide_none, R.anim.slide_vertical_exit)
            else -> Unit
        }
    }

    private fun setStatusBarColor(){

        when(statusBarColorType){
            StatusBarColorType.WHITE_STATUS_BAR -> this.window.statusBarColor = ContextCompat.getColor(this, R.color.white)
            StatusBarColorType.SILVER_STATUS_BAR -> this.window.statusBarColor = ContextCompat.getColor(this, R.color.light_sliver)
        }
    }
}