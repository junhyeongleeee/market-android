package com.example.shopping.presentation.category.productsByCategory

import android.graphics.ColorSpace
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.shopping.model.product.ProductModel
import com.example.shopping.presentation.adapter.model.ModelRecyclerAdapter
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.base.BaseNavFragment
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.category.CategoryFragment
import com.example.shopping.presentation.detail.ProductDetailActivity
import com.example.shopping.presentation.listener.AdapterListener
import com.example.shopping.presentation.listener.ProductListListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.FragmentCategoryBinding
import kotlin.study.shopping.databinding.FragmentCategoryTest1Binding
import kotlin.study.shopping.databinding.FragmentHomeBinding
import kotlin.study.shopping.databinding.FragmentProductsByCategoryBinding

class ProductsByCategoryFragment :
    BaseFragment<ProductsByCategoryViewModel, FragmentProductsByCategoryBinding>() {

    override val viewModel by viewModel<ProductsByCategoryViewModel> {
        parametersOf(
            arguments?.getString(CategoryFragment.CATEGORY_ID_KEY) ?: ""
        )
    }

    override fun getViewBinding(): FragmentProductsByCategoryBinding =
        FragmentProductsByCategoryBinding.inflate(layoutInflater)

    private val adapter: ModelRecyclerAdapter<ProductModel, ProductsByCategoryViewModel> by lazy {
        ModelRecyclerAdapter(
            listOf(),
            viewModel,
            adapterListener = object : ProductListListener {
                override fun onClickItem(model: ProductModel) {
                    startActivity(
                        ProductDetailActivity.newIntent(
                            requireContext(),
                            model
                        )
                    )
                }
            })
    }

    override fun observeData() {
        viewModel.pbcStateLiveData.observe(this) {
            when (it) {
                is ProductsByCategoryState.UnInitialized -> {
                }
                is ProductsByCategoryState.Loading -> {
                }
                is ProductsByCategoryState.Success -> {
                }
            }
        }

        viewModel.productListLiveData.observe(this) {
            adapter.submitList(it)
        }
    }

    override fun initViews() = with(binding) {
        recyclerView.adapter = adapter
//        viewModel.settingList()
        appBar.titleTextView.text = arguments?.getString(CategoryFragment.CATEGORY_NAME_KEY) ?: ""

        appBar.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    companion object{
        const val PRODUCT_KEY = "product"
    }
}