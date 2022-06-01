package com.example.shopping.presentation.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.shopping.model.product.ProductModel
import com.example.shopping.model.type.TransitionModeType
import com.example.shopping.presentation.base.BaseActivity
import com.example.shopping.presentation.category.productsByCategory.ProductsByCategoryFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.experimental.builder.getArguments
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.ActivityProductDetailBinding

class ProductDetailActivity : BaseActivity<ProductDetailActivityViewModel, ActivityProductDetailBinding>(
    TransitionModeType.HORIZON
){
    override val viewModel by viewModel<ProductDetailActivityViewModel>()

    override fun getViewBinding(): ActivityProductDetailBinding =
        ActivityProductDetailBinding.inflate(layoutInflater)

    override fun observeData() {

    }

    override fun initViews() {

        val productModel : ProductModel? = intent.getParcelableExtra(ProductsByCategoryFragment.PRODUCT_KEY) ?: null
        val bundle = Bundle()

        productModel?.let {
            bundle.putParcelable(PRODUCT_MODEL_KEY, it)

            findNavController(R.id.navProductDetail).setGraph(R.navigation.nav_product_detail, bundle)
        }
    }

    companion object {

        const val PRODUCT_MODEL_KEY = "product"

        fun newIntent(context: Context, productModel: ProductModel) =
            Intent(context, ProductDetailActivity::class.java).apply {
                putExtra(ProductsByCategoryFragment.PRODUCT_KEY, productModel)
            }
    }
}