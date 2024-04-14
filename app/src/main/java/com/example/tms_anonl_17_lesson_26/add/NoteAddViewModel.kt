package com.example.tms_anonl_17_lesson_26.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tms_anonl_17_lesson_26.Note
import com.example.tms_anonl_17_lesson_26.NoteHolder
import com.example.tms_anonl_17_lesson_26.SingleLiveEvent
import com.example.tms_anonl_17_lesson_26.add.NoteAddFragment.Action
import com.example.tms_anonl_17_lesson_26.add.NoteAddFragment.Event
import com.example.tms_anonl_17_lesson_26.add.NoteAddFragment.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NoteAddViewModel @Inject constructor(
    private val noteHolder: NoteHolder
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state
    val event = SingleLiveEvent<Event>()

    fun onAction(action: Action) = when (action) {
        is Action.SaveClick -> {
            val newMote = Note(action.text, action.header, Date().toString())
            noteHolder.notes.add(newMote)

            viewModelScope.launch (Dispatchers.Main) {
                // показываем прогресс
                _state.value = State.Progress(isVisible = true)

                // ждем 3 секунды
                withContext(Dispatchers.IO) {
                    delay(THREE_SECONDS)
                }

                // скрываем прогресс
                _state.value = State.Progress(isVisible = false)

                // возвращаемся на предыдущий экран
                event.value = Event.GoBack
            }
        }
    }

    companion object {
        const val THREE_SECONDS = 3000L
    }
}
