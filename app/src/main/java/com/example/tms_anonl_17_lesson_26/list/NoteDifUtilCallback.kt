package com.example.tms_anonl_17_lesson_26.list

import androidx.recyclerview.widget.DiffUtil
import com.example.tms_anonl_17_lesson_26.Note

// создать обьет NoteDiffUtilCallback (он сравнивает две заметки в себе), переопределить два метода
//создать в адаптере differ и функцию аптейд, используя диффер.сабмит
//в майне заменить note на апдейт

object NoteDiffUtilCallback: DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem===newItem
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem==newItem
    }
}