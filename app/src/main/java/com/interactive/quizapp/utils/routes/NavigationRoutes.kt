package com.interactive.quizapp.utils.routes

import com.interactive.quizapp.utils.Args

enum class Screen {
    DASHBOARD,
    CATEGORIES,
    QUIZ,
    HISTORY
}

sealed class NavigationItem(val route: String) {
    data object DASHBOARD: NavigationItem(Screen.DASHBOARD.name)
    data object CATEGORIES: NavigationItem(Screen.CATEGORIES.name)
    data object QUIZ: NavigationItem(Screen.QUIZ.name + "?${Args.CATEGORY}={${Args.CATEGORY}}&${Args.IS_FROM_HISTORY}={${Args.IS_FROM_HISTORY}}") {
        fun createRoute(category: String? = null, isFromHistory: Boolean = false): String {
            return Screen.QUIZ.name + "?${Args.CATEGORY}=$category&${Args.IS_FROM_HISTORY}=$isFromHistory"
        }
    }
    data object HISTORY: NavigationItem(Screen.HISTORY.name)
}