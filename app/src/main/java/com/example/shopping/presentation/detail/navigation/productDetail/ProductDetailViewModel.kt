package com.example.shopping.presentation.detail.navigation.productDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.domain.repository.product.ProductRepositoryImpl
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val product_id: String,
    private val productRepositoryImpl: ProductRepositoryImpl
): BaseViewModel() {

    private var _productDetailStateLiveData = MutableLiveData<ProductDetailState>(ProductDetailState.UnInitialized)
    val productDetailStateLiveData: LiveData<ProductDetailState> = _productDetailStateLiveData

    override fun fetch(): Job = viewModelScope.launch {
//        getProductDetail()
    }

    /*private fun getProductDetail() = viewModelScope.launch {
        _productDetailStateLiveData.postValue(ProductDetailState.Loading)

        productRepositoryImpl.getProductDetail(product_id)?.let {
            _productDetailStateLiveData.postValue(ProductDetailState.Success(
                it.toModel()
            ))
        } ?: kotlin.run { _productDetailStateLiveData.postValue(ProductDetailState.Failure) }
    }*/
}