package com.example.shopping.presentation.my.userDetail

import androidx.navigation.fragment.findNavController
import com.example.shopping.data.entity.user.UserDetailEntity
import com.example.shopping.model.user.UserDetailModel
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.my.MyState
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
            is UserDetailState.Loading -> {}
            is UserDetailState.Success -> {}
            is UserDetailState.Failure -> {}
        }
    }

    override fun initViews() = with(binding){

        appBar.titleTextView.text = "내 정보 관리"
        userDetailEntity = arguments?.getParcelable("UserDetailEntity") ?: null

        userDetailEntity?.let {
            name.text = it.userName
            userDataName.text = it.userName
            pwd.text = "******"
            email.text = it.email
            phone.text = it.phone ?: "....."

        } ?: kotlin.run {
            name.text = "..."
            userDataName.text = "..."
            pwd.text = "..."
            email.text = "..."
            phone.text = "..."
        }

        appBar.back.setOnClickListener {
            findNavController().popBackStack()
        }

        logOutTextView.setOnClickListener {
            //TODO : 로그아웃 로직

        }
    }
}