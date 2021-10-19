package com.shinto.studentregistration

// 5
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {
    val allNotes: LiveData<List<Note>>
    val repository:NoteRepository

    init {
        val dao=NoteDatabase.getDatabase(application).getNotesDao()
        repository= NoteRepository(dao)
        print(repository)
        Log.d("repository","repository")
        allNotes=repository.allNotes
    }

    fun deleteNote(note:Note)=  viewModelScope.launch ( Dispatchers.IO ){
        repository.delete(note)
    }

    fun updateNote(note:Note)=viewModelScope.launch ( Dispatchers.IO) {
        repository.update(note)
    }

    fun addNote(note:Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}