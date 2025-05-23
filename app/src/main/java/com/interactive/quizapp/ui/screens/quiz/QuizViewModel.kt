package com.interactive.quizapp.ui.screens.quiz

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interactive.quizapp.domain.model.QuestionModel
import com.interactive.quizapp.domain.usecase.GetQuestionsByCategoryUseCase
import com.interactive.quizapp.domain.usecase.UpdateQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuestionsByCategoryUseCase: GetQuestionsByCategoryUseCase,
    private val updateQuestionsUseCase: UpdateQuestionsUseCase
): ViewModel() {

    private val _questions = mutableStateListOf<QuestionModel>()
    val questions: List<QuestionModel> get() = _questions

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> get() = _currentPage

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    fun updateCurrentPage(index: Int) {
        _currentPage.value = index
    }

    fun getQuestionsByCategory(category: String?) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                getQuestionsByCategoryUseCase.invoke(category = category).collect { items ->
                    items.forEach { it.userAnswerIndex = null }
                    _questions.apply {
                        clear()
                        addAll(items)
                    }
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isLoading.value = true
                Timber.e(e.message, "Failed to get questions by category")
            }
        }

    }

    fun selectAnswer(questionId: Int, selectedIndex: Int) {
        val index = _questions.indexOfFirst { it.id == questionId }
        if (index != -1) {
            _questions[index] = _questions[index].copy(userAnswerIndex = selectedIndex)
        }
    }

    fun updateAnswers() {
        viewModelScope.launch {
            updateQuestionsUseCase.invoke(_questions)
        }
    }

    fun playAgain(category: String?) {
        getQuestionsByCategory(category = category)
        _currentPage.value = 0
    }
}