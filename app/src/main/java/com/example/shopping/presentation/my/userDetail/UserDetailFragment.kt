package com.example.shopping.presentation.my.userDetail

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.shopping.data.entity.user.UserDetailEntity
import com.example.shopping.model.user.UserDetailModel
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.my.MyState
import com.example.shopping.presentation.my.auth.AuthActivity
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.databinding.FragmentUserDetailBinding

class UserDetailFragment: BaseFragment<UserDetailViewModel, FragmentUserDetailBinding>() {

    override val viewModel by viewModel<UserDetailViewModel>()

    override fun getViewBinding(): FragmentUserDetailBinding =
        FragmentUserDetailBinding.inflate(layoutInflater)

    private var userDetailEntity: UserDetailEntity? = null

    override fun observeData() = viewModel.userDetailStateLiveData.observe(this){
        when(it){
            is UserDetailState.UnInitialized -> {}
            is UserDetailState.Loading -> { handleLoading()}
            is UserDetailState.Success -> { handleSuccess(it)}
            is UserDetailState.Failure -> { handleFailure()}
        }
    }

    private fun handleLoading() {
        binding.progressBar.isVisible = true
    }

    private fun handleFailure() = with(binding){

        desLoginTextView.setOnClickListener {

            //TODO : 로그인 성공 시 마이페이지로 돌아가는 FLOW 만들어야 함.

            startActivity(AuthActivity.newIntent(requireContext()))
        }

        headerSignOutLayout.isVisible = true
        headerSignInLayout.isGone = true
        profileGroup.isGone = true

        binding.progressBar.isGone = true
    }

    private fun handleSuccess(state: UserDetailState.Success) = with(binding){

        profileGroup.isVisible = true
        headerSignOutLayout.isGone = true
        headerSignInLayout.isVisible = true

        state.userDetailEntity.let {
            name.text = it.userName
            userDataName.text = it.userName
            pwd.text = "******"
            email.text = it.email
            phone.text = it.phone ?: "....."
        }

        binding.progressBar.isGone = true
    }

    override fun initViews() = with(binding){

        appBar.titleTextView.text = "내 정보 관리"
        userDetailEntity = arguments?.getParcelable("UserDetailEntity") ?: null

        appBar.back.setOnClickListener {
            findNavController().popBackStack()
        }

        logOutTextView.setOnClickListener {
            //TODO : 로그아웃 로직
            viewModel.signOut()
        }
    }
}