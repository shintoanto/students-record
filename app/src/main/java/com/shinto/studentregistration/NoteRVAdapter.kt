package com.shinto.studentregistration

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// 7
class NoteRVAdapter(
    val context: Context,
    val noteClickInterface: NoteClickInterface,
    val noteClickDeleteInterface: NoteClickDeleteInterface
) : RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //  val noteTv = itemView.findViewById<ImageView>(R.id.item_Image)
        //  val classStudent = itemView.findViewById<CardView>(R.id.classId)
        val timeTv = itemView.findViewById<TextView>(R.id.item_title)
        val deleteTV = itemView.findViewById<TextView>(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.note_rv_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.timeTv.setText(allNotes.get(position).noteTitle)

        holder.deleteTV.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(
                allNotes.get(
                    position
                )
            )
        }
//        holder.rvTv.setOnClickListener {
//            noteClickInterface.onNoteClick(allNotes.get(position))
//        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface NoteClickDeleteInterface {
    fun onDeleteIconClick(note: Note)
}

interface NoteClickInterface {
    fun onNoteClick(note: Note)
}