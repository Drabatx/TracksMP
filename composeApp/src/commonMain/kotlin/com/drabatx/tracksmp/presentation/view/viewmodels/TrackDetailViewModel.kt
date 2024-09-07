package com.drabatx.tracksmp.presentation.view.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drabatx.tracksmp.data.domain.usecase.GetTrackByIdUseCase
import com.drabatx.tracksmp.data.model.response.Track
import com.drabatx.tracksmp.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TrackDetailViewModel(private val getTrackByIdUseCase: GetTrackByIdUseCase):ViewModel() {
    val trackState = MutableStateFlow<Result<Track>>(Result.Loading)
    fun getTrackById(trackId: Int) {
        viewModelScope.launch {
            getTrackByIdUseCase(trackId).collect{
                trackState.value = it
            }
        }
    }
}