package com.example.tms_anonl_17_lesson_26.list
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.tms_anonl_17_lesson_26.Note
import com.example.tms_anonl_17_lesson_26.R

class NotesRecyclerViewAdapter(): RecyclerView.Adapter<NoteViewHolder>() {
    private val differ=AsyncListDiffer(this, NoteDiffUtilCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val listLayoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_layout, parent, false)
        return NoteViewHolder(listLayoutView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = differ.currentList[position]
        holder.bind(note)
    }

    override fun getItemCount() =  differ.currentList.size

    fun update (newItems: List<Note>) {
        differ.submitList(newItems)
    }
}