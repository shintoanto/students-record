package com.shinto.studentregistration

// 3
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)

abstract class NoteDatabase : RoomDatabase() {
    // RoomDatabse is a abstract class of database
    abstract fun getNotesDao(): NoteDao
    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null
        fun getDatabase(context: Context): NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java, "note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}