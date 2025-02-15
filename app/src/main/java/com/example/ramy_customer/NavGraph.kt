package com.example.ramy_customer



import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Camera : Screen("camera/{supermarketName}")
    object Success : Screen("success")  // Changed from Results to Success
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
                        Screen.Camera.route.replace("{supermarketName}", supermarketName)
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
                supermarketName = supermarketName,
                onImageCaptured = {
                    navController.navigate(Screen.Success.route) {  // Changed to navigate to Success
                        popUpTo(Screen.Camera.route) { inclusive = true }
                    }
                },
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

        composable(Screen.Success.route) {  // Changed from Results to Success
            SuccessScreen(  // Added navigation parameters
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
                onLeaderboardClick = {
                    // Already on leaderboard, no action needed
                },
                onProfileClick = { navController.navigate(Screen.Profile.route) }
            )
        }

        composable(Screen.Profile.route) {
            ProfileHome(  // Changed from Profilemarch to ProfileHome
                onHomeClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onLeaderboardClick = { navController.navigate(Screen.Leaderboard.route) },
                onProfileClick = {
                    // Already on profile, no action needed
                }
            )
        }
    }
}