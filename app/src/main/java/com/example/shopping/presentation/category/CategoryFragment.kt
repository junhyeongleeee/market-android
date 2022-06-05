package com.example.shopping.presentation.category

import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.shopping.model.recyclerView.category.CategoryModel
import com.example.shopping.presentation.adapter.model.ModelRecyclerAdapter
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.listener.CategoryListListener
import com.example.shopping.util.provider.ResourcesProvider
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.FragmentCategoryBinding

class CategoryFragment: BaseFragment<CategoryViewModel, FragmentCategoryBinding>() {

    override val viewModel by viewModel<CategoryViewModel>()

    override fun getViewBinding(): FragmentCategoryBinding =
        FragmentCategoryBinding.inflate(layoutInflater)

    private val resourcesProvider by inject<ResourcesProvider>()

    private val adapter : ModelRecyclerAdapter<CategoryModel, CategoryViewModel> by lazy {
        ModelRecyclerAdapter(
            listOf(),
            viewModel,
            resourcesProvider,
            adapterListener = object : CategoryListListener{
                override fun onClickItem(model: CategoryModel) {
                    findNavController().navigate(
                        R.id.action_navCategory_to_navTestProductsByCategory2,
                        bundleOf(
                            CATEGORY_ID_KEY to model.category_id,
                            CATEGORY_NAME_KEY to model.name
                        )
                    )
                }
            })
    }

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
    }

    companion object{
        const val CATEGORY_ID_KEY = "category_id"
        const val CATEGORY_NAME_KEY = "category_name"
    }
}