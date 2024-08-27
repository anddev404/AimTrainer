package com.anddev404.aimtrainer.rank.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anddev404.aimtrainer.auth.domain.model.BestScore
import com.anddev404.aimtrainer.auth.domain.repository.DatabaseRepository
import com.anddev404.aimtrainer.rank.domain.Score
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RankViewModel @Inject constructor(private val databaseRepository: DatabaseRepository) :
    ViewModel() {

    private val _bestScores = MutableStateFlow<List<BestScore>>(listOf())
    val bestScores: StateFlow<List<Score>> = _bestScores.map {

        it.mapIndexed { index, bestScore ->
            Score(index + 1, bestScore.score.nick, bestScore.score.score)
        }

    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        listOf()
    )

    init {
        databaseRepository.getBestScores { result ->
            result.onSuccess { list ->
                if (list.isNotEmpty()) {
                    _bestScores.value = list
                }
            }.onFailure {
            }
        }
    }
}