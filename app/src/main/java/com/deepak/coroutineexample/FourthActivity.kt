package com.deepak.coroutineexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class FourthActivity : AppCompatActivity() {

    val TAG = FourthActivity::class.java.simpleName;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        val job = GlobalScope.launch(Dispatchers.Default) {
            //As it mention it will repeat this times mentioned.
//            repeat(5){
//                Log.d(TAG,"Coroutine is still working...")
//                delay(1000L)
//            }

            Log.d(TAG,"Starting long runnning calculations...")

            //Another way to close computation within coroutine. This wouldnt need canceling a job like below.
            withTimeout(3000L){
                for(i in 30..45){
                    //Required to check, otherwise this calculation will go even after cancel. Hence isActive check added.
                    if(isActive){
                        Log.d(TAG,"Result for i = $i : ${fib(i)}")
                    }
                }
            }

            Log.d(TAG,"Ending long running calculation")
        }

        //Blocks main thread
//        runBlocking {
//            delay(2000L)    //Blocking for 2 seconds here.
//            job.cancel()
//            Log.d(TAG,"Canceled Job after 2 seconds . Main Thread is continuing...")
//        }

    }

    fun fib(n:Int):Long{
        return if(n==0) 0
        else if(n==1) 1
        else fib(n-1) + fib(n-2)
    }
}