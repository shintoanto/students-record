package com.shinto.studentregistration

// 4
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val notesDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }

    suspend fun delete(note: Note) {
        notesDao.delete(note)
    }

    fun searchquery(searchQuery: String): Flow<List<Note>> {
        Log.d("Res", "---repoFuncIsWorkin")
        val r = notesDao.searchDatabase(searchQuery)
        Log.d("Res","dataaaaaaa $r.toString()")
        return r

    }

    suspend fun update(note: Note) {
        notesDao.update(note)
    }


}