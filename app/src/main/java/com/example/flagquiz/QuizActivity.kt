package com.example.flagquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {

    private var nomeParaEnviar: String? = null
    private lateinit var imgFlag: ImageView
    private lateinit var submitButton: Button
    private val flags = listOf(
        R.drawable.flag_argentina,
        R.drawable.flag_brasil,
        R.drawable.flag_venezuela,
        R.drawable.flag_sudao,
        R.drawable.flag_israel,
        R.drawable.flag_espanha,
        R.drawable.flag_estadosunidos,
        R.drawable.flag_estonia,
        R.drawable.flag_russia,
        R.drawable.flag_uruguai
    )

    private lateinit var FLAGS_IN_GAME: List<Int>
    private var questionNumber = 0

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
        submitButton = findViewById(R.id.button2)

        FLAGS_IN_GAME = flags.shuffled().take(5)
        displayRandomFlag()

        submitButton.setOnClickListener {
            displayRandomFlag()
        }

        val  name = intent.getStringExtra("PLAYER_NAME")





        val testeButton = findViewById<Button>(R.id.buttonTest)

        testeButton.setOnClickListener{
            val intent = Intent(this, resultado::class.java)
            intent.putExtra("PLAYER_NAME", name)
            startActivity(intent)

        }

    }


    private fun displayRandomFlag() {
        if(questionNumber < FLAGS_IN_GAME.size){
            imgFlag.setImageResource(FLAGS_IN_GAME[questionNumber])
            questionNumber++
        }
    }
}