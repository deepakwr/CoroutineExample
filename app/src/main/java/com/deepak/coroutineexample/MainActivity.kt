package com.deepak.coroutineexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.logging.Level
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        GlobalScope.launch {
            /*
            This thread is deleted if the Application removed from recent or killed.
            The reason because once the main thread is removed or stopped, the other threads connected in the application scope are removed.
             */
            delay(5000L);
            Logger.getLogger(MainActivity::class.simpleName).log(Level.INFO,"Thread name within Coroutine : ${Thread.currentThread().name}")
        }

        Logger.getLogger(MainActivity::class.simpleName).log(Level.INFO,"MainActivity onCreate :    Thread name within Coroutine : ${Thread.currentThread().name}")
    }
}