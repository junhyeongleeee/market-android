package com.example.shopping.presentation.category.productsByCategory

import androidx.navigation.fragment.findNavController
import com.example.shopping.model.recyclerView.product.ProductModel
import com.example.shopping.presentation.adapter.model.ModelRecyclerAdapter
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.category.CategoryFragment
import com.example.shopping.presentation.detail.ProductDetailActivity
import com.example.shopping.presentation.listener.ProductListListener
import com.example.shopping.util.provider.ResourcesProvider
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
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

    private val resourcesProvider by inject<ResourcesProvider>()

    private val adapter: ModelRecyclerAdapter<ProductModel, ProductsByCategoryViewModel> by lazy {
        ModelRecyclerAdapter(
            listOf(),
            viewModel,
            resourcesProvider,
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