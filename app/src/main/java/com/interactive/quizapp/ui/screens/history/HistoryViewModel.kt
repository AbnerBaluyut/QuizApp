package com.interactive.quizapp.ui.screens.history

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interactive.quizapp.domain.model.QuestionModel
import com.interactive.quizapp.domain.usecase.GetQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getQuestionsUseCase: GetQuestionsUseCase
): ViewModel() {

    private val _groupedQuestions = MutableStateFlow<Map<String, List<QuestionModel>>>(mutableStateMapOf())
    val groupedQuestions: StateFlow<Map<String, List<QuestionModel>>> get() = _groupedQuestions

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    init {
        getQuestions()
    }

    private fun getQuestions() {
        _isLoading.value = true
        viewModelScope.launch {
            getQuestionsUseCase.invoke()
                .catch { _isLoading.value = false }
                .collect { questions ->
                    val groupedByCategory = questions.groupBy { it.category }
                    _groupedQuestions.value = groupedByCategory
                    _isLoading.value = false
                }
        }
    }
}