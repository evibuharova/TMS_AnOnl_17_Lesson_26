package com.example.tms_anonl_17_lesson_26.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tms_anonl_17_lesson_26.Note
import com.example.tms_anonl_17_lesson_26.R
import com.example.tms_anonl_17_lesson_26.add.NoteAddFragment
import com.example.tms_anonl_17_lesson_26.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListFragment : Fragment() {
    private var binding: FragmentListBinding? = null
    private val adapter = NotesRecyclerViewAdapter()
    private val viewModel: NoteListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater)
        return binding?.root
    }

    //переопределили метод и вставили адаптер
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onAction(Action.Update)
        initView()
        observeViewModel()
    }

    private fun initView() {
        binding?.recycler?.adapter = adapter
        binding?.addButton?.setOnClickListener { viewModel.onAction(Action.AddClick) }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state -> showState(state) }
        viewModel.event.observe(viewLifecycleOwner) { event -> showEvent(event) }
    }

    private fun showState(state: State) = when (state) {
        is State.Notes -> {
            adapter.update(state.items)
            binding?.progress?.isVisible = false
        }

        is State.Initial -> {
            adapter.update(state.initialLIst)
            binding?.progress?.isVisible = false
        }

        State.Progress -> {
            binding?.progress?.isVisible = true
        }
    }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    private fun showEvent(event: Event) = when (event) {
        Event.GoToAddScreen -> {
            val fragment = NoteAddFragment()
            parentFragmentManager.beginTransaction().replace(R.id.container, fragment)
                .addToBackStack("back").commit()
        }

        Event.ShowSuccessMessage -> {
            Toast.makeText(requireContext(), R.string.success_message, Toast.LENGTH_LONG)
                .show()
        }
    }


    //метод ондестройвью вызывается каждый раз после возврата на экран
    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    sealed interface State {
        data class Notes(val items: List<Note>) : State
        class Initial(val initialLIst: List<Note>) : State
        object Progress : State
    }

    sealed interface Event {

        data object GoToAddScreen : Event
        data object ShowSuccessMessage : Event
    }

    sealed interface Action {
        data object AddClick : Action
        data object Update : Action
    }
}