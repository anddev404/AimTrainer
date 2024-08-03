package com.example.aimtrainer.game.presentation

sealed class GameState {
    data object Loading : GameState()
    data object Playing : GameState()
    data object Ended : GameState()
    data object Lost : GameState()
}