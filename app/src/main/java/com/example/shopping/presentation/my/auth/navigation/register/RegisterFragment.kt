package com.example.shopping.presentation.my.auth.navigation.register

import androidx.navigation.fragment.findNavController
import com.example.shopping.presentation.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.databinding.FragmentRegisterBinding

class RegisterFragment: BaseFragment<RegisterViewModel, FragmentRegisterBinding>() {
    override val viewModel by viewModel<RegisterViewModel>()

    override fun getViewBinding(): FragmentRegisterBinding =
        FragmentRegisterBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.registerStateLiveData.observe(this){
        when(it){
            is RegisterState.UnInitialized -> {}
            is RegisterState.Loading -> {}
            is RegisterState.Success -> {}
            is RegisterState.Failure -> {}
        }
    }

    override fun initViews() = with(binding){

        appBar.titleTextView.text = "회원가입"

        appBar.back.setOnClickListener {
            findNavController().popBackStack()
        }

        registerButton.setOnClickListener {
            if(agreeCheckBox.isChecked){
                //TODO : 회원가입 처리

                findNavController().popBackStack()
            }
            else{
                Snackbar.make(root, "약관에 동의해주세요.", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}