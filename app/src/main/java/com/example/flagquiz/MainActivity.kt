package com.example.flagquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.widget.EditText
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val playerName: EditText = findViewById(R.id.editTextText)
        val btnStart: Button = findViewById(R.id.button)

        btnStart.setOnClickListener {
            val playerName = playerName.text.toString()

            if (playerName.isEmpty()) {
                // Exibe uma mensagem se o nome do jogador estiver vazio
                Toast.makeText(this, "Por favor, insira seu nome!", Toast.LENGTH_SHORT).show()
            } else {
                // Cria um Intent para iniciar a próxima Activity (QuizActivity)
                val intent = Intent(this, QuizActivity::class.java)

                // Passa o nome do jogador para a próxima Activity
                intent.putExtra("PLAYER_NAME", playerName)

                // Inicia a QuizActivity
                startActivity(intent)
            }
        }
    }
}