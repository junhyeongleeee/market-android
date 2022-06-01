package com.example.shopping.presentation.detail

import com.example.shopping.presentation.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.databinding.FragmentDetailProductBinding

class ProductDetailFragment: BaseFragment<ProductDetailViewModel, FragmentDetailProductBinding>() {
    override val viewModel by viewModel<ProductDetailViewModel>()

    override fun getViewBinding(): FragmentDetailProductBinding =
        FragmentDetailProductBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.productDetailStateLiveData.observe(this){
        when(it){

        }
    }


}