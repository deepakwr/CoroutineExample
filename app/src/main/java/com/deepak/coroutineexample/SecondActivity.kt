package com.deepak.coroutineexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SecondActivity : AppCompatActivity() {

    val TAG = SecondActivity::class.java.simpleName;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        Log.d(TAG,"Before runBlocking")
        //Generally used for testing, It blocks main thread- Needs to understand more.
        runBlocking {
            launch(Dispatchers.IO){
                Log.d(TAG,"Start of runBlocking")
                delay(5000L)
                Log.d(TAG,"End of runBlocking")
            }
        }
        Log.d(TAG,"After runBlocking")

    }
}