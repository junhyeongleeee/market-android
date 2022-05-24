package com.example.shopping.presentation.viewHolder.category

import com.example.shopping.extensions.load
import com.example.shopping.model.category.CategoryModel
import com.example.shopping.model.product.ProductModel
import com.example.shopping.presentation.adapter.model.ModelViewHolder
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.listener.AdapterListener
import com.example.shopping.presentation.listener.ProductListListener
import kotlin.study.shopping.databinding.ViewholderCategoryBinding
import kotlin.study.shopping.databinding.ViewholderProductBinding

class CategoryViewHolder(
    private val binding: ViewholderCategoryBinding,
    val viewModel: BaseViewModel
): ModelViewHolder<CategoryModel>(binding) {

    override fun bindViews(model: CategoryModel, adapterListener: AdapterListener){
        if( adapterListener is ProductListListener){
            binding.root.setOnClickListener {
            }
        }
    }

    override fun bindData(model: CategoryModel) = with(binding){
        // TODO CategoryModel.img 업데이트 예정
        img.load(model.image_url, 0f)
    }
}