package com.example.myapplication.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import models.Course

@Database(entities = arrayOf(Course::class), version = 1)
abstract class HelloDatabase: RoomDatabase() {
    abstract fun courseDao(): CourseDao
}