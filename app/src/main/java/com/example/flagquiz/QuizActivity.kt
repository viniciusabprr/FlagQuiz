package com.example.flagquiz

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {
    private lateinit var imgFlag: ImageView
    private val flags = listOf(
        R.drawable.flag_argentina,
        R.drawable.flag_brasil
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        imgFlag = findViewById(R.id.imageView2)
        displayRandomFlag()
    }

    private fun displayRandomFlag() {
        val randomFlagResource = flags.random()
        imgFlag.setImageResource(randomFlagResource)
    }
}