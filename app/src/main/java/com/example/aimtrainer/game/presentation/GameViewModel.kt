package com.example.aimtrainer.game.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel : ViewModel() {

    private var startTime = 0L
    private var endTime = 0L
    private var lastScore = 0L

    private val _state = MutableStateFlow<GameState>(GameState.Loading)
    val state = _state.asStateFlow()

    init {
        startGame()
    }

    fun startGame() {
        _state.value = GameState.Playing
    }

    fun setStartTime() {
        startTime = System.currentTimeMillis()
    }

    fun setEndTime() {
        endTime = System.currentTimeMillis()
        lastScore = endTime - startTime
        _state.value = GameState.Ended
    }

    fun loseGame() {
        _state.value = GameState.Lost
    }

    fun getScore(): Long {
        return lastScore
    }
}