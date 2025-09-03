package com.example.flagquiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class resultado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val resultname = findViewById<TextView>(R.id.Resultname)
        val namereceived = intent.getStringExtra("PLAYER_NAME")
        resultname.text = namereceived

        val resultScore = findViewById<TextView>(R.id.score)
        val scoreReceived = intent.getIntExtra("EXTRA_SCORE", 0)
        resultScore.text = "Pontuação: $scoreReceived"

        val buttonrRestart = findViewById<Button>(R.id.buttonRestart)
        buttonrRestart.setOnClickListener {
        // Recupera os dados do Intent
        val playerName = intent.getStringExtra("PLAYER_NAME")
        val finalScore = intent.getIntExtra("EXTRA_SCORE", 0)
        val resultsList = intent.getSerializableExtra("QUIZ_RESULTS") as? ArrayList<QuestionResult>

        // Encontra os elementos do layout
        val tvPlayerName = findViewById<TextView>(R.id.Resultname)
        val tvFinalScore = findViewById<TextView>(R.id.score)
        val resultsLayout = findViewById<LinearLayout>(R.id.tableResult)
        val buttonRestart = findViewById<Button>(R.id.buttonRestart)

        // Exibe o nome do jogador e a pontuação final
        tvPlayerName.text = playerName
        tvFinalScore.text = "Pontuação: $finalScore"

        // Itera sobre a lista de resultados e exibe cada um formatado
        resultsList?.forEach { result ->
            val resultTextView = TextView(this)
            val statusText = if (result.isCorrect) "Correto" else "Incorreto"
            val statusColor = if (result.isCorrect) Color.GREEN else Color.RED
            val displayText = "Bandeira: ${result.flagName.replace("_", " ")} - $statusText"

            resultTextView.text = displayText
            resultTextView.setTextColor(statusColor)
            resultTextView.setPadding(0, 8, 0, 8)

            // Adiciona a visualização de texto ao layout dinamicamente
            resultsLayout.addView(resultTextView)
        }

        // Configura o botão para reiniciar o jogo
        buttonRestart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}