package com.example.shopping.presentation.home

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopping.model.product.ProductModel
import com.example.shopping.presentation.adapter.model.ModelRecyclerAdapter
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.base.BaseNavFragment
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.category.productsByCategory.ProductsByCategoryState
import com.example.shopping.presentation.category.productsByCategory.ProductsByCategoryViewModel
import com.example.shopping.presentation.listener.AdapterListener
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.FragmentHomeBinding

class HomeFragment: BaseNavFragment<FragmentHomeBinding>() {

    override val viewModel = HomeViewModel()

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    private val adapter = ModelRecyclerAdapter<ProductModel, HomeViewModel>(
        listOf(),
        viewModel,
        adapterListener = object : AdapterListener {
        }
    )

    override fun observeData() {
        viewModel.homeStateLiveData.observe(this) {
            when (it) {
                is HomeState.UnInitialized -> {
                }
                is HomeState.Loading -> {
                }
                is HomeState.Success -> {
                }
            }
        }

        viewModel.productListLiveData.observe(this) {
            adapter.submitList(it)
        }
    }

    override fun initViews() = with(binding){
        recyclerView.layoutManager = GridLayoutManager(requireActivity(), 3)
        recyclerView.adapter = adapter
        viewModel.settingList()

        alarmCenterButton.setOnClickListener {
            findNavController().navigate(R.id.action_navHome_to_navAlarm)
        }
    }
}