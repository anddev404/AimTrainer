package com.example.aimtrainer.auth.domain.model

data class BestScore(
    val uid: String = "",
    val score: Score = Score()
) {
    data class Score(
        val nick: String = "",
        val score: Int = 0,
        val time: Long = 0L
    )
}