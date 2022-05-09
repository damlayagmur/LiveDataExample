package com.damlayagmur.livedataexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.damlayagmur.livedataexample.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    lateinit var numberText: TextView
    lateinit var startButton: Button
    lateinit var stopButton: Button
    lateinit var inputEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        numberText = findViewById(R.id.numberText)
        startButton = findViewById(R.id.startButton)
        stopButton = findViewById(R.id.stopButton)
        inputEditText = findViewById(R.id.inputEditText)

        viewModel.seconds.observe(this, Observer {
            numberText.text = it.toString()
        })

        viewModel.finished.observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Finished!", Toast.LENGTH_LONG).show()
            }
        })

        startButton.setOnClickListener {
            if (inputEditText.text.isEmpty() || inputEditText.text.length < 4) {
                Toast.makeText(this, "Invalid Number", Toast.LENGTH_LONG).show()
            } else {
                viewModel.timerValue.value = inputEditText.text.toString().toLong()
                viewModel.startTimer()
            }
        }

        stopButton.setOnClickListener {
            viewModel.stopTimer()
        }
    }

}