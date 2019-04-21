package com.example.athletesapp

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


     //   recyclerView_main.setBackgroundColor(Color.BLUE)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
      //  recyclerView_main.adapter = MainAdapter()
        fetchJson()
    }

    fun fetchJson(){
        println("Attempting to fetch Json")

        val url = "https://gist.githubusercontent.com/MohamedWael/1406437f14e9a769a3a572a78906388f/raw/5be50e67c96c5ed1da9fe6344d2dd7befef0ba25/"

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{

            override fun onResponse(call: Call, response: Response) {

                 val body = response.body()?.string()
                println(body)

                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body,HomeFeed::class.java)


                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(homeFeed)
                }

            }
            override fun onFailure(call: Call, e: IOException) {
               println("Failed to execute request")
            }
        } )
    }
}

class HomeFeed(val athletes:List<Athlete>)
class Athlete (val name:String,val image:String, val brief:String)
