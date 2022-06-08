package com.shinto.studentregistration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shinto.studentregistration.databinding.ActivityMainBinding
import kotlin.collections.ArrayList

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {

    lateinit var noteRv: RecyclerView


    lateinit var viewModel: NoteViewModel
    // private val myAdapter by lazy { NoteRVAdapter() }

    private lateinit var tempArraylist: ArrayList<List<Note>>
    private lateinit var saveArraylist: ArrayList<List<Note>>
    private lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteRv = findViewById(R.id.idRvNotes)
        noteRv.layoutManager = LinearLayoutManager(this)
        val noteRVAdapter = NoteRVAdapter(this, this, this)
        noteRv.adapter = noteRVAdapter
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this) { list ->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        }
        binding.fabadnote.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
        }

        //   tempArraylist = arrayListOf<List<Note>>()


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("Res", "---onQueryTextSubmit$query")
                if (query != null) {
                    getStudentData(query)
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Log.d("Res", "---onQueryTextChange")
                if (p0 != null) {
                    getStudentData(p0)
                }
                return true
            }

        })
    }

    private fun getStudentData(query: String) {
        val searchQuery = "%$query%"
        Log.d("Res", "---getStudentData$searchQuery")
        viewModel.searchDatabase(searchQuery).let{ list ->
            list.let {
                myAdapter = MyAdapter()
                myAdapter.setData(it)
            }
        }

    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        Log.d("mes", note.noteTitle)
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("notDob", note.noteDob)
        intent.putExtra("noteSchool", note.noteSchool)
        intent.putExtra("noteID", note.id)
        Log.d("inte", intent.toString())
        startActivity(intent)
    }

    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle}Deleted", Toast.LENGTH_LONG).show()
    }

}