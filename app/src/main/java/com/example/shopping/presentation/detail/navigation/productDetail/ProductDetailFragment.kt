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
import kotlin.study.shopping.databinding.FragmentDetailProductBinding

class ProductDetailFragment: BaseFragment<ProductDetailViewModel, FragmentDetailProductBinding>() {
    override val viewModel by viewModel<ProductDetailViewModel>()

    override fun getViewBinding(): FragmentDetailProductBinding =
        FragmentDetailProductBinding.inflate(layoutInflater)

    private val productModel : ProductModel? by lazy {
        arguments?.getParcelable<ProductModel>(ProductDetailActivity.PRODUCT_MODEL_KEY) ?: null
    }

    override fun observeData() = viewModel.productDetailStateLiveData.observe(this){
        when(it){
        }
    }

    override fun initViews(): Unit = with(binding){

        productModel?.let { model ->
            productImg.load(model.image_url ?: "", )
            productNameTextView.text = model.name
            productPriceTextView.text = model.price.toString()

        }

        appBar.back.setOnClickListener {
            requireActivity().finish()
        }
    }
}