package com.example.shopping.presentation.detail.navigation.order

import android.util.Log
import androidx.navigation.fragment.findNavController
import com.example.shopping.data.entity.product.order.OrderItemEntity
import com.example.shopping.extensions.load
import com.example.shopping.extensions.snackbar
import com.example.shopping.model.product.ProductModel
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.detail.ProductDetailActivity
import com.example.shopping.presentation.detail.navigation.productDetail.ProductDetailFragment
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.study.shopping.databinding.FragmentDetailProductBinding
import kotlin.study.shopping.databinding.FragmentOrderBinding

class OrderFragment : BaseFragment<OrderViewModel, FragmentOrderBinding>() {

    override val viewModel by viewModel<OrderViewModel>()

    override fun getViewBinding(): FragmentOrderBinding =
        FragmentOrderBinding.inflate(layoutInflater)

    val orderItemEntity: OrderItemEntity? by lazy {
        arguments?.getParcelable<OrderItemEntity>(ProductDetailFragment.ORDER_ITEM_KEY)
    }
    private val orderTotalPrice: String? by lazy {
        arguments?.getString(ProductDetailFragment.TOTAL_PRICE_KEY) ?: null
    }

    override fun observeData() {

    }

    override fun initViews() = with(binding) {
        appBar.titleTextView.text = "주문/결제"

        orderTotalPrice?.let {
            totalPrice.text = "$it 원"
            totalResult.text = "$it 원"
        }

        orderButton.setOnClickListener {
            // TODO : 주문 로직

        }
    }
}