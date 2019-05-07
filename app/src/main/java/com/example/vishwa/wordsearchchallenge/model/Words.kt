package com.example.vishwa.wordsearchchallenge.model

/**
 * Created by Vishwa Patel
 */

data class Words(var inputWords: List<String>) {

    private var wordsMap: MutableMap<String, Boolean> = mutableMapOf()

    init {

        for (word in inputWords) {
            wordsMap[word] = false
        }

    }

    fun containsWord(checkWord: String) : Boolean? {

        if(!wordsMap.containsKey(checkWord)) {
            return false
        }

        return wordsMap[checkWord]

    }

    fun isWordFound(word: String) : Boolean? {

        if(!wordsMap.containsKey(word)) {
            throw IllegalArgumentException("Word not present in the grid")
        }

        return wordsMap[word]
    }

    fun markWordFound(word: String) {

        if(!wordsMap.containsKey(word)) {
            throw IllegalArgumentException("Word not present in the grid")
        }

        wordsMap[word] = true

    }


}