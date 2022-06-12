package com.example.shopping.presentation.search.navigation.products

import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.shopping.model.recyclerView.product.ProductModel
import com.example.shopping.presentation.adapter.model.ModelRecyclerAdapter
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.detail.ProductDetailActivity
import com.example.shopping.presentation.listener.ProductListListener
import com.example.shopping.util.provider.ResourcesProvider
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.databinding.FragmentSearchBinding

class SearchResultFragment: BaseFragment<SearchResultViewModel, FragmentSearchBinding>() {

    override val viewModel by viewModel<SearchResultViewModel>()

    override fun getViewBinding(): FragmentSearchBinding =
        FragmentSearchBinding.inflate(layoutInflater)

    private val resourcesProvider by inject<ResourcesProvider>()

    private val adapter: ModelRecyclerAdapter<ProductModel, SearchResultViewModel> by lazy {
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
        viewModel.searchItemListLiveData.observe(this){
            adapter.submitList(it)
        }

        viewModel.searchResultStateLiveData.observe(this){
            when(it){
                is SearchResultState.UnInitialized -> {}
                is SearchResultState.Loading -> { handleLoading()}
                is SearchResultState.Success -> { handleSuccess()}
                is SearchResultState.Failure -> { handleFailure()}
            }
        }
    }

    private fun handleFailure() {
        binding.progressBar.isGone = true
        // TODO Dialog 띄우기
    }

    private fun handleSuccess() {
        binding.progressBar.isGone = true
    }

    private fun handleLoading() {
        binding.progressBar.isVisible = true
    }

    override fun initViews() = with(binding){
        recyclerView.adapter = adapter

        searchEditText.requestFocus()
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)

        searchEditText.setOnKeyListener { _, keyCode, keyEvent ->
            if(keyCode == KeyEvent.KEYCODE_ENTER
            ){
                viewModel.getSearchProducts(searchEditText.text.toString().trim())
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
            }

            false
        }
        searchButton.setOnClickListener {
            viewModel.getSearchProducts(searchEditText.text.toString().trim())
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        }

        back.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}