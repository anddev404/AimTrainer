package com.example.aimtrainer.rank.domain

data class Score(val position: Int, val nick: String, val score: Int) {

    companion object {
        fun getFakeScore(): List<Score> {
            return List(30) {
                Score(it + 1, "Player $it", 100 - it)
            }
        }
    }
}



