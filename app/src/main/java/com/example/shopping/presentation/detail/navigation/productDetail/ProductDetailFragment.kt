package com.example.shopping.presentation.detail.navigation.productDetail

import android.util.Log
import androidx.navigation.fragment.findNavController
import com.example.shopping.extensions.load
import com.example.shopping.extensions.snackbar
import com.example.shopping.model.product.ProductModel
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.detail.ProductDetailActivity
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.study.shopping.databinding.FragmentDetailProductBinding

class ProductDetailFragment: BaseFragment<ProductDetailViewModel, FragmentDetailProductBinding>() {

    override val viewModel by viewModel<ProductDetailViewModel>{
        parametersOf(
            productModel?.uid
        )
    }

    override fun getViewBinding(): FragmentDetailProductBinding =
        FragmentDetailProductBinding.inflate(layoutInflater)

    private val productModel : ProductModel? by lazy {
        arguments?.getParcelable<ProductModel>(ProductDetailActivity.PRODUCT_MODEL_KEY) ?: null
    }

    override fun observeData() = viewModel.productDetailStateLiveData.observe(this){
        when(it){
            is ProductDetailState.UnInitialized -> {}
            is ProductDetailState.Success -> { handleSuccess(it)}
            is ProductDetailState.Loading -> {}
            is ProductDetailState.Failure -> {}
        }
    }

    private fun handleSuccess(state: ProductDetailState.Success) = with(binding){
        // TODO: 데이터 리로딩
        productDataFetch(state.productModel)
    }

    override fun initViews(): Unit = with(binding){

        productModel?.let { it ->
            productDataFetch(it)
        }

        appBar.back.setOnClickListener {
            requireActivity().finish()
        }

        orderButton.setOnClickListener {

        }
    }

    private fun productDataFetch(model: ProductModel) = with(binding){
        productImg.load(model.image_url ?: "", )
        productNameTextView.text = model.name
        productPriceTextView.text = model.price.toString()
    }
}