package com.interactive.quizapp.utils.routes

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import com.interactive.quizapp.utils.Args

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.DASHBOARD.route,
    ) {
        composable(
            route = NavigationItem.DASHBOARD.route,
            content = { DashboardScreen(navController) }
        )
        composable(
            route = NavigationItem.CATEGORIES.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(300))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300))
            },
            content = { CategoriesScreen(navController) }
        )
        composable(
            route = NavigationItem.QUIZ.route,
            arguments = listOf(
                navArgument(Args.CATEGORY) {
                    type = NavType.StringType
                    defaultValue = null
                    nullable = true
                },
                navArgument(Args.IS_FROM_HISTORY) {
                    type = NavType.BoolType
                    defaultValue = false
                }
            ),
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(300))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300))
            },
            content = { QuizScreen(navController) }
        )
        composable(
            route = NavigationItem.HISTORY.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(300))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300))
            },
            content = { HistoryScreen(navController) }
        )
    }
}