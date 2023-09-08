package com.example.ganheinamega

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.edit_number)
        val txtResult: TextView = findViewById(R.id.txt_result)
        val btnGenerator: Button = findViewById(R.id.btn_generate)

        prefs = getSharedPreferences("db", MODE_PRIVATE)
        prefs.getString("result", null)?.let {
            txtResult.text = it
        }

        btnGenerator.setOnClickListener {
            val text = editText.text.toString()
            numberGenerate(text, txtResult)
        }

    }

    private fun numberGenerate(text: String, txtResult: TextView) {
        if (text.isEmpty())
            return Toast
                .makeText(this, "Digite um número", Toast.LENGTH_LONG)
                .show()

        val qtd = text.toInt()
        if (qtd !in 6..15) {
            return Toast
                .makeText(this, "Digite um número entre 6 e 15", Toast.LENGTH_LONG)
                .show()
        }

        val numbers = mutableSetOf<Int>()
        while (numbers.size < qtd) {
            val random = (1..60).random()
            numbers.add(random)
        }

        txtResult.text = numbers.sorted().joinToString(" - ")

        prefs.edit().apply {
            putString("result", txtResult.text.toString())
            apply()
        }
    }

}