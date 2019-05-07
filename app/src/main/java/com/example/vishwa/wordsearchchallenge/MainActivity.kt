package com.example.vishwa.wordsearchchallenge

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.Window
import android.widget.GridView
import com.example.vishwa.wordsearchchallenge.adapters.GridAdapter
import com.example.vishwa.wordsearchchallenge.model.CharElement
import com.example.vishwa.wordsearchchallenge.model.CharGrid
import com.example.vishwa.wordsearchchallenge.model.Words

class MainActivity : AppCompatActivity() {

    private var grid: CharGrid

    private val rows = 10
    private val columns = 10
    private val words: List<String> = arrayListOf("Swift", "Kotlin", "ObjectiveC", "Variable", "Java", "Mobile")

    private var wordsData: Words

    init {
        grid = CharGrid(rows, columns)

        wordsData = Words(words)

        // Add the words in the grid
        grid.addWords(words)

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        supportActionBar!!.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gridView = findViewById<GridView>(R.id.gridView)

        val charElements: List<List<CharElement>> = grid.getGrid()

        val gridAdapter = GridAdapter(this, charElements)

        gridView.adapter = gridAdapter

        gridView.numColumns = columns

        //gridView.setOn

        println("Size of grid is ${gridAdapter.count}")

    }
}
