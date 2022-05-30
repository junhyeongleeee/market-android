package com.example.shopping.presentation.viewHolder.category.productsByCategory

import com.example.shopping.extensions.load
import com.example.shopping.model.product.ProductModel
import com.example.shopping.presentation.adapter.model.ModelViewHolder
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.listener.AdapterListener
import com.example.shopping.presentation.listener.ProductListListener
import kotlin.study.shopping.databinding.ViewholderProductBinding

class ProductViewHolder(
    private val binding: ViewholderProductBinding,
    val viewModel: BaseViewModel
): ModelViewHolder<ProductModel>(binding) {

    override fun bindViews(model: ProductModel, adapterListener: AdapterListener){
        if( adapterListener is ProductListListener){
            binding.root.setOnClickListener {
                adapterListener.onClickItem(model)
            }
        }
    }

    override fun bindData(model: ProductModel) = with(binding){
        name.text = model.name
        price.text = model.price.toString()
        img.load(model.image_url ?: "", corner = 0f)
    }
}