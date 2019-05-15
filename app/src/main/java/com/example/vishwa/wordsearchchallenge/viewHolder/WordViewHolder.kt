package com.example.vishwa.wordsearchchallenge.viewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.vishwa.wordsearchchallenge.R

/**
 * Created by Vishwa Patel
 */

class WordViewHolder(item:View) : RecyclerView.ViewHolder(item) {

    private val wordTextView : TextView = item.findViewById(R.id.word)

    fun getWordTextView() : TextView {
        return wordTextView
    }

}