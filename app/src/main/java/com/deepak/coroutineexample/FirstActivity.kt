package com.deepak.coroutineexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.coroutines.*

class FirstActivity : AppCompatActivity() {


    val TAG = FirstActivity::class.simpleName;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)


        GlobalScope.launch {
            /*
            This thread is deleted if the Application removed from recent or killed.
            The reason because once the main thread is removed or stopped, the other threads connected in the application scope are removed.
             */

            delay(5000L);   //suspend function interally, can be called within CoroutineScope. Here delay is added on this thread.
            Log.i(TAG,"Thread name within Coroutine : ${Thread.currentThread().name}")
            val threadName = Thread.currentThread().name;


            //Switching thread here to update UI from the default thread.
            launch(Dispatchers.Main){
                text_view_message.text = "Main Activity : ${threadName}";
            }
        }




        btn_start_activity.setOnClickListener {
            lifecycleScope.launch {
                while(true){
                    delay(1000L)
                    Log.i(TAG,"MainActivity onStartActivity :   Still Running..")
                }
            }

            GlobalScope.launch {
                delay(5000L)
                Intent(this@FirstActivity,SecondActivity::class.java).also{
                    startActivity(it)
                    finish()
                }
            }
        }


        Log.i(TAG,"MainActivity onCreate :    Thread name within Coroutine : ${Thread.currentThread().name}")
        text_view_message.text = "Main Activity : ${Thread.currentThread().name}";

    }
}