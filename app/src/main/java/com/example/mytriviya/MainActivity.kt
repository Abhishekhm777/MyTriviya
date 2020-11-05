package com.example.mytriviya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Setting theme dynamically
        setTheme(R.style.MyTriviya)
        setContentView(R.layout.activity_main)
    }
}