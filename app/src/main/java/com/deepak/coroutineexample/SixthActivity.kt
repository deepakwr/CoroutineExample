package com.deepak.coroutineexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class SixthActivity : AppCompatActivity() {

    val TAG = SixthActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sixth)


        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RequestApi::class.java)


        /*
        Traditional way with retrofit in kotlin

        api.getComments().enqueue(
            object: Callback<List<Comment>> {
                override fun onResponse(
                    call: Call<List<Comment>>,
                    response: Response<List<Comment>>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            for(comment in it){
                                Log.d(TAG,comment.toString())
                            }
                        }
                    }
                }
                override fun onFailure(call: Call<List<Comment>>, t: Throwable) {

                    Log.e(TAG, "Error $t")
                }

            }
        )

         */


        //Retrofit with coroutine handled with far less code.
        GlobalScope.launch (Dispatchers.IO){
            val comments = api.getComments().await()    // No error handled
            for(comment in comments){
                Log.d(TAG,comment.toString())
            }

            //another with response checking
            val response = api.getComments().awaitResponse()
            if(response.isSuccessful){
                for(comment in response.body()!!){
                    Log.d(TAG,comment.toString())
                }
            }
        }

    }
}