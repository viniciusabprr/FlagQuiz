package com.example.flagquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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



        val buttonrRestart = findViewById<Button>(R.id.buttonRestart)

        buttonrRestart.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

            finish()
        }
    }

}