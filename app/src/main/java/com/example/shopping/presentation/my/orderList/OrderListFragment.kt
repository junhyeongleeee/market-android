package com.example.shopping.presentation.my.orderList

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.shopping.extensions.toast
import com.example.shopping.model.recyclerView.product.order.OrderModel
import com.example.shopping.model.remote.order.OrderRefundCancelModel
import com.example.shopping.model.type.OrderType
import com.example.shopping.presentation.adapter.model.ModelRecyclerAdapter
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.listener.order.OrderListListener
import com.example.shopping.presentation.my.refundList.RefundListState
import com.example.shopping.presentation.my.refundList.RefundListViewModel
import com.example.shopping.util.provider.ResourcesProvider
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.databinding.FragmentOrderListBinding

class OrderListFragment : BaseFragment<OrderListViewModel, FragmentOrderListBinding>() {

    override val viewModel by viewModel<OrderListViewModel>()

    override fun getViewBinding(): FragmentOrderListBinding =
        FragmentOrderListBinding.inflate(layoutInflater)

    private val resourcesProvider by inject<ResourcesProvider>()

    private val adapter: ModelRecyclerAdapter<OrderModel, OrderListViewModel> by lazy {
        ModelRecyclerAdapter(
            listOf(),
            viewModel,
            resourcesProvider,
            adapterListener = object : OrderListListener {
                override fun onCancelButton(model: OrderModel) {
                    viewModel.requestCancel(model.uid,
                        OrderRefundCancelModel(
                            reason = null,
                            status = OrderType.Cancelling.type
                        )
                    )
                }
                override fun onRepurchaseButton(model: OrderModel) {

                }
                override fun onRefundButtonButton(model: OrderModel) {
                    viewModel.requestCancel(model.uid,
                        OrderRefundCancelModel(
                            reason = null,
                            status = OrderType.Refunding.type
                        )
                    )
                }
                override fun onCanceledDetailButton(model: OrderModel) {

                }

                override fun onOrderDeleteButton(model: OrderModel) {
                    viewModel.deleteOrder(model.uid)
                }
            })
    }

    override fun observeData() = with(binding){

        viewModel.orderListStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is RefundListState.UnInitialized -> {

                }
                is RefundListState.Loading -> {
                    handleLoading()
                }
                is RefundListState.Success -> {
                    handleSuccess()
                }
                is RefundListState.Failure -> {
                    handleFailure()
                }
            }
        }

        viewModel.orderItemListLiveData.observe(viewLifecycleOwner){
            if(it.isEmpty()){
                noExistDataLayout.isVisible = true
                existDataLayout.isGone = true
            }
            else{
                noExistDataLayout.isGone = true
                existDataLayout.isVisible = true
            }

            progressBar.isGone = true
            adapter.submitList(it)
        }
    }

    private fun handleFailure() = with(binding){
        toast(requireActivity(), "주문 내역 가져오기 실패!!")
        noExistDataLayout.isVisible = true
        existDataLayout.isGone = true
        progressBar.isGone = true
    }

    private fun handleSuccess() = with(binding){
        progressBar.isGone = true
        toast(requireActivity(), "주문 취소가 완료되었습니다.")
    }

    private fun handleLoading() = with(binding){
        progressBar.isVisible = true
    }

    override fun initViews() = with(binding) {
        recyclerView.adapter = adapter

        back.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}