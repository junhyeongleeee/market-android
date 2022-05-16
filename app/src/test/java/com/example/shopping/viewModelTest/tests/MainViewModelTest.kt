package com.example.shopping.viewModelTest.tests

import com.example.shopping.presentation.main.MainState
import com.example.shopping.presentation.main.MainViewModel
import com.example.shopping.viewModelTest.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.koin.test.inject


@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
internal class MainViewModelTest: ViewModelTest() {

    private val mainViewModel by inject<MainViewModel>()

    @Test
    fun `test viewModel fetch`() = runBlockingTest {
        val testObservable = mainViewModel.mainStateLiveData.test()

        mainViewModel.fetch()

        testObservable.assertValueSequence(
            listOf(
                MainState.UnInitialized,
                MainState.Loading
            )
        )
    }
}