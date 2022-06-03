package com.example.shopping.presentation.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.shopping.extensions.snackbar
import com.example.shopping.extensions.toast
import com.example.shopping.presentation.RemoteState
import kotlinx.coroutines.Job
import retrofit2.HttpException

abstract class BaseFragment<VM: BaseViewModel, VB: ViewBinding>: Fragment() {

    abstract val viewModel: VM

    protected lateinit var binding: VB

    abstract fun getViewBinding(): VB

    private lateinit var fetch: Job

    protected val imm: InputMethodManager by lazy {
        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetch = viewModel.fetch()
        observeData()
        initViews()
        fetchStateObserving()
    }

    abstract fun observeData()

    open fun fetchStateObserving() = viewModel.fetchState.observe(this) {
        var message = ""
        when (it.second) {
            RemoteState.BAD_INTERNET -> {
                message = "소켓 오류 / 서버와 연결에 실패하였습니다."
            }
            RemoteState.PARSE_ERROR -> {
                val error = (it.first as HttpException)
                message = "${error.code()} ERROR : \n ${
                    error.response()!!.errorBody()!!.string().split("\"")[7]
                }"
            }
            RemoteState.WRONG_CONNECTION -> {
                message = "호스트를 확인할 수 없습니다. 네트워크 연결을 확인해주세요"
            }
            else -> {
                message = "통신에 실패하였습니다.\n ${it.first.message}"
            }

        }

        Log.d("********NETWORK_ERROR_MESSAGE : ", it.first.message.toString())
        toast(requireActivity(), message)
    }

    open fun initViews() = Unit

    override fun onDestroyView() {
        super.onDestroyView()

        if(fetch.isActive){
            fetch.cancel()
        }
    }
}