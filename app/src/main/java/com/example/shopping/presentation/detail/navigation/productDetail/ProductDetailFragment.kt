package com.example.shopping.presentation.detail.navigation.productDetail

import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.example.shopping.data.entity.product.order.OrderItemEntity
import com.example.shopping.extensions.load
import com.example.shopping.extensions.snackbar
import com.example.shopping.model.product.ProductModel
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.detail.ProductDetailActivity
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.study.shopping.R
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
        Log.e("handleSuccess", state.productModel.toString())
        productDataFetch(state.productModel)
    }

    override fun initViews(): Unit = with(binding){

        productModel?.let { model ->
            productDataFetch(model)

            orderButton.setOnClickListener {
                if(viewModel.checkUser()){
                    val bundle = Bundle()
                    bundle.putParcelable(ORDER_ITEM_KEY, OrderItemEntity(
                        uid = model.uid,
                        count = 1
                    ))
                    bundle.putString(TOTAL_PRICE_KEY, model.price.toString())
                    findNavController().navigate(R.id.action_navProductDetail_to_navOrder, bundle)
                }
                else{
                    snackbar(root, "로그인 후 이용해 주세요!")
                }
            }
        }

        appBar.back.setOnClickListener {
            requireActivity().finish()
        }

    }

    private fun productDataFetch(model: ProductModel) = with(binding){
        productImg.load(model.image_url ?: "", )
        productNameTextView.text = model.name
        productPriceTextView.text = model.price.toString()
    }

    companion object{
        const val ORDER_ITEM_KEY = "orderItem"
        const val TOTAL_PRICE_KEY = "total_price"
    }
}