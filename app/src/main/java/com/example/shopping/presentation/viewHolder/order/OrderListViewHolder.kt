package com.example.shopping.presentation.viewHolder.order

import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.shopping.extensions.load
import com.example.shopping.model.product.order.OrderItemModel
import com.example.shopping.model.product.order.OrderModel
import com.example.shopping.presentation.adapter.model.ModelViewHolder
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.listener.AdapterListener
import okhttp3.internal.notifyAll
import kotlin.study.shopping.databinding.ViewholderOrderListBinding

class OrderListViewHolder(
    private val binding: ViewholderOrderListBinding,
    val viewModel: BaseViewModel
): ModelViewHolder<OrderModel>(binding) {

    override fun bindViews(itemModel: OrderModel, adapterListener: AdapterListener){
        /*if( adapterListener is ProductListListener){
            binding.root.setOnClickListener {
                adapterListener.onClickItem(model)
            }
        }*/
    }

    override fun bindData(orderModel: OrderModel) = with(binding){

        val size = orderModel.items.size
        if(size > 1){
            moreProductTextView.isVisible = true
            moreProductTextView.text = "~외 $size 개"
        }
        else{
            moreProductTextView.isGone = true
        }

        name.text = orderModel.items[0].product_name
        price.text = orderModel.items[0].product_price.toString() + "원 "
        count.text = orderModel.items[0].count.toString() + "원"
    }
}
