package com.example.shopping.presentation.category

import androidx.navigation.fragment.findNavController
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.FragmentCategoryBinding
import kotlin.study.shopping.databinding.FragmentHomeBinding

class CategoryFragment: BaseFragment<CategoryViewModel, FragmentCategoryBinding>() {

    override val viewModel by viewModel<CategoryViewModel>()

    override fun getViewBinding(): FragmentCategoryBinding =
        FragmentCategoryBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.mainStateLiveData.observe(this){
        when(it){
            CategoryState.UnInitialized -> {
                initViews()
            }
        }
    }

    private fun initViews() = with(binding){
        nextButton.setOnClickListener {
            findNavController().navigate(R.id.action_navCategory_to_navCategoryTest1)
        }
    }
}