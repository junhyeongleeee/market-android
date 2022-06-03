package com.example.shopping.presentation.my.orderList

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.shopping.extensions.toast
import com.example.shopping.model.product.ProductModel
import com.example.shopping.model.product.order.OrderItemModel
import com.example.shopping.model.product.order.OrderModel
import com.example.shopping.presentation.adapter.model.ModelRecyclerAdapter
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.category.productsByCategory.ProductsByCategoryViewModel
import com.example.shopping.presentation.detail.ProductDetailActivity
import com.example.shopping.presentation.listener.AdapterListener
import com.example.shopping.presentation.listener.OrderListListener
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
            adapterListener = object : OrderListListener {
                override fun onClickItem(model: OrderModel) {
                }
            })
    }

    override fun observeData() = with(binding){

        viewModel.orderListStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is OrderListState.UnInitialized -> {

                }
                is OrderListState.Loading -> {
                    handleLoading()
                }
                is OrderListState.Success -> {
                    handleSuccess()
                }
                is OrderListState.Failure -> {
                    handleFailure()
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

    private fun handleFailure() = with(binding){
        toast(requireActivity(), "주문 내역 가져오기 실패!!")
        noExistDataLayout.isVisible = true
        existDataLayout.isGone = true
        progressBar.isGone = true
    }

    private fun handleSuccess() = with(binding){
        progressBar.isGone = true
    }

    private fun handleLoading() = with(binding){
        progressBar.isVisible = true
    }

    override fun initViews() = with(binding) {
        recyclerView.adapter = adapter

        back.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}