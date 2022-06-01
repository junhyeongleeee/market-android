package com.example.shopping.presentation.detail.navigation.productDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.data.local.AppPreferenceManager
import com.example.shopping.domain.repository.product.ProductRepositoryImpl
import com.example.shopping.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductDetailViewModel(
    private val product_id: String,
    private val productRepositoryImpl: ProductRepositoryImpl,
    private val preference: AppPreferenceManager,
) : BaseViewModel() {

    private var _productDetailStateLiveData =
        MutableLiveData<ProductDetailState>(ProductDetailState.UnInitialized)
    val productDetailStateLiveData: LiveData<ProductDetailState> = _productDetailStateLiveData

    override fun fetch(): Job = viewModelScope.launch {
        getProductDetail()
    }

    private fun getProductDetail() = viewModelScope.launch {
        _productDetailStateLiveData.postValue(ProductDetailState.Loading)

        productRepositoryImpl.getProductDetail(product_id)?.let {
            _productDetailStateLiveData.postValue(ProductDetailState.Success(
                it.toModel()
            ))
        } ?: kotlin.run { _productDetailStateLiveData.postValue(ProductDetailState.Failure) }
    }

    fun checkUser(): Boolean {

        preference.getString(AppPreferenceManager.ACCESS_TOKEN)?.let {
            Log.e("ACCESS_TOKEN", it)
            return true
        } ?: kotlin.run {
            return false
        }
    }
}