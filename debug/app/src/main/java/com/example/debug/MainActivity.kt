package com.example.debug

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        val helloTextView: TextView = findViewById(R.id.hello_world) //setContentView(R.layout.activity_main)이거 전에 호출되면 null 반환 --> 오류
        helloTextView.text = "hello, debugging"


        logging()
        //division()
    }

    fun logging(){
        Log.v(TAG, "Hello, world!") //android.util.Log
    }

    fun division(){
        val numerator = 60
        var denominator = 4
        repeat(5){
            Log.v(TAG, "${numerator/denominator}")
            denominator--
        }
    }
}