package com.example.tms_anonl_17_lesson_26

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteHolder @Inject constructor(){
    val notes = mutableListOf<Note>()
}