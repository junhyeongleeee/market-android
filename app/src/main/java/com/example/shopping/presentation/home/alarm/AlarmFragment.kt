package com.example.shopping.presentation.home.alarm

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.shopping.presentation.base.BaseFragment
import com.example.shopping.presentation.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.databinding.FragmentAlarmBinding

class AlarmFragment: BaseFragment<AlarmViewModel, FragmentAlarmBinding>() {

    override val viewModel by viewModel<AlarmViewModel>()

    override fun getViewBinding(): FragmentAlarmBinding =
        FragmentAlarmBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.alarmStateLiveData.observe(this){
        when(it){
            is AlarmState.UnInitialized -> {

            }
            is AlarmState.Loading -> {

            }
            is AlarmState.AlarmExistence -> {
                alarmExistenceHandler(it)
            }
            is AlarmState.Success -> {
                alarmSuccessHandler()
            }
        }
    }

    private fun alarmExistenceHandler(state: AlarmState.AlarmExistence) = with(binding){
        when(state){
            is AlarmState.AlarmExistence.Success -> {
                alarmExistence()
            }
            is AlarmState.AlarmExistence.Failure -> {
                alarmNotExistence()
            }
        }
    }

    private fun alarmNotExistence() = with(binding){
        noAlarmFrameLayout.isVisible = true
    }

    private fun alarmExistence() = with(binding){
        noAlarmFrameLayout.isGone = true
    }

    private fun alarmSuccessHandler() = with(binding){

    }

    override fun initViews() = with(binding){

        appBar.categoryTextView.text = "알림센터"
        appBar.back.setOnClickListener {
            findNavController().popBackStack()
        }

        goToHomeButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}