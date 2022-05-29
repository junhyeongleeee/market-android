package com.example.shopping.presentation.my.auth.navigation.login

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import com.example.shopping.extensions.snackbar
import com.example.shopping.presentation.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.FragmentLoginBinding

class LoginFragment: BaseFragment<LoginViewModel, FragmentLoginBinding>() {

    override val viewModel by viewModel<LoginViewModel>()

    override fun getViewBinding(): FragmentLoginBinding =
        FragmentLoginBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.loginStateLiveData.observe(this){
        when(it){
            is LoginState.UnInitialized -> {

                // TODO : 화면 Animation 이 끝난 후에 키보드 올리기
            }
            is LoginState.Loading -> {}
            is LoginState.Success -> { handleSuccess()}
            is LoginState.Failure -> { handleFailure()}
        }
    }

    private fun handleFailure() {
        snackbar(binding.root, "로그인 실패!!")
    }

    private fun handleSuccess() {
        snackbar(binding.root, "로그인 성공!!")
        requireActivity().finish()
    }
    override fun initViews() = with(binding){

        // emailEditText 로 포커스
        emailEditText.requestFocus()
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)

        val emailWatcher = Watcher(emailDeleteButton)
        emailEditText.addTextChangedListener(emailWatcher)


        emailDeleteButton.setOnClickListener {
            emailEditText.setText("")
            emailDeleteButton.isGone = true
        }

        val pwdWatcher = Watcher(pwdVisibility)
        pwdEditText.addTextChangedListener(pwdWatcher)

        pwdVisibility.setOnClickListener {

            if(pwdEditText.transformationMethod == null) {
                pwdEditText.transformationMethod = PasswordTransformationMethod.getInstance()
                pwdVisibility.text = "보기"
            }
            else {
                pwdEditText.transformationMethod = null
                pwdVisibility.text = "숨기기"
            }

            pwdEditText.setSelection(pwdEditText.length())
        }

        loginButton.setOnClickListener{

            val email = emailEditText.text.toString().trim()
            val password = pwdEditText.text.toString().trim()

            viewModel.login(email, password)
        }

        registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_navLogin_to_navRegister)
        }
    }

    class Watcher(val view: View) : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, cnt: Int, after: Int) {

        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, cnt: Int) {
            val text = s.toString()
            if (text.isNotEmpty()) view.isVisible = true
            else view.isGone = true
        }
        override fun afterTextChanged(p0: Editable?) {
        }
    }
}