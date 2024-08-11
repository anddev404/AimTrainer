package com.example.aimtrainer.auth.data.remote

import com.example.aimtrainer.auth.domain.model.BestScore
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseDatabaseService {
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()

    fun getBestScores(result: (Result<List<BestScore>>) -> Unit) {

        val reference =
            database.getReference("Scores").orderByChild("score").limitToFirst(100)

        reference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val list = arrayListOf<BestScore>()

                for (s in snapshot.children) {

                    val uid = s.key ?: continue
                    val score = s.getValue(BestScore.Score::class.java) ?: continue

                    list.add(BestScore(uid, score))
                }
                list.sortBy { it.score.score }
                result(Result.success(list))
            }

            override fun onCancelled(error: DatabaseError) {
                result(Result.failure(error.toException()))
            }
        })
    }
}