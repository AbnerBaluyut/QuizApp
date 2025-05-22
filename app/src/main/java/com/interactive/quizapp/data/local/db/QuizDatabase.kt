package com.interactive.quizapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.interactive.quizapp.data.local.dao.QuestionDao
import com.interactive.quizapp.data.local.entities.QuestionEntity
import com.interactive.quizapp.utils.Converters

@Database(entities = [QuestionEntity::class], version = 3)
@TypeConverters(Converters::class)
abstract class QuizDatabase: RoomDatabase() {
    abstract fun questionDao(): QuestionDao
}