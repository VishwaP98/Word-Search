package com.example.vishwa.wordsearchchallenge.listeners

import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import android.widget.GridView
import com.example.vishwa.wordsearchchallenge.callbacks.SwipeCallback
import com.example.vishwa.wordsearchchallenge.model.CharGrid
import com.example.vishwa.wordsearchchallenge.model.WordPlacement

/**
 * Created by Vishwa Patel
 */

class GridTouchListener(private val swipeCallback: SwipeCallback, private val gridSize : Pair<Int, Int>) : View.OnTouchListener {

    private var startX = -1
    private var startY = -1
    private var wordSelected : String = ""
    private var prevBlock : Int = 0
    private var direction: WordPlacement = WordPlacement.NONE

    private fun findGridBlock(x: Int, y:Int, view: View?): Int {

        // using the information about the grid, we can find out the block
        // that the user touched

        val gridView = view as GridView

        val rowHeight = gridView.height/gridSize.first - gridView.verticalSpacing

        var rowBlock = y / (rowHeight + gridView.verticalSpacing)
        var columnBlock = x / (gridView.columnWidth + gridView.horizontalSpacing)

        var colRem = x % (gridView.columnWidth + gridView.horizontalSpacing)
        var rowRem = y % (rowHeight + gridView.verticalSpacing)

        if (colRem > gridView.columnWidth) {
            return -1
        }

        if (rowRem > rowHeight) {
            return -1
        }

        return rowBlock * 10 + columnBlock

    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

        val y = event!!.y.toInt()
        val x = event.x.toInt()

        when(event.actionMasked) {

            ACTION_DOWN -> {
                // user pressed on the screen - marks beginning of a swipe

                // get the location of the event to save it as a start location
                startX = x
                startY = y

                prevBlock = findGridBlock(startX, startY, v)

                if(prevBlock == -1 || prevBlock > 100) {
                    return true // ignore the block for now
                }

                wordSelected = CharGrid.getCharAtBlock(prevBlock).toString()
                println("Word added is $wordSelected")
                println("Block selected is $prevBlock")

            }

            ACTION_MOVE -> {

                // Here we find the direction the user is moving the finger
                // and decide if it is a valid swipe or not

                if(direction == WordPlacement.NONE) {

                    // set the direction here

                    val currBlock = findGridBlock(x, y, v)

                    if(currBlock == (prevBlock + 1)) {  // to the right
                        direction = WordPlacement.RIGHT

                    } else if(currBlock == (prevBlock + 10)) { // down
                        direction = WordPlacement.DOWN

                    } else if(currBlock == (prevBlock + 11)) { // right diagonal down
                        direction = WordPlacement.RIGHT_DIAGONAL_DOWN

                    } else if(currBlock == (prevBlock - 9)) { // right diagonal up
                        direction = WordPlacement.RIGHT_DIAGONAL_UP

                    }

                    return true
                }

                // if the direction is already set, check if selection in correct direction

                val currBlock = findGridBlock(x, y, v)

                if(currBlock == prevBlock) {
                    return true // no need to process this
                }

                val invalidRight = direction == WordPlacement.RIGHT && currBlock != (prevBlock + 1)

                val invalidDown = direction == WordPlacement.DOWN && currBlock != (prevBlock + 10)

                val invalidRightDiagonalDown = direction == WordPlacement.RIGHT_DIAGONAL_DOWN && currBlock != (prevBlock + 11)

                val invalidRightDiagonalUp = direction == WordPlacement.RIGHT_DIAGONAL_UP && (currBlock != prevBlock - 9)

                if(invalidRight || invalidDown || invalidRightDiagonalDown || invalidRightDiagonalUp) {
                    println("prev block is $prevBlock and curr block is $currBlock")
                    return true
                }

                // otherwise the swipe is correct and so get the char from the block
                println("New char added is ${CharGrid.getCharAtBlock(currBlock)}")
                wordSelected += CharGrid.getCharAtBlock(currBlock)

                prevBlock = currBlock

            }

            ACTION_UP -> {
                // user release finger from the screen - marks end of a swipe

                swipeCallback.onWordSelected(wordSelected) // we are done

                // try to find if the word is contained in the grid


                direction = WordPlacement.NONE

                wordSelected = ""
            }

        }

        return true
    }


}