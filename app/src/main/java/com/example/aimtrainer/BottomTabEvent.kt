package com.example.aimtrainer

sealed class BottomTabEvent {
    data object OnSettingClick : BottomTabEvent()
    data object OnGameClick : BottomTabEvent()
    data object OnLevelClick : BottomTabEvent()
    data object OnRankClick : BottomTabEvent()
}