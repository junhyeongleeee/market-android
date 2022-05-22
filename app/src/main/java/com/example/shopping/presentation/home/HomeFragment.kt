package com.example.shopping.presentation.home

import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.databinding.FragmentHomeBinding

class HomeFragment: BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel by viewModel<HomeViewModel>()

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun observeData() {
    }

    override fun initViews() {
    }
}