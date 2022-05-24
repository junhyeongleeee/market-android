package com.example.shopping.presentation.category

import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.shopping.model.category.CategoryModel
import com.example.shopping.presentation.adapter.category.CategoryAdapter
import com.example.shopping.presentation.adapter.model.ModelRecyclerAdapter
import com.example.shopping.presentation.adapter.model.ModelViewHolder
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.listener.AdapterListener
import com.example.shopping.presentation.listener.CategoryListListener
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.FragmentCategoryBinding

class CategoryFragment: BaseFragment<CategoryViewModel, FragmentCategoryBinding>() {

    override val viewModel by viewModel<CategoryViewModel>()

    override fun getViewBinding(): FragmentCategoryBinding =
        FragmentCategoryBinding.inflate(layoutInflater)

    private val adapter = ModelRecyclerAdapter<CategoryModel, CategoryViewModel>(
        listOf(),
        viewModel,
        adapterListener = object : AdapterListener{

        }
    )

    override fun observeData() {
        viewModel.categoryStateLiveData.observe(this){
            when(it){
                CategoryState.UnInitialized -> {
                }
            }
        }
        viewModel.categoryListLiveData.observe(this){
            adapter.submitList(it)
        }
    }

    override fun initViews() = with(binding){
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = adapter

        viewModel.settingList()
    }
}