package com.example.shopping.presentation.adapter.model

import android.view.LayoutInflater
import android.view.ViewGroup
import aop.fastcampus.part6.chapter01.model.CellType
import aop.fastcampus.part6.chapter01.model.Model
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.viewHolder.category.CategoryViewHolder
import com.example.shopping.presentation.viewHolder.category.productsByCategory.ProductViewHolder
import com.example.shopping.presentation.viewHolder.home.HomeViewHolder
import kotlin.study.shopping.databinding.ViewholderCategoryBinding
import kotlin.study.shopping.databinding.ViewholderHomeBinding
import kotlin.study.shopping.databinding.ViewholderProductBinding

object ModelViewHolderMapper {
    fun<M: Model> map(
        parent: ViewGroup,
        type: CellType,
        viewModel: BaseViewModel
    ): ModelViewHolder<M>{
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = when (type) {
            CellType.PRODUCT_OF_CATEGORY_CELL -> ProductViewHolder(
                ViewholderProductBinding.inflate(inflater, parent, false),
                viewModel
            )
            CellType.CATEGORY_CELL -> CategoryViewHolder(
                ViewholderCategoryBinding.inflate(inflater, parent, false),
                viewModel
            )
            CellType.PRODUCT_OF_SEARCH_CELL -> HomeViewHolder(
                ViewholderHomeBinding.inflate(inflater, parent, false),
                viewModel
            )
        }
        return viewHolder as ModelViewHolder<M>
    }
}