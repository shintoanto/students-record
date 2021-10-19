package com.shinto.studentregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
// 6
class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {
    lateinit var noteRv: RecyclerView
    lateinit var addFAB: FloatingActionButton
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteRv = findViewById(R.id.idRvNotes)
        addFAB = findViewById(R.id.fabadnote)
        noteRv.layoutManager = LinearLayoutManager(this)
        val noteRVAdapter = NoteRVAdapter(this, this, this)
        noteRv.adapter = noteRVAdapter
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, { list ->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener{
            val intent=Intent(this@MainActivity,AddEditNoteActivity::class.java)
            startActivity(intent)
//            this.finish()
        }
    }

    override fun onNoteClick(note: Note) {


        val intent=Intent(this@MainActivity,AddEditNoteActivity::class.java)

        intent.putExtra("noteType","Edit")
        Log.d("mes",note.noteTitle)
        intent.putExtra("noteTitle",note.noteTitle)
        intent.putExtra("noteDescription",note.noteDescription)
        intent.putExtra("notDob",note.noteDob)
        intent.putExtra("noteSchool",note.noteSchool)
        intent.putExtra("noteID",note.id)
        Log.d("inte",intent.toString())
        startActivity(intent)
       // this.finish()
    }

    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.noteTitle}Deleted",Toast.LENGTH_LONG).show()    }
}