package com.example.flagquiz

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {
    private lateinit var imgFlag: ImageView
    private lateinit var submitButton: Button

    private var score = 0
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
    private lateinit var answerTry: EditText
    private var currentFlagName: String = ""

    private val displayedFlagNames = mutableListOf<String>()
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
        answerTry = findViewById<EditText>(R.id.editTextText2)

        FLAGS_IN_GAME = flags.shuffled().take(5)
        displayRandomFlag()

        submitButton.setOnClickListener {
            checkAnswer()
            displayRandomFlag()
        }
    }
    private fun displayRandomFlag() {
        if(questionNumber < FLAGS_IN_GAME.size){
            val currentFlagId = FLAGS_IN_GAME[questionNumber]
            imgFlag.setImageResource(FLAGS_IN_GAME[questionNumber])
            questionNumber++

            val resourceName = resources.getResourceEntryName(currentFlagId)
            try {
                val parts = resourceName.split("_")
                if (parts.size > 1){
                    currentFlagName = parts.drop(1).joinToString("_")
                    displayedFlagNames.add(currentFlagName)
                } else {
                    currentFlagName = resourceName
                    displayedFlagNames.add(currentFlagName)
                }
            } catch (e: Exception){
                currentFlagName = ""
            }
        }
    }
    fun checkAnswer (){
        val userAnswer = answerTry.text.toString().trim()
        if (userAnswer.equals(currentFlagName, ignoreCase = true)){
            Toast.makeText(this, "Correto", Toast.LENGTH_LONG).show()
            score += 0
        } else{
            Toast.makeText(this, "Errado", Toast.LENGTH_LONG).show()
        }
    }
}