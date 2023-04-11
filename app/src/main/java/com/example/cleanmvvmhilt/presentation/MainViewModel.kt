package com.example.cleanmvvmhilt.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanmvvmhilt.domain.GetUsefulActivityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsefulActivityUseCase: GetUsefulActivityUseCase
):ViewModel() {

    private val _activity = MutableStateFlow("Push Refresh Button to load Activity!")
    val activity = _activity.asStateFlow()

    private val _activityType = MutableStateFlow("")
    val activityType = _activityType.asStateFlow()

    fun ovRefreshButtonClick(){
        _activity.value = "Loading..."
        viewModelScope.launch {
            val activityBody = getUsefulActivityUseCase.execute()
            _activity.value = activityBody.activity
            _activityType.value = activityBody.type
        }
    }
}