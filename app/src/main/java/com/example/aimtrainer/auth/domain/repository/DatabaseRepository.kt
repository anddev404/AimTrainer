package com.example.aimtrainer.auth.domain.repository

import com.example.aimtrainer.auth.domain.model.BestScore

interface DatabaseRepository {

    fun getBestScores(result: (Result<List<BestScore>>) -> Unit)
}