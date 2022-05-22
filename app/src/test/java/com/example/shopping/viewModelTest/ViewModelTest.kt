package com.example.shopping.viewModelTest

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.example.shopping.di.module.ViewModelModule
import com.example.shopping.di.module.appTestModule
import com.example.shopping.liveData.LiveDataTestObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule


@ExperimentalCoroutinesApi
internal abstract class ViewModelTest : KoinTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    /**
     * InstantTaskExecutorRule()
     * 1. 안드로이드 구성요소 관련 작업들을 모두 한 스레드에서 실행되게 한다.
     * 2. 모든 작업이 synchronous 하게 동작하여 테스팅을 원할하게 할 수 있다.
     * 3. LiveData 이용할 경우 필수적으로 사용해야 한다.
     */
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var context: Application

    /**
     * TestCoroutineDispatcher()
     * 1. Dispatchers.Main() 은 안드로이드에서 Main thread 의 looper 를 이용한다.
     * 2. 따라서 Unit test 에서 coroutine unit 테스트를 사용하기 위해서는 Dispatchers.Main 를
     *      TestCoroutineDispatcher() 로 변경 해야 함.
     * 3. Dispatchers.setMain(dispatcher: coroutineDispatcher)
     * 4. Dispatchers.resetmain() -> Unit test 를 isolation 시키고 side effect 없도록 실행하기 위함.
     *      clean UP 시점에 호출.
     *
     *
     */
    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        startKoin{
            androidContext(context)
            modules(appTestModule)
        }
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain() // MainDispatcher 를 초기화 해주어야 메모리 누수가 발생하지 않는다.
        dispatcher.cleanupTestCoroutines()
    }

    protected fun <T> LiveData<T>.test(): LiveDataTestObserver<T> {
        val testObserver = LiveDataTestObserver<T>()
        observeForever(testObserver)
        return testObserver
    }
}