package com.example.shopping.presentation.listener

import com.example.shopping.model.recyclerView.category.CategoryModel

interface CategoryListListener: AdapterListener{
    fun onClickItem(model: CategoryModel)
}