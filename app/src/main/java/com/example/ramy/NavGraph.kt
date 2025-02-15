package com.example.ramy

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Camera : Screen("camera/{supermarketName}")
    object Results : Screen("results/{supermarketName}")
    object Leaderboard : Screen("leaderboard")
    object Profile : Screen("profile")

    fun createRoute(vararg args: String): String {
        var tempRoute = route
        args.forEach { arg ->
            tempRoute = tempRoute.replaceFirst("{${arg.substringBefore("=")}}", arg)
        }
        return tempRoute
    }
}

@Composable
fun NavGraph(navController: NavHostController) {
    val viewModel: ProductDetectionViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onGetStartedClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onSupermarketClick = { supermarketName ->
                    navController.navigate(
                        Screen.Camera.createRoute("supermarketName=$supermarketName")
                    )
                },
                onLeaderboardClick = {
                    navController.navigate(Screen.Leaderboard.route)
                },
                onProfileClick = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }

        composable(
            route = Screen.Camera.route,
            arguments = listOf(navArgument("supermarketName") { type = NavType.StringType })
        ) { backStackEntry ->
            val supermarketName = backStackEntry.arguments?.getString("supermarketName") ?: ""
            CameraPage(
                viewModel = viewModel,
                supermarketName = supermarketName,
                onImageCaptured = {
                    navController.navigate(
                        Screen.Results.createRoute("supermarketName=$supermarketName")
                    )
                },
                onBackClick = { navController.popBackStack() },
                onHomeClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onLeaderboardClick = { navController.navigate(Screen.Leaderboard.route) },
                onProfileClick = { navController.navigate(Screen.Profile.route) },
                onNotificationsClick = { /* Handle notifications */ }
            )
        }

        composable(
            route = Screen.Results.route,
            arguments = listOf(navArgument("supermarketName") { type = NavType.StringType })
        ) { backStackEntry ->
            val supermarketName = backStackEntry.arguments?.getString("supermarketName") ?: ""
            Results(
                viewModel = viewModel,
                supermarketName = supermarketName,
                onBackClick = { navController.popBackStack() },
                onHomeClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onLeaderboardClick = { navController.navigate(Screen.Leaderboard.route) },
                onProfileClick = { navController.navigate(Screen.Profile.route) }
            )
        }

        composable(Screen.Leaderboard.route) {
            Classement(
                onHomeClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onLeaderboardClick = { /* Already on leaderboard */ },
                onProfileClick = { navController.navigate(Screen.Profile.route) }
            )
        }

        composable(Screen.Profile.route) {
            Profilemarch(
                onHomeClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onLeaderboardClick = { navController.navigate(Screen.Leaderboard.route) },
                onProfileClick = { /* Already on profile */ }
            )
        }
    }
}