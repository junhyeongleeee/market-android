package com.example.shopping.presentation.my.auth.navigation.register

import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.example.shopping.extensions.snackbar
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
            is RegisterState.Success -> { handleSuccess()}
            is RegisterState.Failure -> { handleFailure()}
        }
    }

    private fun handleFailure() {
        snackbar(binding.root, "회원가입이 실패하였습니다!!")
    }

    private fun handleSuccess() {
        snackbar(binding.root, "회원가입이 완료되었습니다!!")
        findNavController().popBackStack()
    }

    override fun initViews() = with(binding){

        appBar.titleTextView.text = "회원가입"

        emailEditText.requestFocus()
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)

        appBar.back.setOnClickListener {
            findNavController().popBackStack()
        }

        registerButton.setOnClickListener {
            if(agreeCheckBox.isChecked){
                //TODO : 회원가입 처리
                val email = emailEditText.text.toString().trim()
                val userName = nameEditText.text.toString().trim()
                val password = pwdEditText.text.toString().trim()
                val phone = phoneEditText.text.toString().trim()

                viewModel.createUser(userName, email, password, phone)
            }
            else{
                Snackbar.make(root, "약관에 동의해주세요.", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}