package com.shinto.studentregistration

// 5
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class NoteViewModel(application: Application) : AndroidViewModel(application) {
    var d: Int = 0
    val allNotes: LiveData<List<Note>>
    val repository: NoteRepository
    var searchResponse: MutableLiveData<List<Note>> = MutableLiveData()


    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

    fun searchDatabase(searchQuery: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response: LiveData<List<Note>> =  repository.searchquery(searchQuery).asLiveData()
            searchResponse = response as MutableLiveData<List<Note>>
        }
    }

    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}


