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
    data object QUIZ: NavigationItem(Screen.QUIZ.name + "?category={category}&is_from_history={is_from_history}") {
        fun createRoute(category: String? = null, isFromHistory: Boolean = false): String {
            return Screen.QUIZ.name + "?category=$category&is_from_history=$isFromHistory"
        }
    }
    data object HISTORY: NavigationItem(Screen.HISTORY.name)
}