package com.interactive.quizapp.utils.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.interactive.quizapp.ui.screens.categories.CategoriesScreen
import com.interactive.quizapp.ui.screens.dashboard.DashboardScreen
import com.interactive.quizapp.ui.screens.history.HistoryScreen
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
            CategoriesScreen(navController)
        }
        composable(
            route = "${NavigationItem.QUIZ.route}?category={category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                    defaultValue = null
                    nullable = true
                }
            )
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")
            QuizScreen(navController, category)
        }
        composable(NavigationItem.HISTORY.route) {
            HistoryScreen(navController)
        }
    }
}