package com.example.shopping.presentation.my

import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.shopping.extensions.load
import com.example.shopping.extensions.snackbar
import com.example.shopping.presentation.RemoteState
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.my.auth.AuthActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import retrofit2.HttpException
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.FragmentMyBinding

class MyFragment : BaseFragment<MyViewModel, FragmentMyBinding>() {

    override val viewModel by viewModel<MyViewModel>()

    override fun getViewBinding(): FragmentMyBinding =
        FragmentMyBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.fetchState.observe(this) {
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
            snackbar(binding.root, message)
        }

        viewModel.myStateLiveData.observe(this) {
            when (it) {
                is MyState.UnInitialized -> {
                }
                is MyState.Loading -> {
                    handleLoading()
                }
                is MyState.Success -> {
                    handleSuccess(it)
                }
                is MyState.Failure -> {
                    handleFailure()
                }
            }
        }
    }

    private fun handleLoading() {
        binding.progressBar.isVisible = true
    }

    private fun handleFailure() = with(binding) {
        // name
        nameTextView.text = "로그인"
        nameTextView.isClickable = true
        nameTextView.setOnClickListener {
            // TODO : 로그인 페이지로 이동
            startActivity(AuthActivity.newIntent(requireContext()))
        }
        // profile
        profileImg.load("", R.drawable.ic_my, corner = 60f)
        profileImg.setOnClickListener {
            // TODO : 로그인 페이지로 이동
            startActivity(AuthActivity.newIntent(requireContext()))
        }
        // money
        payMoney.text = "-"

        // User Detail Data Layout
        payCache.text = "-"
        purchaseAfter.text = "-"
        likeProduct.text = "-"
        recentlyProduct.text = "-"
        frequentlyProduct.text = "-"

        binding.progressBar.isGone = true
    }

    private fun handleSuccess(state: MyState.Success) = with(binding) {

        // name
        nameTextView.text = state.userDetailEntity.userName
        nameTextView.setOnClickListener {
            // TODO : 내 정보관리 페이지로 이동
            findNavController().navigate(R.id.action_navMy_to_navUserDetail,
                bundleOf(
                    "UserDetailEntity" to state.userDetailEntity
                )
            )
        }
        // profile
        profileImg.load("", R.drawable.ic_my, corner = 60f)
        // money
        payMoney.text = "0"

        // User Detail Data Layout
        payCache.text = "0"
        purchaseAfter.text = "0"
        likeProduct.text = "0"
        recentlyProduct.text = "0"
        frequentlyProduct.text = "0"

        binding.progressBar.isGone = true
    }

    override fun onResume() {
        super.onResume()

        viewModel.fetch()
    }
}