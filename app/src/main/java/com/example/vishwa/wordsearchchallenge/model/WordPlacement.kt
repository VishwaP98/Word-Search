package com.example.vishwa.wordsearchchallenge.model

/**
 * Created by Vishwa Patel
 */

enum class WordPlacement(val verticalStep: Int, val horizontalStep: Int, val directionString: String) {
    RIGHT(0, 1, "Right"),
    DOWN(1,0, "Down"),
    RIGHT_DIAGONAL_UP(-1,1, "Right Diagonal Up"),
    RIGHT_DIAGONAL_DOWN(1, 1, "Right Diagonal Down"),
    NONE(-1,-1, "None")
}