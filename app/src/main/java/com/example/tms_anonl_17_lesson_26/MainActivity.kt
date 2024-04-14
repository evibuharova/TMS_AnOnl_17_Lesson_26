package com.example.tms_anonl_17_lesson_26

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tms_anonl_17_lesson_26.databinding.ActivityMainBinding
import com.example.tms_anonl_17_lesson_26.list.NoteListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val fragment = NoteListFragment()
        //контейнер в маин лояут активити пустой, мы вставляем туда фрагмент
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}