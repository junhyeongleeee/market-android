package com.example.shopping.presentation.home

import androidx.navigation.fragment.findNavController
import com.example.shopping.model.product.ProductModel
import com.example.shopping.presentation.adapter.model.ModelRecyclerAdapter
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.listener.AdapterListener
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.FragmentHomeBinding

class HomeFragment: BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel by viewModel<HomeViewModel>()

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    private val whatAdapter : ModelRecyclerAdapter<ProductModel, HomeViewModel> by lazy {
        ModelRecyclerAdapter(
            listOf(),
            viewModel,
            adapterListener = object : AdapterListener {
            }
        )
    }
    private val hotAdapter : ModelRecyclerAdapter<ProductModel, HomeViewModel> by lazy {
        ModelRecyclerAdapter(
            listOf(),
            viewModel,
            adapterListener = object : AdapterListener {
            }
        )
    }

    override fun observeData() {
        viewModel.homeStateLiveData.observe(this) {
            when (it) {
                is HomeState.UnInitialized -> {
                }
                is HomeState.Loading -> {
                }
                is HomeState.Success -> { handleSuccess(it)
                }
            }
        }

        viewModel.whatProductListLiveData.observe(this) {
            whatAdapter.submitList(it)
        }

        viewModel.hotProductListLiveData.observe(this){
            hotAdapter.submitList(it)
        }

    }

    private fun handleSuccess(state: HomeState.Success) {
        whatAdapter.submitList(state.whatList)
        hotAdapter.submitList(state.hotList)
    }

    override fun initViews() = with(binding){
        whatRecyclerView.adapter = whatAdapter
        hotRecyclerView.adapter = hotAdapter

        alarmCenterButton.setOnClickListener {
            findNavController().navigate(R.id.action_navHome_to_navAlarm)
        }
    }
}