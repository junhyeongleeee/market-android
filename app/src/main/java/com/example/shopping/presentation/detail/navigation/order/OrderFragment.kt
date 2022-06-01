package com.example.shopping.presentation.detail.navigation.order

import android.util.Log
import androidx.navigation.fragment.findNavController
import com.example.shopping.data.entity.product.order.OrderItemEntity
import com.example.shopping.extensions.snackbar
import com.example.shopping.model.product.order.OrderListModel
import com.example.shopping.model.product.order.OrderModel
import com.example.shopping.presentation.RemoteState
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.detail.navigation.productDetail.ProductDetailFragment
import org.koin.android.viewmodel.ext.android.viewModel
import retrofit2.HttpException
import kotlin.study.shopping.databinding.FragmentOrderBinding

class OrderFragment : BaseFragment<OrderViewModel, FragmentOrderBinding>() {

    override val viewModel by viewModel<OrderViewModel>()

    override fun getViewBinding(): FragmentOrderBinding =
        FragmentOrderBinding.inflate(layoutInflater)

    private val orderModel: OrderModel? by lazy {
        arguments?.getParcelable<OrderModel>(ProductDetailFragment.ORDER_ITEM_KEY)
    }
    private val orderTotalPrice: String? by lazy {
        arguments?.getString(ProductDetailFragment.TOTAL_PRICE_KEY) ?: null
    }

    override fun observeData(){

        viewModel.fetchState.observe(this) {
            var message = ""
            when (it.second) {
                RemoteState.BAD_INTERNET -> {
                    message = "소켓 오류 / 서버와 연결에 실패하였습니다."
                }
                RemoteState.PARSE_ERROR -> {
                    val error = (it.first as HttpException)
                    message = "${error.code()} ERROR : \n ${
                        error.response()!!.errorBody()!!.string().split("\"")[7]
                    }"
                }
                RemoteState.WRONG_CONNECTION -> {
                    message = "호스트를 확인할 수 없습니다. 네트워크 연결을 확인해주세요"
                }
                else -> {
                    message = "통신에 실패하였습니다.\n ${it.first.message}"
                }

            }

            Log.d("********NETWORK_ERROR_MESSAGE : ", it.first.message.toString())
            snackbar(binding.root, message)
        }

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
            var list = mutableListOf<OrderModel>()

            orderModel?.let {
                list.add(it)
                viewModel.orderProduct(OrderListModel(list))
            }
        }
    }
}