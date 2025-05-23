package com.interactive.quizapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.interactive.quizapp.data.local.entities.QuestionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    // Get All Questions
    @Query("SELECT * FROM questions ORDER BY category")
    fun getQuestions(): Flow<List<QuestionEntity>>

    // Insert All Questions
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<QuestionEntity>)

    // Delete All Questions
//    @Query("DELETE FROM questions")
//    suspend fun deleteAllQuestions()

    // Get All Categories
    @Query("SELECT DISTINCT category FROM questions ORDER BY category ASC")
    fun getCategories(): Flow<List<String>>

    // Update All Questions
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateQuestions(questions: List<QuestionEntity>)

    // Update Question
    @Query("UPDATE questions SET userAnswerIndex = :answerIndex WHERE id = :id")
    suspend fun updateQuestion(id: Int, answerIndex: Int)

    // Get Questions By Category
    @Query("SELECT * FROM questions WHERE category = :category")
    fun getQuestionsByCategory(category: String): Flow<List<QuestionEntity>>

    // Get Random Category
    @Query("SELECT category FROM questions GROUP BY category ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomCategory(): String?
}