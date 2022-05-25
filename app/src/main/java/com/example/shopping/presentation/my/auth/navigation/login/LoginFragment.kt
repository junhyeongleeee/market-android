package com.example.shopping.presentation.my.auth.navigation.login

import androidx.navigation.fragment.findNavController
import com.example.shopping.presentation.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.FragmentLoginBinding

class LoginFragment: BaseFragment<LoginViewModel, FragmentLoginBinding>() {
    override val viewModel by viewModel<LoginViewModel>()

    override fun getViewBinding(): FragmentLoginBinding =
        FragmentLoginBinding.inflate(layoutInflater)

    override fun observeData() {
    }

    override fun initViews() = with(binding){
        registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_navLogin_to_navRegister)
        }
    }
}