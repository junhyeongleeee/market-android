package com.example.shopping.presentation.category.productsByCategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.FragmentCategoryBinding
import kotlin.study.shopping.databinding.FragmentCategoryTest1Binding
import kotlin.study.shopping.databinding.FragmentHomeBinding

class ProductsByCategoryFragment: Fragment() {


    private lateinit var binding: FragmentCategoryTest1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryTest1Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextButton.setOnClickListener {

            findNavController().navigate(R.id.action_navCategoryTest1_to_navCategoryTest2)
        }
    }
}