package com.example.athletesapp

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.example.athletesapp.CustomViewHolder.Companion.PLAYER_BRIEF_KEY
import com.example.athletesapp.CustomViewHolder.Companion.PLAYER_NAME_KEY
import kotlinx.android.synthetic.main.detail.*

class CellDetails : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.detail)
      val title =   intent.getStringExtra(PLAYER_NAME_KEY)
        textView_players.text=title
        val summary = intent.getStringExtra(PLAYER_BRIEF_KEY)
        textView2.text = summary
    }

}