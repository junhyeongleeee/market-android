package com.example.shopping.presentation.search

import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.databinding.FragmentHomeBinding
import kotlin.study.shopping.databinding.FragmentSearchBinding

class SearchFragment: BaseFragment<SearchViewModel, FragmentSearchBinding>() {

    override val viewModel by viewModel<SearchViewModel>()

    override fun getViewBinding(): FragmentSearchBinding =
        FragmentSearchBinding.inflate(layoutInflater)

    override fun observeData() {
    }

    override fun initViews() {

    }
}