package com.example.hf4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs
import kotlin.math.log10


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // finding the button
        val showButton = findViewById<Button>(R.id.button)

        // finding the edit text
        val etQ = findViewById<EditText>(R.id.etQ)
        val etL = findViewById<EditText>(R.id.etL)
        val etD = findViewById<EditText>(R.id.etD)
        val etV = findViewById<EditText>(R.id.etV)
        val etE = findViewById<EditText>(R.id.etE)
        val tvHF = findViewById<TextView>(R.id.tvHF)

        etQ.setText("0.001")
        etL.setText("200")
        etD.setText("0.025")
        etV.setText("0.00000101")
        etE.setText("0.0001")


        showButton.setOnClickListener{

            // Getting the user input
            val Q = etQ.editableText.toString().toDouble()
            val L = etL.editableText.toString().toDouble()
            val D = etD.editableText.toString().toDouble()
            val V = etV.editableText.toString().toDouble()
            val E = etE.editableText.toString().toDouble()

            val PI = 3.14159265359
            val Vel = (4 * Q) / (PI * D * D)
            val Re = Vel*D/V
            var x1 = 4.0
            do {
                val x2 =
                    x1 - (x1 + 2 * log10(E / (3.7 * D) + 2.51 * x1 / Re)) / (1 + 2.18 / (E * Re / (3.7 * D) + 2.51 * x1))
                val x = abs(x1-x2)
                 x1 = x2
            }while(x > 0.000001)
            val f=1/(x1*x1)
            val hf=f*L/D*Vel*Vel/19.62
            tvHF.text = hf.toString()
        }
    }
}