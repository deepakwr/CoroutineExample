package com.deepak.coroutineexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.logging.Level
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.simpleName;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        GlobalScope.launch {
            /*
            This thread is deleted if the Application removed from recent or killed.
            The reason because once the main thread is removed or stopped, the other threads connected in the application scope are removed.
             */

            delay(5000L);
            Log.i(TAG,"Thread name within Coroutine : ${Thread.currentThread().name}")
            val threadName = Thread.currentThread().name;

            launch(Dispatchers.Main){
                text_view_message.text = "Main Activity : ${threadName}";
            }
        }

        Log.i(TAG,"MainActivity onCreate :    Thread name within Coroutine : ${Thread.currentThread().name}")
        text_view_message.text = "Main Activity : ${Thread.currentThread().name}";


    }
}