package com.shinto.studentregistration

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

// 8
class AddEditNoteActivity : AppCompatActivity() {
    lateinit var noteName: EditText
    lateinit var noteClass: EditText
    lateinit var noteDob:EditText
    lateinit var noteSchool:EditText
    lateinit var addupdatebutton: Button
    lateinit var viewModel: NoteViewModel
    var noteID = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        noteName = findViewById(R.id.txtHead)
        noteClass = findViewById(R.id.txtClass)
        noteDob=findViewById(R.id.txtDob)
        noteSchool=findViewById(R.id.txtSchool)
        addupdatebutton = findViewById(R.id.btnUpdate)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(
            NoteViewModel::class.java
        )
        val notetype = intent.getStringExtra("noteType")
        if (notetype.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            Log.d("massss",noteTitle.toString())
            val noteDesc = intent.getStringExtra("noteDescription")
            val noteDobs = intent.getStringExtra("notDob")
            val noteSchools = intent.getStringExtra("noteSchool")
            noteID = intent.getIntExtra("noteID", -1)
            addupdatebutton.setText("Update note")

            noteName.setText(noteTitle.toString())
            noteClass.setText(noteDesc.toString())
            noteDob.setText(noteDobs.toString())
            noteSchool.setText(noteSchools.toString())

        } else {
            addupdatebutton.setText("Save Note")
        }

        addupdatebutton.setOnClickListener {
            val noteName = noteName.text.toString()
            val noteClass = noteClass.text.toString()
            val noteDob= noteDob.text.toString()
            val noteSchool=noteSchool.text.toString()


            if (notetype.equals("Edit")) {
                if (noteName.isNotEmpty() && noteClass.isNotEmpty()&& noteDob.isNotEmpty()&& noteSchool.isNotEmpty()) {
                    val updateNote = Note(noteName, noteClass,noteDob,noteSchool)
                     updateNote.id=noteID
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this, "Note update..(it save in the  pass the value in view model)", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteName.isNotEmpty() && noteClass.isNotEmpty()&& noteDob.isNotEmpty()&&noteSchool.isNotEmpty()) {
                    viewModel.addNote(Note(noteName, noteClass,noteDob,noteSchool))
                    Toast.makeText(this, "note added", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }


    }
}
