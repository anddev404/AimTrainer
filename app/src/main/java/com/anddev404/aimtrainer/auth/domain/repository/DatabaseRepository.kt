package com.anddev404.aimtrainer.auth.domain.repository

import com.anddev404.aimtrainer.auth.domain.model.BestScore

interface DatabaseRepository {

    fun getBestScores(result: (Result<List<BestScore>>) -> Unit)

    suspend fun insertScore(uid: String, score: BestScore.Score): Boolean
}