package com.example.vishwa.wordsearchchallenge.model

/**
 * Created by Vishwa Patel
 */

data class CharElement(val initUse: Boolean) {

    private var useStatus:Boolean = initUse /* Checks whether we can use this element or not */

    private var value: Char = (65 + (0 until 26).random()).toChar()

    fun getUseStatus(): Boolean {
        return useStatus
    }

    fun getValue(): Char {
        return value
    }

    fun setValue(newValue: Char) {

        this.value = newValue
        this.useStatus = true
    }

    fun updateInUseStatus(newStatus: Boolean) {
        this.useStatus = newStatus
    }

}