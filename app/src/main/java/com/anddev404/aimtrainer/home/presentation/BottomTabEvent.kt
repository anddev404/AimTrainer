package com.anddev404.aimtrainer.home.presentation

sealed class BottomTabEvent {
    data object OnSettingClick : BottomTabEvent()
    data object OnGameClick : BottomTabEvent()
    data object OnLevelClick : BottomTabEvent()
    data object OnRankClick : BottomTabEvent()
}