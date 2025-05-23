package com.interactive.quizapp.utils.routes

enum class Screen {
    DASHBOARD,
    CATEGORIES,
    QUIZ,
    HISTORY
}

sealed class NavigationItem(val route: String) {
    data object DASHBOARD: NavigationItem(Screen.DASHBOARD.name)
    data object CATEGORIES: NavigationItem(Screen.CATEGORIES.name)
    data object QUIZ: NavigationItem(Screen.QUIZ.name)
    data object HISTORY: NavigationItem(Screen.HISTORY.name)
}