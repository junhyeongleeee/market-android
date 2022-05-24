package com.example.shopping.presentation.adapter.model

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import aop.fastcampus.part6.chapter01.model.Model
import com.example.shopping.presentation.listener.AdapterListener

abstract class ModelViewHolder<M: Model>(
    binding: ViewBinding
): RecyclerView.ViewHolder(binding.root) {
    open fun bindData(model: M) = Unit
    abstract fun bindViews(model: M, adapterListener: AdapterListener)
}