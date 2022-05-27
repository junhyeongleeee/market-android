package com.example.shopping.presentation.my.auth

import android.content.Context
import android.content.Intent
import com.example.shopping.model.type.StatusBarColorType
import com.example.shopping.model.type.TransitionModeType
import com.example.shopping.presentation.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.databinding.ActivityAuthBinding

class AuthActivity : BaseActivity<AuthViewModel, ActivityAuthBinding>(
    TransitionModeType.HORIZON,
    StatusBarColorType.SILVER_STATUS_BAR
) {

    override val viewModel by viewModel<AuthViewModel>()

    override fun getViewBinding(): ActivityAuthBinding =
        ActivityAuthBinding.inflate(layoutInflater)

    override fun observeData() {

    }

    companion object {
        fun newIntent(context: Context) =
            Intent(context, AuthActivity::class.java)
    }
}