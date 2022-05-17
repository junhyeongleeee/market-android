package com.example.shopping.presentation.category

import com.example.shopping.presentation.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.databinding.FragmentCategoryBinding

class CategoryFragment: BaseFragment<CategoryViewModel, FragmentCategoryBinding>() {

    override val viewModel by viewModel<CategoryViewModel>()

    override fun getViewBinding(): FragmentCategoryBinding =
        FragmentCategoryBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.categoryStateLiveData.observe(this){
        when(it){
            CategoryState.UnInitialized -> {
                initViews()
            }
        }
    }

    private fun initViews() = with(binding){

    }
}