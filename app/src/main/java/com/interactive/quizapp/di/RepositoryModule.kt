package com.interactive.quizapp.di

import com.interactive.quizapp.data.local.dao.QuestionDao
import com.interactive.quizapp.data.repository.QuizRepositoryImpl
import com.interactive.quizapp.domain.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideQuizRepository(
        dao: QuestionDao
    ): QuizRepository = QuizRepositoryImpl(dao)
}