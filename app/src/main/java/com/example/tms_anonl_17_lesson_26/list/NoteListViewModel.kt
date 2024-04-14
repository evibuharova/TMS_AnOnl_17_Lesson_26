package com.example.tms_anonl_17_lesson_26.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tms_anonl_17_lesson_26.Note
import com.example.tms_anonl_17_lesson_26.NoteHolder
import com.example.tms_anonl_17_lesson_26.SingleLiveEvent
import com.example.tms_anonl_17_lesson_26.list.NoteListFragment.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteHolder: NoteHolder
) : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state
    val event = SingleLiveEvent<Event>()

    init {
        noteHolder.notes.add(
            Note("examle note", "some random text", Date().toString())
        )
        _state.value = State.Initial(noteHolder.notes.toList())
    }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    fun onAction(action: Action) = when (action) {
        Action.Update -> {
            viewModelScope.launch(Dispatchers.Main) {
                //показываем прогресс
                _state.value = State.Progress

                // ждем 3 секунды
                withContext(Dispatchers.IO) {
                    delay(THREE_SECONDS)
                }

                // показываем сообщение о успешном добавлении
                val oldNotes = (_state.value as? State.Notes)?.items
                if (oldNotes != noteHolder.notes) {
                    event.value = Event.ShowSuccessMessage
                }

                // показываем обновленный список
                _state.value = State.Notes(noteHolder.notes.toList())
            }
        }

        Action.AddClick -> {
            event.value = Event.GoToAddScreen
        }
    }

    companion object {
        const val THREE_SECONDS = 3000L
    }
}
