package com.example.shopping.presentation.my.userDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.data.entity.user.UserDetailEntity
import com.example.shopping.data.local.AppPreferenceManager
import com.example.shopping.domain.repository.user.UserRepositoryImpl
import com.example.shopping.model.type.UserType
import com.example.shopping.presentation.base.BaseViewModel
import com.example.shopping.presentation.my.MyState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserDetailViewModel(
    private val userRepositoryImpl: UserRepositoryImpl,
    private val preference: AppPreferenceManager
) : BaseViewModel(){

    private var _userDetailStateLiveData = MutableLiveData<UserDetailState>(UserDetailState.UnInitialized)
    val userDetailStateLiveData: LiveData<UserDetailState> = _userDetailStateLiveData

    /*private var userDetailEntity : UserDetailEntity? = UserDetailEntity(
        uid = "1",
        username = "이준형",
        email = "djskal3745@gmail.com",
        phone = "01036153247",
        role = UserType.Customer.type
    )*/

    override fun fetch(): Job = viewModelScope.launch {
        _userDetailStateLiveData.postValue(UserDetailState.Loading)
        getUserData()
    }

    private fun getUserData() = viewModelScope.launch{
        // Mock

        /*userDetailEntity?.let {
            _userDetailStateLiveData.postValue(UserDetailState.Success(it))
        } ?: kotlin.run { _userDetailStateLiveData.postValue(UserDetailState.Failure) }*/

        preference.getString(AppPreferenceManager.ACCESS_TOKEN)?.let{
            userRepositoryImpl.getUserDetail(it)?.let { response ->
                _userDetailStateLiveData.postValue(UserDetailState.Success(
                    response.toEntity()
                ))
            } ?: _userDetailStateLiveData.postValue(UserDetailState.Failure)
        } ?: _userDetailStateLiveData.postValue(UserDetailState.Failure)
    }

    fun signOut() = viewModelScope.launch {
//        userDetailEntity = null
//        fetch()

        preference.getString(AppPreferenceManager.ACCESS_TOKEN)?.let {
            userRepositoryImpl.signOutUser(it)?.let {
                preference.setString("access_token", null)

                fetch()
            } ?: _userDetailStateLiveData.postValue(UserDetailState.Failure)
        } ?:  _userDetailStateLiveData.postValue(UserDetailState.Failure)
    }
}