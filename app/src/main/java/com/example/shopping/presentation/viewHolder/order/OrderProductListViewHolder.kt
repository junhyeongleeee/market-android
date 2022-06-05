package com.example.shopping.presentation.viewHolder.order

import com.example.shopping.extensions.load
import com.example.shopping.model.recyclerView.product.order.OrderItemModel
import com.example.shopping.presentation.adapter.model.ModelViewHolder
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.listener.AdapterListener
import com.example.shopping.util.provider.ResourcesProvider
import kotlin.study.shopping.databinding.ViewholderOrderProductListBinding

class OrderProductListViewHolder(
    private val binding: ViewholderOrderProductListBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
): ModelViewHolder<OrderItemModel>(binding, viewModel, resourcesProvider) {

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
