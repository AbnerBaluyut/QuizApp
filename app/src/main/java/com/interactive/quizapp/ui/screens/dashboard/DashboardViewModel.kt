package com.interactive.quizapp.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interactive.quizapp.domain.usecase.SaveQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

sealed class DashboardState {
    data object SuccessState: DashboardState()
    data object ErrorState: DashboardState()
    data object LoadingState: DashboardState()
}

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val saveQuestionsUseCase: SaveQuestionsUseCase
): ViewModel() {

    private val _state = MutableStateFlow<DashboardState>(DashboardState.LoadingState)

    init {
        startUp()
    }

    private fun startUp() {
        _state.value = DashboardState.LoadingState
        viewModelScope.launch {
            try {
                saveQuestionsUseCase.invoke()
                _state.value = DashboardState.SuccessState
            } catch (e: Exception) {
                _state.value = DashboardState.ErrorState
                Timber.e(e.message, "Failed to save questions")
            }
        }
    }
}