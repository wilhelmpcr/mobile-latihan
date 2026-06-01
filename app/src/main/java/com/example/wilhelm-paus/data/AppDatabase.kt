package com.example.wilhelm_paus.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wilhelm_paus.data.dao.MessageDao
import com.example.wilhelm_paus.data.dao.NoteDao
import com.example.wilhelm_paus.data.entity.MessageEntity
import com.example.wilhelm_paus.data.entity.NoteEntity

@Database(entities = [NoteEntity::class, MessageEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun messageDao(): MessageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
