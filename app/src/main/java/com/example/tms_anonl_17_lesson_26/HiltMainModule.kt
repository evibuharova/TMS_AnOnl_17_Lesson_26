package com.example.tms_anonl_17_lesson_26

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class HiltMainModule {

    @Provides
    fun providesNoteHolder(): NoteHolder = NoteHolder()
}