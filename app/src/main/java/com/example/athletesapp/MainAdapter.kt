package com.example.athletesapp

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.TextUtils.isEmpty
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_row.view.*
import java.lang.System.load

class MainAdapter(val homeFeed:HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {


    //numberOfItems
    override fun getItemCount(): Int {
        return homeFeed.athletes.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        //view
        val layoutInflater = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, p0, false)
        return CustomViewHolder(cellForRow)


    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {

        val player = homeFeed.athletes.get(p1)
        p0.view.textView_PlayerName.text = player.name
        val briefs = homeFeed.athletes.get(p1)
        p0.view.textView_Brief.text = briefs.brief
      val thumbnailImageView = p0.view.imageView
        if (isEmpty(homeFeed.athletes.get(p1).image)){
            thumbnailImageView.setImageResource(R.drawable.ic_launcher_background)
        }
        else {
            Picasso.get().load(homeFeed.athletes.get(p1).image).into(thumbnailImageView)

        }
        p0?.play   = player
        p0?.summ = briefs

    }
}



class CustomViewHolder(val view:View, var play: Athlete ?= null,var summ: Athlete ?= null) : RecyclerView.ViewHolder(view){
    companion object {
        val PLAYER_NAME_KEY = "playerName"
        val PLAYER_BRIEF_KEY = "brief"


    }

    init {
        view.setOnClickListener {
            val intent = Intent(view.context,CellDetails::class.java)
            intent.putExtra(PLAYER_NAME_KEY,play?.name)

            intent.putExtra(PLAYER_BRIEF_KEY,summ?.brief)
            view.context.startActivity(intent)
        }
    }


}
