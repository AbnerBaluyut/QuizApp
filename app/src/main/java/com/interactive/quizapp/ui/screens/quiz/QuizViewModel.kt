package com.interactive.quizapp.ui.screens.quiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interactive.quizapp.domain.model.QuestionModel
import com.interactive.quizapp.domain.usecase.GetQuestionsByCategoryUseCase
import com.interactive.quizapp.domain.usecase.UpdateQuestionsUseCase
import com.interactive.quizapp.utils.Args
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuestionsByCategoryUseCase: GetQuestionsByCategoryUseCase,
    private val updateQuestionsUseCase: UpdateQuestionsUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val categoryArg: String? = savedStateHandle[Args.CATEGORY]
    val isFromHistoryArg: Boolean = checkNotNull(savedStateHandle[Args.IS_FROM_HISTORY])


    private val _questions = MutableStateFlow<List<QuestionModel>>(emptyList())
    val questions: StateFlow<List<QuestionModel>> get() = _questions

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> get() = _currentPage

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    init {
        getQuestionsByCategory(category = categoryArg)
    }

    private fun getQuestionsByCategory(category: String?) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                getQuestionsByCategoryUseCase.invoke(category = category).collect { items ->
                    if (!isFromHistoryArg) {
                        items.map {
                            it.userAnswerIndex = null
                        }
                    }
                    _questions.value = items
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isLoading.value = false
                Timber.e(e.message, "Failed to get questions by category")
            }
        }

    }

    fun selectAnswer(questionId: Int, selectedIndex: Int) {
        _questions.value = _questions.value.map {
            if (it.id == questionId) it.copy(userAnswerIndex = selectedIndex) else it
        }
    }

    fun updateCurrentPage(index: Int) {
        _currentPage.value = index
    }

    fun updateAnswers() {
        viewModelScope.launch {
            try {
                updateQuestionsUseCase.invoke(_questions.value)
            } catch (e: Exception) {
                Timber.e(e, "Failed to update answers")
            }
        }
    }

    fun playAgain() {
        _questions.value = _questions.value.map {
            it.copy(userAnswerIndex = null)
        }
        _currentPage.value = 0
    }
}