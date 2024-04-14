package com.example.tms_anonl_17_lesson_26.list

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tms_anonl_17_lesson_26.Note
import com.example.tms_anonl_17_lesson_26.R

class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val name:TextView=itemView.findViewById(R.id.name)
    private val text:TextView=itemView.findViewById(R.id.text)
    private val date:TextView=itemView.findViewById(R.id.date)

    fun bind(note: Note) {
        name.text = note.name
        text.text = note.text
        date.text = note.data
    }
}
