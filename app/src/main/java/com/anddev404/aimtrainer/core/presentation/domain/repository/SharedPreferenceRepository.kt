package com.anddev404.aimtrainer.core.presentation.domain.repository

interface SharedPreferenceRepository {

    fun getBestScore(): Int
    fun setBestScore(score: Int, timeInMs: Long)
    fun isScoreSentToServer(): Boolean
    fun setScoreSentToServer()
}