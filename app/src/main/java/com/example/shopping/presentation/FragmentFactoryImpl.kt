package com.example.shopping.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.shopping.presentation.category.productsByCategory.ProductsByCategoryFragment

class FragmentFactoryImpl: FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            ProductsByCategoryFragment::class.java.name -> {
                ProductsByCategoryFragment()
            }
            else -> super.instantiate(classLoader, className)
        }
    }
}