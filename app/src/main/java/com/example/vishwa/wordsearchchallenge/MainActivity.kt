package com.example.vishwa.wordsearchchallenge

import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.Window
import android.widget.GridView
import android.widget.Toast
import com.example.vishwa.wordsearchchallenge.adapters.GridAdapter
import com.example.vishwa.wordsearchchallenge.adapters.WordsAdapter
import com.example.vishwa.wordsearchchallenge.callbacks.SwipeCallback
import com.example.vishwa.wordsearchchallenge.listeners.GridTouchListener
import com.example.vishwa.wordsearchchallenge.model.CharElement
import com.example.vishwa.wordsearchchallenge.model.CharGrid
import com.example.vishwa.wordsearchchallenge.model.Words
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class MainActivity : AppCompatActivity(), SwipeCallback {

    override fun onInvalidWordSelected() {
        println("Invalid selection made")
        Toast.makeText(this, "Invalid selection made", Toast.LENGTH_LONG).show()

    }

    override fun onWordSelected(word: String) {
        println("The word selected by user is $word")
        Toast.makeText(this, "Word Selected is $word", Toast.LENGTH_LONG).show()

        if(CharGrid.gridContainsWord(word)) {

            CharGrid.highlightWordInGrid(gridView, word)

            wordsAdapter.updateWordsFound(word)

        }
    }

    private var grid: CharGrid

    private val rows = 10
    private val columns = 10
    private val words: List<String> = arrayListOf("Swift", "Kotlin", "Go",
                                                "ObjectiveC", "Variable", "Java", "Mobile", "Ruby")

    private var wordsData: Words

    private lateinit var gridView: GridView

    private lateinit var wordsAdapter: WordsAdapter

    private lateinit var gridAdapter: GridAdapter

    init {
        grid = CharGrid(rows, columns)

        wordsData = Words(words)

        // Add the words in the grid
        grid.addWords(words)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        supportActionBar!!.hide()

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        gridView = findViewById(R.id.gridView)

        val charElements: List<List<CharElement>> = grid.getGrid()

        gridAdapter = GridAdapter(this, charElements)

        gridView.adapter = gridAdapter

        gridView.numColumns = columns

        gridView.setOnTouchListener(GridTouchListener(this, Pair(rows, columns)))

        println("Size of grid is ${gridAdapter.count}")


        val recyclerView = findViewById<RecyclerView>(R.id.wordList)

        val flexBoxLayoutManager = FlexboxLayoutManager(this)

        flexBoxLayoutManager.flexDirection = FlexDirection.ROW

        flexBoxLayoutManager.justifyContent = JustifyContent.CENTER

        flexBoxLayoutManager.alignItems = AlignItems.CENTER

        recyclerView.layoutManager = flexBoxLayoutManager

        wordsAdapter = WordsAdapter(this, this.layoutInflater, words, arrayListOf())

        recyclerView.adapter = wordsAdapter

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        // save the gridView

 //       outState.putParcelableArrayList("adapter", gridAdapter.getItems())

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
    }

}
