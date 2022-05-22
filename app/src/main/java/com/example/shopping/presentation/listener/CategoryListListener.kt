package com.example.shopping.presentation.listener

import com.example.shopping.model.category.CategoryModel

interface CategoryListListener{
    fun onClickItem(model: CategoryModel)
}