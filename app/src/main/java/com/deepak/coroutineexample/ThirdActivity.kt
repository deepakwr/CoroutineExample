package com.deepak.coroutineexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.coroutines.*

class ThirdActivity : AppCompatActivity() {

    val TAG = ThirdActivity::class.java.simpleName;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)


//        Suspend function 'doNetworkCall' should be called only from a coroutine or another suspend functio
//        doNetworkCall();

        GlobalScope.launch {
            /*
            This thread is deleted if the Application removed from recent or killed.
            The reason because once the main thread is removed or stopped, the other threads connected in the application scope are removed.
             */

            val netWorkcall = doNetworkCall();  //Delay added of 3 seconds
            val netWorkcallAgain = doNetworkCallAgain(); //Delay added of 3 seconds again

            Log.i(TAG,"MainActivity netWorkcall : ${netWorkcall}")
            Log.i(TAG,"MainActivity netWorkcallAgain : ${netWorkcallAgain}")

            //Another way of Switching thread here to update UI from the default thread.
            withContext(Dispatchers.Main){
                text_view_message.text = netWorkcall;
            }
        }

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