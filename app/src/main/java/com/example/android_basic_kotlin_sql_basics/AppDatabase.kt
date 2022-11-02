package com.example.android_basic_kotlin_sql_basics

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CaliforniaPark::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun californiaParkDao(): CaliforniaParkDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .createFromAsset("database/sql_basics.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}