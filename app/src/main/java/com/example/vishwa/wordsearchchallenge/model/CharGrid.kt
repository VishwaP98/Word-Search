package com.example.vishwa.wordsearchchallenge.model

import android.graphics.Color
import android.widget.GridView
import java.util.*
/**
 * Created by Vishwa Patel
 */

class CharGrid(rowsInput: Int, columnsInput: Int) {

    /* Our grid should be at least 10x10 */
    private val rows: Int = rowsInput
    private val columns: Int = columnsInput

    init {

        if(rows < 10 || columns < 10) {
            throw IllegalArgumentException("Invalid rows or columns (must be >= 10)")
        }

        // If input is fine, we should start by initializing the grid

        for (currRow in 0..(rows-1)) { // 0..val -> val is included which we don't want

            var list = ArrayList<CharElement>()

            for(currCol in 0..(columns - 1)) {

                var charElement = CharElement(false)

                list.add(charElement)
            }

            grid.add(list)

        }
    }

    fun getGrid() : List<List<CharElement>> {
        return grid
    }

    fun addWords(words: List<String>) : Boolean {

        // let's sort the words in decreasing order because it will be harder
        // to add longer words later on

        var sortedWords = words.sortedWith(kotlin.Comparator { word1, word2 -> -1 * (word1.length - word2.length) })

        println("words are $sortedWords")

        for (word in sortedWords) {
            println("Word is $word")

            if(!addWord(word)) {
                return false
            }
        }

        return true
    }

    /*
        Add logic to add a word in the grid
        For that need to find a suitable place

        For now, let's try to have all the words horizontally
        one on each row
     */

    private fun addWord(word: String) : Boolean {

        if(word.length > columns) {
            throw IllegalArgumentException("Word length more than storage limit")
        }

        return findPossiblePlacements(word)
    }

    private fun findPossiblePlacements(word: String) : Boolean {

        /* Goal is to find all possible places where we can place this word
           in the grid
        */

        val start = 0 // start at the top right corner of the grid

        val possiblePlacesList: ArrayList<Pair<Int, WordPlacement>> = arrayListOf()

        val visitedMap : MutableMap<Int, Boolean> = mutableMapOf()

        // let's do a BFS on the grid to find possible placements
        val queue: Queue<Int> = PriorityQueue<Int>()

        val wordLength = word.length

        queue.add(start)

        while(!queue.isEmpty()) { // queue is not empty

            val currIndex = queue.remove()

            val (currIndexRow, currIndexCol) = getPosition(currIndex)

            // check in all directions

            if(currIndexCol + wordLength - 1 < columns && findEmptySpaces(currIndex, WordPlacement.RIGHT, wordLength)) {
                possiblePlacesList.add(Pair(currIndex, WordPlacement.RIGHT))
            }

            if(currIndexRow + wordLength - 1 < rows && findEmptySpaces(currIndex, WordPlacement.DOWN, wordLength)) {
                possiblePlacesList.add(Pair(currIndex, WordPlacement.DOWN))
            }

            if(currIndexCol + wordLength - 1 < columns && currIndexRow - wordLength + 1 >= 0 &&
                    findEmptySpaces(currIndex, WordPlacement.RIGHT_DIAGONAL_UP, wordLength)) {
                possiblePlacesList.add(Pair(currIndex, WordPlacement.RIGHT_DIAGONAL_UP))
            }

            if(currIndexCol + wordLength - 1 < columns && currIndexRow + wordLength - 1 < rows &&
                    findEmptySpaces(currIndex, WordPlacement.RIGHT_DIAGONAL_DOWN, wordLength)) {
                possiblePlacesList.add(Pair(currIndex, WordPlacement.RIGHT_DIAGONAL_DOWN))
            }

            if((!visitedMap.containsKey(currIndex + 1) || !visitedMap[currIndex + 1]!!)
                && currIndex + 1 < rows * columns) {
                queue.add(currIndex + 1) // element to right
                visitedMap[currIndex + 1] = true
            }

            if((!visitedMap.containsKey(currIndex + 10) || !visitedMap[currIndex + 10]!!)
                && currIndex + 10 < rows * columns) {
                queue.add(currIndex + 10) // element beneath current one
                visitedMap[currIndex + 10] = true
            }

            if((!visitedMap.containsKey(currIndex + 10 + 1) || !visitedMap[currIndex + 10 + 1]!!)
                && currIndex + 10 + 1 < rows * columns) {
                queue.add(currIndex + 10 + 1) // element to diagonal down
                visitedMap[currIndex + 10 + 1] = true
            }

        }

        // Let's generate a number between 0 and randomRange - 1 to randomly place our word
        println("possibilities size is ${possiblePlacesList.size}")

        val randomStarts = (0 until possiblePlacesList.size).random()

        println("Randomly selected start is $randomStarts in range ${possiblePlacesList.size}")

        storedWords[word.toUpperCase()] = possiblePlacesList[randomStarts] // store the word in map for future use

        placeWord(word, possiblePlacesList[randomStarts].first, possiblePlacesList[randomStarts].second)

        return possiblePlacesList.size > 0
    }

    private fun placeWord(word: String, start: Int, placement: WordPlacement) {

        var index = start
        for(char in word) {

            val (arrayNum, offset) = getPosition(index)

            grid[arrayNum][offset].setValue(char.toUpperCase()) // sets the element in grid to use

            index += 10 * placement.verticalStep + placement.horizontalStep
        }

    }

    private fun findEmptySpaces(start:Int, placement: WordPlacement, length:Int) : Boolean {
        // return true if we have empty spaces in the direction and length given

        var block = start

        for (index in 0..(length-1)) {

            if(block >= rows * columns || block < 0) {
                return false
            }

            val (arrayNum, offset) = getPosition(block)

            if(grid[arrayNum][offset].getUseStatus()) {
                return false
            }

            block += (10 * placement.verticalStep) + placement.horizontalStep// next block

        }

        // if we reach the end, then we have found empty spaces
        return true
    }

    companion object {
        private var grid = ArrayList<ArrayList<CharElement>>()

        private val storedWords: MutableMap<String, Pair<Int, WordPlacement>> = mutableMapOf()

        @JvmStatic
        fun gridContainsWord(word : String) : Boolean {

            return storedWords.containsKey(word)
        }

        @JvmStatic
        fun getWordGridPosition(word : String) : Pair<Int, WordPlacement>? {

            if(gridContainsWord(word.toUpperCase())) {
                return storedWords[word.toUpperCase()]

            }

            return null

        }

        @JvmStatic
        fun highlightWordInGrid(gridView: GridView, word : String) : Boolean {

            if(!storedWords.containsKey(word)) {
                return false
            }

            println("In highlighting here ")

            val (startIndex, direction) = Pair(storedWords[word]!!.first, storedWords[word]!!.second)

            var currIndex = startIndex

            for(char in word) {

                gridView.getChildAt(currIndex).setBackgroundColor(Color.YELLOW)

                currIndex += 10 * direction.verticalStep + direction.horizontalStep

            }

            return true
        }


        @JvmStatic
        fun getCharAtBlock(block : Int) : Char {

            val (currIndexRow, currIndexCol) = getPosition(block)

            return grid[currIndexRow][currIndexCol].getValue()
        }

        @JvmStatic
        fun getPosition(index: Int) : Pair<Int,Int> {

            val arrayNum = index / grid.size
            val offset = index % grid.size

            return Pair(arrayNum, offset)
        }
    }

}