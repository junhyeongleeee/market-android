package com.example.shopping.presentation.category

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.shopping.presentation.adapter.CategoryAdapter
import com.example.shopping.presentation.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.databinding.FragmentCategoryBinding

class CategoryFragment: BaseFragment<CategoryViewModel, FragmentCategoryBinding>() {

    override val viewModel by viewModel<CategoryViewModel>()

    override fun getViewBinding(): FragmentCategoryBinding =
        FragmentCategoryBinding.inflate(layoutInflater)

    private val adapter = CategoryAdapter()

    override fun observeData() {
        viewModel.categoryStateLiveData.observe(this){
            when(it){
                CategoryState.UnInitialized -> {
                    initViews()
                }
            }
        }

        viewModel.categoryListLiveData.observe(this){
            adapter.submitList(it)
        }


    }

    private fun initViews() = with(binding){
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = adapter
    }
}