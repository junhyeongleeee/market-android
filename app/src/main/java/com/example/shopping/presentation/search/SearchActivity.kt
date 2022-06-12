package com.example.shopping.presentation.search

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shopping.presentation.base.BaseActivity
import com.example.shopping.presentation.my.auth.AuthActivity
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.ActivitySearchBinding

class SearchActivity : BaseActivity<SearchViewModel, ActivitySearchBinding>() {

    override val viewModel by viewModel<SearchViewModel>()

    override fun getViewBinding(): ActivitySearchBinding =
        ActivitySearchBinding.inflate(layoutInflater)

    override fun observeData() {
    }

    companion object {
        fun newIntent(context: Context) =
            Intent(context, SearchActivity::class.java)
    }
}