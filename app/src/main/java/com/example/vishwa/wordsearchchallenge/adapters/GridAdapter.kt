package com.example.vishwa.wordsearchchallenge.adapters

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.text.style.BackgroundColorSpan
import android.view.View
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.vishwa.wordsearchchallenge.R
import com.example.vishwa.wordsearchchallenge.model.CharElement

/**
 * Created by Vishwa Patel
 */

class GridAdapter(contextInput: Context, charElementsInput: List<List<CharElement>>) : BaseAdapter() {

    private val context: Context = contextInput
    private val charElements: List<List<CharElement>> = charElementsInput


    fun getItems() : List<List<CharElement>> {
        return charElements
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val charView = TextView(context)

        val arrayLength = charElements[0].size

        // Find which array

        val arrayNum = position / arrayLength
        val arrayPosition = position % arrayLength

        charView.text = charElements[arrayNum][arrayPosition].getValue().toString()
        charView.textSize = 15f
        charView.textAlignment = TEXT_ALIGNMENT_CENTER
        charView.setBackgroundResource(R.drawable.grid_block)

        return charView
    }

    override fun getItem(position: Int): Any {
        println("The position for this guy is $position")
        return charElements[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return charElements.size * charElements[0].size
    }

}