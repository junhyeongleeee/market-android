package com.example.shopping.presentation.main

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.NavOptions
import androidx.viewpager.widget.ViewPager
import com.example.shopping.presentation.base.BaseActivity
import com.example.shopping.presentation.base.BaseViewPagerFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(),
    ViewPager.OnPageChangeListener,
    BottomNavigationView.OnNavigationItemReselectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener{

    override val viewModel by viewModel<MainViewModel>()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    // overall back stack of containers
    private val backStack = Stack<Int>()

    // list of base destination containers
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

    override fun observeData() = viewModel.mainStateLiveData.observe(this){
        when(it){
            is MainState.UnInitialized -> {
                initViewpager()
                initBottomNavigation()
                initBackStack()
            }
        }
    }

    private fun initViewpager() = with(binding){
        // setup main view pager
        mainViewPager.addOnPageChangeListener(this@MainActivity)
        mainViewPager.adapter = ViewPagerAdapter()
        mainViewPager.post(this@MainActivity::checkDeepLink)
        mainViewPager.offscreenPageLimit = fragments.size
    }

    private fun initBottomNavigation() = with(binding){
        // set bottom nav
        bottomNavigation.setOnNavigationItemSelectedListener(this@MainActivity)
        bottomNavigation.setOnNavigationItemReselectedListener(this@MainActivity)
    }

    private fun initBackStack() {
        // initialize backStack with elements
        if (backStack.empty()) backStack.push(0)
    }


    private fun setItem(position: Int) = with(binding){
        mainViewPager.currentItem = position
        backStack.push(position)
    }

    private fun checkDeepLink() {
        fragments.forEachIndexed { index, fragment ->
            val hasDeepLink = fragment.handleDeepLink(intent)
            if (hasDeepLink) setItem(index)
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(page: Int) = with(binding){
        val itemId = indexToPage[page] ?: R.id.home
        if (bottomNavigation.selectedItemId != itemId) bottomNavigation.selectedItemId = itemId
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onNavigationItemReselected(item: MenuItem) {
        val position = indexToPage.values.indexOf(item.itemId)
        val fragment = fragments[position]
        fragment.popToRoot()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean = with(binding){
        val position = indexToPage.values.indexOf(item.itemId)
        if (mainViewPager.currentItem != position) setItem(position)
        return true
    }

    override fun onBackPressed() = with(binding){
        val fragment = fragments[mainViewPager.currentItem]
        val hadNestedFragments = fragment.onBackPressed()
        // if no fragments were popped
        if (!hadNestedFragments) {
            if (backStack.size > 1) {
                // remove current position from stack
                backStack.pop()
                // set the next item in stack as current
                mainViewPager.currentItem = backStack.peek()

            } else super.onBackPressed()
        }
    }

    inner class ViewPagerAdapter : FragmentPagerAdapter(supportFragmentManager) {

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = fragments.size

    }
}
