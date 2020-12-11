package com.deepak.coroutineexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.logging.Level
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    // tutorial Source : https://www.youtube.com/watch?v=ShNhJ3wMpvQ&list=PLQkwcJG4YTCQcFEPuYGuv54nYai_lwil_&index=1&ab_channel=PhilippLackner

    val TAG = MainActivity::class.simpleName;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startFirstActivity(view:View){
        Intent(this@MainActivity,FirstActivity::class.java).also {
            startActivity(it);
        }
    }

    fun startSecondActivity(view:View){
        Intent(this@MainActivity,SecondActivity::class.java).also {
            startActivity(it);
        }
    }

    fun startThirdActivity(view:View){
        Intent(this@MainActivity,ThirdActivity::class.java).also {
            startActivity(it);
        }
    }

    fun startFourthActivity(view:View){
        Intent(this@MainActivity, FourthActivity::class.java).also {
            startActivity(it)
        }
    }

    fun startFifthActivity(view:View){
        Intent(this@MainActivity,FifthActivity::class.java).also {
            startActivity(it)
        }
    }

    fun startSixthActivity(view:View){
        Intent(this@MainActivity,SixthActivity::class.java).also {
            startActivity(it)
        }
    }

}