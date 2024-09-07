package com.drabatx.tracksmp.presentation.view.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drabatx.tracksmp.data.domain.usecase.GetCurrentUserUseCase
import com.drabatx.tracksmp.data.domain.usecase.GetTrackListUseCase
import com.drabatx.tracksmp.data.domain.usecase.LogOutUseCase
import com.drabatx.tracksmp.data.model.response.Track
import com.drabatx.tracksmp.data.model.response.TracksResponse
import com.drabatx.tracksmp.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TrackViewModel(
    private val getTrackListUseCase: GetTrackListUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logoutUseCase: LogOutUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<Result<TracksResponse>>(Result.Loading)
    val uiState: StateFlow<Result<TracksResponse>> = _uiState.asStateFlow()

    val nameUser = MutableStateFlow("")

    val logoutState = MutableStateFlow(false)

    fun getCurrentUser() {
        viewModelScope.launch {
            try {
                getCurrentUserUseCase().collect { result ->
                    val user = (result as Result.Success).data
                    user.name?.let { nameUser.value = it }
                }
            } catch (e: Exception) {
                logoutUseCase()
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase().collect {
                logoutState.value = it
            }
        }
    }

    fun getTrackList() {
        viewModelScope.launch {
            try {
                getTrackListUseCase().collect { result ->
                    _uiState.value = result
                }
            } catch (e: Exception) {
                _uiState.value = Result.Error(e)
            }
        }
    }


    init {
        getTrackList()
    }

}