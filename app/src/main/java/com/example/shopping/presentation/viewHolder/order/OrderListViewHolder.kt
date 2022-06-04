package com.example.shopping.presentation.viewHolder.order

import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.shopping.model.product.order.OrderItemModel
import com.example.shopping.model.product.order.OrderModel
import com.example.shopping.model.type.OrderType
import com.example.shopping.presentation.adapter.model.ModelRecyclerAdapter
import com.example.shopping.presentation.adapter.model.ModelViewHolder
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.listener.AdapterListener
import com.example.shopping.presentation.listener.order.OrderListListener
import com.example.shopping.util.provider.ResourcesProvider
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.ViewholderOrderListBinding

class OrderListViewHolder(
    private val binding: ViewholderOrderListBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
) : ModelViewHolder<OrderModel>(binding, viewModel, resourcesProvider) {

    override fun bindViews(model: OrderModel, adapterListener: AdapterListener) = with(binding) {

        if (adapterListener is OrderListListener) {
            binding.cancelButton.setOnClickListener {
                adapterListener.onCancelButton(model)
            }
            binding.repurchaseButton.setOnClickListener {
                adapterListener.onRepurchaseButton(model)
            }
            binding.refundButton.setOnClickListener {
                adapterListener.onRefundButtonButton(model)
            }
            binding.canceledDetailButton.setOnClickListener {
                adapterListener.onCanceledDetailButton(model)
            }
            binding.canceledRepurchaseButton.setOnClickListener {
                adapterListener.onRepurchaseButton(model)
            }
        }
        // TODO : 주문 내역 삭제 작성
    }

    override fun bindData(orderModel: OrderModel) = with(binding) {

        date.text = orderModel.ordered_at.split("T")[0]

        val adapter = ModelRecyclerAdapter<OrderItemModel, BaseViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            adapterListener = object : AdapterListener {
            })

        orderProductListRecyclerView.adapter = adapter
//        val decoration = DividerItemDecoration(, VERTICAL)
//        orderProductListRecyclerView.addItemDecoration(decoration)

        orderStateView(orderModel.status)

        adapter.submitList(orderModel.items.mapIndexed { _, entity ->
            OrderItemModel(
                id = entity.hashCode().toLong(),
                uid = entity.uid,
                order_id = entity.order_id,
                product_id = entity.product_id,
                product_name = entity.product_name,
                product_price = entity.product_price,
                product_image_url = entity.product_image_url,
                count = entity.count
            )
        })
    }

    private fun orderStateView(status: String) = with(binding) {
        when (status) {
            OrderType.Pending.type -> {
                // TODO : 올바르지 않는 주문
                // - 주문 취소
                orderPendingGroup.isVisible = true
                orderCompletedGroup.isGone = true
                orderCanceledGroup.isGone = true
                statusTextView.text = resourcesProvider.getString(R.string.ordred)
            }
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
            OrderType.Delivered.type -> {
                // TODO : 배송 된 주문
                // - 환불 신청 가능
                orderPendingGroup.isGone = true
                orderCompletedGroup.isVisible = true
                orderCanceledGroup.isGone = true
                statusTextView.text = resourcesProvider.getString(R.string.delivered)
            }
            OrderType.Delivering.type -> {
                // TODO : 배송 중 주문
                // - 주문, 환불 불가
                orderPendingGroup.isGone = true
                orderCompletedGroup.isVisible = true
                orderCanceledGroup.isGone = true
                statusTextView.text = resourcesProvider.getString(R.string.delivering)
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
