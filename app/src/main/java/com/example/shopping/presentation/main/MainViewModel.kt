package com.example.shopping.presentation.main

import androidx.compose.runtime.collection.mutableVectorOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.base.BaseViewPagerFragment
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel: BaseViewModel() {

    private var _mainStateLiveData = MutableLiveData<MainState>(MainState.UnInitialized)
    val mainStateLiveData: LiveData<MainState> = _mainStateLiveData

    private val backStack = Stack<Int>()

    override fun fetch(): Job = viewModelScope.launch {
        if (backStack.empty()) backStack.push(0)
    }

    fun menuSelected(position: Int){
        if(backStack.empty()){
            _mainStateLiveData.value = MainState.SelectedFailure
        }
        else{
            if (backStack.size > 1) {
                if (backStack.contains(position)) {
                    do {
                        val state = backStack.peek()
                        backStack.pop()
                    } while (position != state)
                }
            }
            _mainStateLiveData.value = MainState.SelectedSuccess(position)
        }
    }
    fun backPressed(){
        if (backStack.size > 1) {
            // remove current position from stack
            backStack.pop()
            // set the next item in stack as current
            _mainStateLiveData.value = MainState.BackPressedSuccess(backStack.peek())
        }
        else _mainStateLiveData.value = MainState.BackPressedFailure
    }
    fun push(position: Int) = backStack.push(position)
}