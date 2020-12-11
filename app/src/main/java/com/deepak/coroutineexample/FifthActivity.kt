package com.deepak.coroutineexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class FifthActivity : AppCompatActivity() {

    val TAG = FifthActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth)

        GlobalScope.launch {

            val time = measureTimeMillis {
                var answer1:String?= null
                var answer2 :String? = null

                // Parallel calls
                val job1 = launch {
                    answer1 = networkCall()
                }

                // Parallel calls
                val job2 = launch {
                    answer2 = networkCallAgain()
                }



                //Join the jobs to get the expected answers in the log below, Else they would go ahead and state null.
                job1.join()
                job2.join()






                Log.d(TAG," networkCall : $answer1")
                Log.d(TAG," networkCallAgain : $answer2")

                //Better than the above use async
                //Deferred Strings, give funcs await to wait to receive the output. No need for join.
                val betterAnswer1 = async { networkCall() }
                val betterAnswer2 = async { networkCallAgain() }

                Log.d(TAG, " Ideal way to use async : networkCall : ${betterAnswer1.await()}")
                Log.d(TAG, " Ideal way to use async : networkCallAgain : ${betterAnswer2.await()}")

            }

            Log.d(TAG,"Requests took $time ms")

        }

    }



    suspend fun networkCall():String{
        delay(3000L);
        return "Answer 1";
    }

    suspend fun networkCallAgain():String{
        delay(3000L);
        return "Answer 1 Again";
    }


}