package com.example.vishwa.wordsearchchallenge.model

/**
 * Created by Vishwa Patel
 */

enum class WordPlacement(val verticalStep: Int, val horizontalStep: Int) {
    RIGHT(0, 1),
    DOWN(1,0),
    RIGHT_DIAGONAL_UP(-1,1),
    RIGHT_DIAGONAL_DOWN(1, 1)
}