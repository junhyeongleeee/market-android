package com.example.shopping.presentation.detail.navigation.order

import androidx.navigation.fragment.findNavController
import com.example.shopping.extensions.snackbar
import com.example.shopping.model.recyclerView.product.order.OrderRequestListModel
import com.example.shopping.model.remote.order.OrderRequestModel
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.detail.navigation.productDetail.ProductDetailFragment
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.databinding.FragmentOrderBinding

class OrderFragment : BaseFragment<OrderViewModel, FragmentOrderBinding>() {

    override val viewModel by viewModel<OrderViewModel>()

    override fun getViewBinding(): FragmentOrderBinding =
        FragmentOrderBinding.inflate(layoutInflater)

    private val orderRequestModel: OrderRequestModel? by lazy {
        arguments?.getParcelable<OrderRequestModel>(ProductDetailFragment.ORDER_ITEM_KEY)
    }
    private val orderTotalPrice: String? by lazy {
        arguments?.getString(ProductDetailFragment.TOTAL_PRICE_KEY) ?: null
    }

    override fun observeData(){

        viewModel.orderStateLiveData.observe(this) {
            when (it) {
                is OrderState.UnInitialized -> {
                }
                is OrderState.Loading -> {
                }
                is OrderState.Success -> {
                    handleSuccess(it)
                }
                is OrderState.Failure -> {
                    handleFailure()
                }
            }
        }
    }

    private fun handleFailure() {
        snackbar(binding.root, "주문 실패!!")
    }

    private fun handleSuccess(state: OrderState.Success) {
        snackbar(binding.root, "주문 성공!!")
        findNavController().popBackStack()
    }

    override fun initViews() = with(binding) {
        appBar.titleTextView.text = "주문/결제"
        appBar.back.setOnClickListener {
            findNavController().popBackStack()
        }

        orderTotalPrice?.let {
            totalPrice.text = "$it 원"
            totalResult.text = "$it 원"
        }

        orderButton.setOnClickListener {
            // TODO : 주문 로직
            var list = mutableListOf<OrderRequestModel>()

            orderRequestModel?.let {
                list.add(it)
                viewModel.orderProduct(OrderRequestListModel(list))
            }
        }
    }
}