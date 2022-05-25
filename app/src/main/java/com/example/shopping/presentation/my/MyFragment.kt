package com.example.shopping.presentation.my

import com.example.shopping.extensions.load
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.my.auth.AuthActivity
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.FragmentMyBinding

class MyFragment: BaseFragment<MyViewModel, FragmentMyBinding>() {

    override val viewModel by viewModel<MyViewModel>()

    override fun getViewBinding(): FragmentMyBinding =
        FragmentMyBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.myStateLiveData.observe(this){
        when(it){
            is MyState.UnInitialized -> {}
            is MyState.Loading -> {}
            is MyState.Success -> { handleSuccess(it)}
            is MyState.Failure -> { handleFailure()}
        }
    }

    private fun handleFailure() = with(binding){
        // name
        nameTextView.text = "로그인"
        nameTextView.isClickable = true
        nameTextView.setOnClickListener {
            // TODO : 로그인 페이지로 이동
            startActivity(AuthActivity.newIntent(requireContext()))
        }
        // profile
        profileImg.load("", R.drawable.ic_my,  corner = 60f)
        profileImg.setOnClickListener {
            // TODO : 로그인 페이지로 이동
            startActivity(AuthActivity.newIntent(requireContext()))
        }
        // money
        payMoney.text = "-"

        // User Detail Data Layout
        payCache.text = "-"
        purchaseAfter.text = "-"
        likeProduct.text = "-"
        recentlyProduct.text = "-"
        frequentlyProduct.text = "-"
    }

    private fun handleSuccess(state: MyState.Success) = with(binding){
        // name
        nameTextView.text = state.userDetailModel.userName ?: ""
        nameTextView.setOnClickListener {
            // TODO : 내 정보관리 페이지로 이동
        }
        // profile
        profileImg.load("", R.drawable.ic_my,  corner = 60f)
        // money
        payMoney.text = "0"

        // User Detail Data Layout
        payCache.text = "0"
        purchaseAfter.text = "0"
        likeProduct.text = "0"
        recentlyProduct.text = "0"
        frequentlyProduct.text = "0"
    }

    override fun initViews() {
        viewModel.getUserData()

    }
}