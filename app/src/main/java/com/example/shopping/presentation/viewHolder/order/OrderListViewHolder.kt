package com.example.shopping.presentation.viewHolder.order

import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.shopping.extensions.load
import com.example.shopping.model.product.order.OrderItemModel
import com.example.shopping.model.product.order.OrderModel
import com.example.shopping.presentation.adapter.model.ModelRecyclerAdapter
import com.example.shopping.presentation.adapter.model.ModelViewHolder
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.listener.AdapterListener
import com.example.shopping.presentation.listener.OrderListListener
import com.example.shopping.presentation.my.orderList.OrderListViewModel
import okhttp3.internal.notifyAll
import kotlin.study.shopping.databinding.ViewholderOrderListBinding

class OrderListViewHolder(
    private val binding: ViewholderOrderListBinding,
    val viewModel: BaseViewModel
): ModelViewHolder<OrderModel>(binding) {

    override fun bindViews(model: OrderModel, adapterListener: AdapterListener){
        if( adapterListener is OrderListListener){
            binding.root.setOnClickListener {
                adapterListener.onClickItem(model)
            }
        }
    }

    override fun bindData(orderModel: OrderModel) = with(binding){

        date.text = orderModel.ordered_at

        val adapter = ModelRecyclerAdapter<OrderItemModel, BaseViewModel>(
            listOf(),
            viewModel,
            adapterListener = object : AdapterListener {
            })

        orderProductListRecyclerView.adapter = adapter
//        val decoration = DividerItemDecoration(, VERTICAL)
//        orderProductListRecyclerView.addItemDecoration(decoration)

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
}
