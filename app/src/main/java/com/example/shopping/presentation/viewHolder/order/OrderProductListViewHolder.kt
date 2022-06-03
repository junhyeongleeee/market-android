package com.example.shopping.presentation.viewHolder.order

import android.content.Context
import android.graphics.drawable.ClipDrawable.VERTICAL
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.shopping.extensions.load
import com.example.shopping.model.product.order.OrderItemModel
import com.example.shopping.model.product.order.OrderModel
import com.example.shopping.model.type.TransitionModeType
import com.example.shopping.presentation.adapter.model.ModelRecyclerAdapter
import com.example.shopping.presentation.adapter.model.ModelViewHolder
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.listener.AdapterListener
import com.example.shopping.presentation.listener.OrderListListener
import com.example.shopping.presentation.my.orderList.OrderListViewModel
import okhttp3.internal.notifyAll
import kotlin.study.shopping.databinding.ViewholderOrderListBinding
import kotlin.study.shopping.databinding.ViewholderOrderProductListBinding

class OrderProductListViewHolder(
    private val binding: ViewholderOrderProductListBinding,
    val viewModel: BaseViewModel
): ModelViewHolder<OrderItemModel>(binding) {

    override fun bindViews(model: OrderItemModel, adapterListener: AdapterListener){
        /*if( adapterListener is OrderListListener){
            binding.root.setOnClickListener {
                adapterListener.onClickItem(model)
            }
        }*/
    }

    override fun bindData(orderModel: OrderItemModel) = with(binding){

        name.text = orderModel.product_name
        price.text = orderModel.product_price.toString() + "원 "
        count.text = orderModel.count.toString() + "원"
        img.load(orderModel.product_image_url ?: "")
    }
}
