package com.example.shopping.presentation.main

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.shopping.presentation.FragmentFactoryImpl
import com.example.shopping.presentation.base.BaseActivity
import com.example.shopping.presentation.base.BaseViewPagerFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(),
    ViewPager.OnPageChangeListener,
    BottomNavigationView.OnNavigationItemReselectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    override val viewModel by viewModel<MainViewModel>()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    // backPressCheck
    private var backPressedChecked = false
    private var backKeyPressTime : Long = 0

    private val fragments = listOf(
        BaseViewPagerFragment.newInstance(R.layout.content_category_base, R.id.navHostCategory),
        BaseViewPagerFragment.newInstance(R.layout.content_search_base, R.id.navHostSearch),
        BaseViewPagerFragment.newInstance(R.layout.content_home_base, R.id.navHostHome),
        BaseViewPagerFragment.newInstance(R.layout.content_my_base, R.id.navHostMy))

    // map of navigation_id to container index
    private val indexToPage = mapOf(
        0 to R.id.category,
        1 to R.id.search,
        2 to R.id.home,
        3 to R.id.my
    )

    override fun observeData() = viewModel.mainStateLiveData.observe(this) {
        when (it) {
            is MainState.UnInitialized -> {
                initViewpager()
                initBottomNavigation()
            }
            is MainState.Loading -> {}
            is MainState.SelectedSuccess -> {
                handleSelectedSuccess(it)
            }
            is MainState.SelectedFailure -> {
                // TODO : Error 처리
            }
            is MainState.BackPressedSuccess -> {
                handleBackPressedSuccess(it)
            }
            is MainState.BackPressedFailure -> {
                handleBackPressedFailure()
            }
        }
    }

    private fun handleBackPressedFailure() = with(binding){
        if (System.currentTimeMillis() > backKeyPressTime + 2000) {
            backKeyPressTime = System.currentTimeMillis()
            Snackbar.make(root, "\'뒤로\' 버튼을 한번 더 누르면 종료합니다.", Snackbar.LENGTH_LONG).show()
            return
        }

        if (System.currentTimeMillis() <= backKeyPressTime + 2000) {
            finish()
        }
    }

    private fun handleBackPressedSuccess(state: MainState.BackPressedSuccess) = with(binding){
        backPressedChecked = true
        // set the next item in stack as current
        mainViewPager.currentItem = state.item
    }

    private fun handleSelectedSuccess(state: MainState.SelectedSuccess) = with(binding){
        if (mainViewPager.currentItem != state.position) setItem(state.position)
    }

    private fun initViewpager() = with(binding) {
        // setup main view pager
        mainViewPager.addOnPageChangeListener(this@MainActivity)
        mainViewPager.adapter = ViewPagerAdapter()
        mainViewPager.post(this@MainActivity::checkDeepLink)
        mainViewPager.offscreenPageLimit = fragments.size
        mainViewPager.currentItem = 2
    }

    private fun initBottomNavigation() = with(binding) {
        // set bottom nav
        bottomNavigation.setOnNavigationItemSelectedListener(this@MainActivity)
        bottomNavigation.setOnNavigationItemReselectedListener(this@MainActivity)
    }

    private fun setItem(position: Int) = with(binding) {
        mainViewPager.currentItem = position
        viewModel.push(position)
    }

    private fun checkDeepLink() {
        fragments.forEachIndexed { index, fragment ->
            val hasDeepLink = fragment.handleDeepLink(intent)
            if (hasDeepLink) setItem(index)
        }
    }

    override fun onPageSelected(page: Int) = with(binding) {

        val itemId = indexToPage[page] ?: R.id.home
        if (bottomNavigation.selectedItemId != itemId) bottomNavigation.selectedItemId = itemId
    }

    override fun onNavigationItemReselected(item: MenuItem) {
        val position = indexToPage.values.indexOf(item.itemId)
        val fragment = fragments[position]
        fragment.popToRoot()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (!backPressedChecked) {

            val position = indexToPage.values.indexOf(item.itemId)
            viewModel.menuSelected(position)

        } else {
            backPressedChecked = false
        }
        return true
    }

    override fun onBackPressed() = with(binding) {
        val fragment = fragments[mainViewPager.currentItem]
        val hadNestedFragments = fragment.onBackPressed()

        // if no fragments were popped
        if (!hadNestedFragments) {
            viewModel.backPressed()
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
    override fun onPageScrollStateChanged(state: Int) {}

    inner class ViewPagerAdapter : FragmentPagerAdapter(supportFragmentManager) {
        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = fragments.size
    }
}
