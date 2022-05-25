package com.example.shopping.presentation.my.auth.navigation.register

import com.example.shopping.presentation.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.databinding.FragmentRegisterBinding

class RegisterFragment: BaseFragment<RegisterViewModel, FragmentRegisterBinding>() {
    override val viewModel by viewModel<RegisterViewModel>()

    override fun getViewBinding(): FragmentRegisterBinding =
        FragmentRegisterBinding.inflate(layoutInflater)

    override fun observeData() {
    }
}