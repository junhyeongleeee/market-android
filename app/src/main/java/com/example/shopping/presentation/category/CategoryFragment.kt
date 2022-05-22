package com.example.shopping.presentation.category

import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.shopping.model.category.CategoryModel
import com.example.shopping.presentation.adapter.category.CategoryAdapter
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.listener.CategoryListListener
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.FragmentCategoryBinding

class CategoryFragment: BaseFragment<CategoryViewModel, FragmentCategoryBinding>() {

    override val viewModel by viewModel<CategoryViewModel>()

    override fun getViewBinding(): FragmentCategoryBinding =
        FragmentCategoryBinding.inflate(layoutInflater)

    private val adapter = CategoryAdapter(listener = object : CategoryListListener{
        override fun onClickItem(model: CategoryModel) {
            findNavController().navigate(R.id.action_navCategory_to_navCategoryTest1)
        }
    })

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