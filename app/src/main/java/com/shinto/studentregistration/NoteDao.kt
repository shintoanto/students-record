package com.shinto.studentregistration

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    //IF THERE IS ANY SAME ID THAT IS IGNORED
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notesTable WHERE Name LIKE:searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Note>>


//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun upsertByReplacement(image: List<Note>)
//
//    @Query("SELECT * FROM notesTable")
//    fun getAll(): List<Note>
//
//    @Query("SELECT * FROM notesTable WHERE id IN (data)")
//    fun findByIds(imageTestIds: List<Int>): List<Note>

//    @Delete
//    fun delete(imageTest: Note)

    // lIve data is observe data our data is updated or not.

}