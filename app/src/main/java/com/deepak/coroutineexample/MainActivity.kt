package com.deepak.coroutineexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
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


//        Suspend function 'doNetworkCall' should be called only from a coroutine or another suspend functio
//        doNetworkCall();

        GlobalScope.launch {
            /*
            This thread is deleted if the Application removed from recent or killed.
            The reason because once the main thread is removed or stopped, the other threads connected in the application scope are removed.
             */


            delay(5000L);   //suspend function interally, can be called within CoroutineScope. Here delay is added on this thread.
            Log.i(TAG,"Thread name within Coroutine : ${Thread.currentThread().name}")
            val threadName = Thread.currentThread().name;

            launch(Dispatchers.Main){
                text_view_message.text = "Main Activity : ${threadName}";
            }

            val netWorkcall = doNetworkCall();  //Delay added of 3 seconds
            val netWorkcallAgain = doNetworkCallAgain(); //Delay added of 3 seconds again

            Log.i(TAG,"MainActivity netWorkcall : ${netWorkcall}")
            Log.i(TAG,"MainActivity netWorkcallAgain : ${netWorkcallAgain}")
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
                Intent(this@MainActivity,SecondActivity::class.java).also{
                    startActivity(it)
                    finish()
                }
            }
        }


        Log.i(TAG,"MainActivity onCreate :    Thread name within Coroutine : ${Thread.currentThread().name}")
        text_view_message.text = "Main Activity : ${Thread.currentThread().name}";

    }


    suspend fun doNetworkCall():String{
        delay(3000L);
        return "This is the answer - doNetworkCall";
    }

    suspend fun doNetworkCallAgain():String{
        delay(3000L);
        return "This is the answer - doNetworkCallAgain";
    }
}