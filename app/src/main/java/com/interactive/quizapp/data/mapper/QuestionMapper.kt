package com.interactive.quizapp.data.mapper

import com.interactive.quizapp.data.local.entities.QuestionEntity
import com.interactive.quizapp.domain.model.QuestionModel

fun QuestionEntity.toModel(): QuestionModel = QuestionModel(
    id = id,
    text = questionText,
    category = category,
    options = options,
    correctAnswerIndex = correctAnswerIndex,
    isMarked = isMarked,
    userAnswerIndex = userAnswerIndex
)

fun QuestionModel.toEntity(): QuestionEntity = QuestionEntity(
    id = id,
    questionText = text,
    category = category,
    options = options,
    correctAnswerIndex = correctAnswerIndex,
    isMarked = isMarked,
    userAnswerIndex = userAnswerIndex
)