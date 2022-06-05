package com.example.shopping.presentation.viewHolder.order

import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.shopping.model.recyclerView.product.order.OrderItemModel
import com.example.shopping.model.recyclerView.product.order.RefundModel
import com.example.shopping.model.type.OrderType
import com.example.shopping.presentation.adapter.model.ModelRecyclerAdapter
import com.example.shopping.presentation.adapter.model.ModelViewHolder
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.listener.AdapterListener
import com.example.shopping.presentation.listener.order.OrderListListener
import com.example.shopping.util.provider.ResourcesProvider
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.ViewholderOrderListBinding

class RefundListViewHolder(
    private val binding: ViewholderOrderListBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
) : ModelViewHolder<RefundModel>(binding, viewModel, resourcesProvider) {

    override fun bindViews(model: RefundModel, adapterListener: AdapterListener) = with(binding) {

        if (adapterListener is OrderListListener) {
            binding.cancelButton.setOnClickListener {
                adapterListener.onCancelButton(model.order)
            }
            binding.repurchaseButton.setOnClickListener {
                adapterListener.onRepurchaseButton(model.order)
            }
            binding.refundButton.setOnClickListener {
                adapterListener.onRefundButtonButton(model.order)
            }
            binding.canceledDetailButton.setOnClickListener {
                adapterListener.onCanceledDetailButton(model.order)
            }
            binding.canceledRepurchaseButton.setOnClickListener {
                adapterListener.onRepurchaseButton(model.order)
            }
            binding.orderDeleteButton.setOnClickListener {
                adapterListener.onOrderDeleteButton(model.order)
            }
        }
        // TODO : 주문 내역 삭제 작성
    }

    override fun bindData(refundModel: RefundModel) = with(binding) {

        date.text = refundModel.updated_at.split("T")[0]
        orderStateView(refundModel.status)
    }

    private fun orderStateView(status: String) = with(binding) {
        when (status) {
            OrderType.Cancelled.type -> {
                // TODO : 취소 완료 된 주문
                // - 재구매
                orderPendingGroup.isGone = true
                orderCompletedGroup.isGone = true
                orderCanceledGroup.isVisible = true
                statusTextView.text = resourcesProvider.getString(R.string.canceled)
            }
            OrderType.Cancelling.type -> {
                // TODO : 취소 중 주문
                // - 아무것도 못함.
                orderPendingGroup.isGone = true
                orderCompletedGroup.isGone = true
                orderCanceledGroup.isGone = true
                statusTextView.text = resourcesProvider.getString(R.string.canceling)
            }
            OrderType.Refunded.type -> {
                // TODO : 환불 된 주문
                // - 재구매
                orderPendingGroup.isGone = true
                orderCompletedGroup.isVisible = true
                orderCanceledGroup.isGone = true
                statusTextView.text = resourcesProvider.getString(R.string.refunded)
            }
            OrderType.Refunding.type -> {
                // TODO : 환불 중 주문
                // - 아무것도 못함.
                orderPendingGroup.isGone = true
                orderCompletedGroup.isGone = true
                orderCanceledGroup.isGone = true
                statusTextView.text = resourcesProvider.getString(R.string.refunding)
            }
        }
    }
}
