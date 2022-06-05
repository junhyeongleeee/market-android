package com.example.shopping.presentation.adapter.model

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shopping.model.type.CellType
import aop.fastcampus.part6.chapter01.model.Model
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.viewHolder.category.CategoryViewHolder
import com.example.shopping.presentation.viewHolder.category.productsByCategory.ProductViewHolder
import com.example.shopping.presentation.viewHolder.home.HomeViewHolder
import com.example.shopping.presentation.viewHolder.order.OrderListViewHolder
import com.example.shopping.presentation.viewHolder.order.OrderProductListViewHolder
import com.example.shopping.presentation.viewHolder.order.RefundListViewHolder
import com.example.shopping.util.provider.ResourcesProvider
import kotlin.study.shopping.databinding.*

object ModelViewHolderMapper {
    fun<M: Model> map(
        parent: ViewGroup,
        type: CellType,
        viewModel: BaseViewModel,
        resourcesProvider: ResourcesProvider
    ): ModelViewHolder<M>{
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = when (type) {
            CellType.PRODUCT_OF_CATEGORY_CELL -> ProductViewHolder(
                ViewholderProductBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.CATEGORY_CELL -> CategoryViewHolder(
                ViewholderCategoryBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.PRODUCT_OF_SEARCH_CELL -> HomeViewHolder(
                ViewholderHomeBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.ORDER_CELL -> OrderListViewHolder(
                ViewholderOrderListBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.ORDER_PRODUCT_CELL -> OrderProductListViewHolder(
                ViewholderOrderProductListBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.REFUND_CELL -> RefundListViewHolder(
                ViewholderOrderListBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
        }
        return viewHolder as ModelViewHolder<M>
    }
}