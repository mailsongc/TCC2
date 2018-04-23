package com.example.mailson.tcc

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIniciar = findViewById<Button>(R.id.btnIniciar)
        val btnNovo = findViewById<Button>(R.id.btnNovo)

        btnIniciar.setOnClickListener{
            var intent = Intent(this, LogarActivity::class.java)
            //var intent = Intent(this, LerPlacaActivity::class.java)
            startActivity(intent)
        }

        btnNovo.setOnClickListener {
            var intent = Intent(this, CadCNHActivity::class.java)
            startActivity(intent)
        }

    }
}
