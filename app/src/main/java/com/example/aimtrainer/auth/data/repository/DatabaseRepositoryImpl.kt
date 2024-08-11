package com.example.aimtrainer.auth.data.repository

import com.example.aimtrainer.auth.data.remote.FirebaseDatabaseService
import com.example.aimtrainer.auth.domain.model.BestScore
import com.example.aimtrainer.auth.domain.repository.DatabaseRepository

class DatabaseRepositoryImpl(private val firebaseDatabaseService: FirebaseDatabaseService) :
    DatabaseRepository {

    override fun getBestScores(result: (Result<List<BestScore>>) -> Unit) {
        firebaseDatabaseService.getBestScores {
            result(it)
        }
    }
}