package com.anddev404.aimtrainer.core.presentation.data.repository

import android.content.SharedPreferences
import com.anddev404.aimtrainer.core.presentation.domain.repository.SharedPreferenceRepository

class SharedPreferencesRepositoryImpl(private val sharedPreferences: SharedPreferences) :
    SharedPreferenceRepository {

    override fun getBestScore(): Int {
        return sharedPreferences.getInt("bestScore", 0)
    }

    override fun setBestScore(score: Int, timeInMs: Long) {
        val editor = sharedPreferences.edit()

        editor.putInt("bestScore", score)
        editor.putLong("bestScore_time", timeInMs)
        editor.putBoolean("isScoreSent", false)

        editor.apply()
    }

    override fun isScoreSentToServer(): Boolean {
        return sharedPreferences.getBoolean("isScoreSent", false)
    }

    override fun setScoreSentToServer() {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isScoreSent", true)
        editor.apply()
    }
}