package com.example.vishwa.wordsearchchallenge.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.vishwa.wordsearchchallenge.R
import com.example.vishwa.wordsearchchallenge.listeners.WordClickListener
import com.example.vishwa.wordsearchchallenge.viewHolder.WordViewHolder


/**
 * Created by Vishwa Patel
 */

class WordsAdapter(private val context: Context, private val layoutInflater: LayoutInflater,
                   private val words: List<String>, private val wordsFound: ArrayList<String>) : RecyclerView.Adapter<WordViewHolder>() {

    fun updateWordsFound(wordFound: String) {
        wordsFound.add(wordFound)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(p0: WordViewHolder, p1: Int) {

        if(words[p1].toUpperCase() in wordsFound) {
            p0.getWordTextView().paintFlags = (p0.getWordTextView().paintFlags or Paint.STRIKE_THRU_TEXT_FLAG)
        }

        p0.getWordTextView().text = words[p1]
        p0.getWordTextView().textSize = 25f
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): WordViewHolder {

        val view = layoutInflater.inflate(R.layout.word_item, p0, false)

        view.setOnClickListener(WordClickListener(context))

        return WordViewHolder(view)

    }

    override fun getItemCount(): Int {
        return words.size
    }

}