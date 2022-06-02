package com.example.shopping.presentation.my.orderList

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.shopping.model.product.ProductModel
import com.example.shopping.model.product.order.OrderItemModel
import com.example.shopping.model.product.order.OrderModel
import com.example.shopping.presentation.adapter.model.ModelRecyclerAdapter
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.category.productsByCategory.ProductsByCategoryViewModel
import com.example.shopping.presentation.detail.ProductDetailActivity
import com.example.shopping.presentation.listener.AdapterListener
import com.example.shopping.presentation.listener.ProductListListener
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.databinding.FragmentAlarmBinding
import kotlin.study.shopping.databinding.FragmentOrderListBinding

class OrderListFragment : BaseFragment<OrderListViewModel, FragmentOrderListBinding>() {

    override val viewModel by viewModel<OrderListViewModel>()

    override fun getViewBinding(): FragmentOrderListBinding =
        FragmentOrderListBinding.inflate(layoutInflater)

    private val adapter: ModelRecyclerAdapter<OrderModel, OrderListViewModel> by lazy {
        ModelRecyclerAdapter(
            listOf(),
            viewModel,
            adapterListener = object : AdapterListener {
            })
    }

    override fun observeData() = with(binding){

        viewModel.alarmStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is OrderListState.UnInitialized -> {

                }
                is OrderListState.Loading -> {

                }
            }
        }

        viewModel.orderItemListLiveData.observe(viewLifecycleOwner){
            if(it.isEmpty()){
                noExistDataLayout.isVisible = true
                existDataLayout.isGone = true
            }
            else{
                noExistDataLayout.isGone = true
                existDataLayout.isVisible = true
            }

            adapter.submitList(it)
        }
    }

    override fun initViews() = with(binding) {
        recyclerView.adapter = adapter

        back.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}