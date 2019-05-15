package com.example.vishwa.wordsearchchallenge.listeners

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.vishwa.wordsearchchallenge.R
import com.example.vishwa.wordsearchchallenge.model.CharGrid
import com.example.vishwa.wordsearchchallenge.model.WordPlacement
import com.google.android.flexbox.FlexboxLayout

/**
 * Created by Vishwa Patel
 */

class WordClickListener(private val context:Context) : View.OnClickListener {

    override fun onClick(v: View?) {

        // we want to find the word in the grid

        val textView = (v as FlexboxLayout).findViewById<TextView>(R.id.word)

        val highlightWord = textView.text.toString().toUpperCase()

        val (startIndex, wordPlacement) = CharGrid.getWordGridPosition(highlightWord)!!

        val (row, column) = CharGrid.getPosition(startIndex)

        val toastString = "Hint: ${textView.text.toString()}'s direction is ${wordPlacement.directionString}"

        Toast.makeText(context, toastString, Toast.LENGTH_LONG).show()

    }

}