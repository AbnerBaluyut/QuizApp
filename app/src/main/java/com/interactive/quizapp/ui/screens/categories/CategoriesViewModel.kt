package com.interactive.quizapp.ui.screens.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interactive.quizapp.domain.model.QuestionModel
import com.interactive.quizapp.domain.usecase.GetCategoriesUseCase
import com.interactive.quizapp.domain.usecase.GetQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

sealed class CategoriesState {
    data object LoadingState: CategoriesState()
    data class SuccessState(val categories: List<String>): CategoriesState()
    data class ErrorState(val errorMessage: String): CategoriesState()
}

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
): ViewModel() {

    private val _state = MutableStateFlow<CategoriesState>(CategoriesState.LoadingState)
    val state: StateFlow<CategoriesState> = _state

    init {
        getCategories()
    }

    fun getCategories() {
        _state.value = CategoriesState.LoadingState
        viewModelScope.launch {
            try {
                getCategoriesUseCase.invoke().collect { categories ->
                    _state.value = CategoriesState.SuccessState(categories = categories)
                }
            } catch (e: Exception) {
                _state.value = CategoriesState.ErrorState(errorMessage = "Something went wrong")
                Timber.e(e.message, "Failed to get categories")
            }
        }
    }
}