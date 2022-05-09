package com.damlayagmur.livedataexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.damlayagmur.livedataexample.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    lateinit var numberText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        numberText = findViewById(R.id.numberText)

        viewModel.startTimer()
        viewModel.seconds.observe(this, Observer {
            numberText.text = it.toString()
        })
    }

}