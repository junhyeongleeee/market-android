package com.example.shopping.presentation.viewHolder.category

import com.example.shopping.extensions.load
import com.example.shopping.model.recyclerView.category.CategoryModel
import com.example.shopping.presentation.adapter.model.ModelViewHolder
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.listener.AdapterListener
import com.example.shopping.presentation.listener.CategoryListListener
import com.example.shopping.util.provider.ResourcesProvider
import kotlin.study.shopping.databinding.ViewholderCategoryBinding

class CategoryViewHolder(
    private val binding: ViewholderCategoryBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
): ModelViewHolder<CategoryModel>(binding, viewModel, resourcesProvider) {

    override fun bindViews(model: CategoryModel, adapterListener: AdapterListener){
        if( adapterListener is CategoryListListener){
            binding.root.setOnClickListener {
                adapterListener.onClickItem(model)
            }
        }
    }

    override fun bindData(model: CategoryModel) = with(binding){
        // TODO CategoryModel.img 업데이트 예정
        img.load(model.image_url?: "", corner = 0f)
        name.text = model.name
    }
}