package com.interactive.quizapp.utils.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.interactive.quizapp.ui.screens.categories.CategoriesScreen
import com.interactive.quizapp.ui.screens.dashboard.DashboardScreen
import com.interactive.quizapp.ui.screens.quiz.QuizScreen

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.DASHBOARD.route,
    ) {
        composable(NavigationItem.DASHBOARD.route) {
            DashboardScreen(navController)
        }
        composable(NavigationItem.CATEGORIES.route) {
            CategoriesScreen()
        }
        composable(NavigationItem.QUIZ.route) {
            QuizScreen(navController)
        }
    }
}