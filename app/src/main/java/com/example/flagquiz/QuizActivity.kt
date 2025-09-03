package com.example.flagquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {
    private lateinit var imgFlag: ImageView
    private lateinit var submitButton: Button
    private lateinit var answerTry: EditText
    private lateinit var playerName: String
    private var score = 0

    private val flags = listOf(
        R.drawable.flag_argentina, R.drawable.flag_brasil, R.drawable.flag_venezuela,
        R.drawable.flag_sudao, R.drawable.flag_israel, R.drawable.flag_espanha,
        R.drawable.flag_estadosunidos, R.drawable.flag_estonia, R.drawable.flag_russia,
        R.drawable.flag_uruguai
    )

    private var currentFlagName: String = ""
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

        playerName = intent.getStringExtra("PLAYER_NAME") ?: ""

        imgFlag = findViewById(R.id.imageView2)
        submitButton = findViewById(R.id.button2)
        answerTry = findViewById(R.id.editTextText2)

        FLAGS_IN_GAME = flags.shuffled().take(5)
        displayNextFlag()

        submitButton.setOnClickListener {
            checkAnswer()
            displayNextFlag()
        }
    }

    private fun displayNextFlag() {
        if (questionNumber < FLAGS_IN_GAME.size) {
            val currentFlagId = FLAGS_IN_GAME[questionNumber]
            imgFlag.setImageResource(currentFlagId)

            val resourceName = resources.getResourceEntryName(currentFlagId)
            currentFlagName = try {
                val parts = resourceName.split("_")
                if (parts.size > 1) parts.drop(1).joinToString("_") else resourceName
            } catch (e: Exception) {
                ""
            }
        } else {
            val intent = Intent(this, resultado::class.java)
            intent.putExtra("EXTRA_SCORE", score)
            intent.putExtra("PLAYER_NAME", playerName)
            startActivity(intent)
            finish()
        }
    }

    fun checkAnswer() {
        val userAnswer = answerTry.text.toString().trim()
        if (userAnswer.equals(currentFlagName, ignoreCase = true)) {
            score += 20
            Toast.makeText(this, "Correto! Pontuação: $score", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Errado", Toast.LENGTH_SHORT).show()
        }
        answerTry.text.clear()
        questionNumber++
    }
}