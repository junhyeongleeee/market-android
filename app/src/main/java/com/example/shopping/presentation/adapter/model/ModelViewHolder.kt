package com.example.shopping.presentation.adapter.model

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import aop.fastcampus.part6.chapter01.model.Model
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.listener.AdapterListener
import com.example.shopping.util.provider.ResourcesProvider

abstract class ModelViewHolder<M: Model>(
    binding: ViewBinding,
    protected val viewModel: BaseViewModel,
    protected val resourcesProvider: ResourcesProvider
): RecyclerView.ViewHolder(binding.root) {
    open fun bindData(model: M) = Unit
    abstract fun bindViews(model: M, adapterListener: AdapterListener)
}