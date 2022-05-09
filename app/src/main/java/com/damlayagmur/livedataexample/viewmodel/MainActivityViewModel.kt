package com.damlayagmur.livedataexample.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private lateinit var timer: CountDownTimer

    private val _finished = MutableLiveData<Boolean>()
    val finished: LiveData<Boolean>
        get() = _finished

    private val _seconds = MutableLiveData<Int>()
    val seconds: LiveData<Int>
        get() = _seconds

    fun startTimer() {
        timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(p0: Long) {
                val timeLeft = p0 / 1000
                _seconds.value = timeLeft.toInt()
            }

            override fun onFinish() {
                _finished.value = true
            }

        }.start()
    }

    fun stopTimer() {
        timer.cancel()
    }
}