package com.shinto.studentregistration

import androidx.lifecycle.LiveData
import androidx.room.*


// 2
// this is a class for crud operation
@Dao
interface NoteDao {

    //IF THERE IS ANY SAME ID THAT IS IGNORED
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from notesTable order by id ASC")

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
    fun getAllNotes(): LiveData<List<Note>>
}