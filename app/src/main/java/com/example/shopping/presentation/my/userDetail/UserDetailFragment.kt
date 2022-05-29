package com.example.shopping.presentation.my.userDetail

import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.databinding.FragmentHomeBinding
import kotlin.study.shopping.databinding.FragmentSearchBinding
import kotlin.study.shopping.databinding.FragmentUserDetailBinding

class UserDetailFragment: BaseFragment<UserDetailViewModel, FragmentUserDetailBinding>() {

    override val viewModel by viewModel<UserDetailViewModel>()

    override fun getViewBinding(): FragmentUserDetailBinding =
        FragmentUserDetailBinding.inflate(layoutInflater)

    override fun observeData() {
    }

    override fun initViews() {

    }
}