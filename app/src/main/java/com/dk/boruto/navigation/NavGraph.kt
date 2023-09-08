package com.dk.boruto.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dk.boruto.presentation.screens.home.HomeScreen
import com.dk.boruto.presentation.screens.splash.SplashScreen
import com.dk.boruto.presentation.screens.welcome.WelcomeScreen
import com.dk.boruto.util.Constants.DETAILS_ARGUMENT_KEY

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ){
        composable(route = Screen.Splash.route){
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Welcome.route){
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Home.route){
            HomeScreen()
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(name = DETAILS_ARGUMENT_KEY){
                type = NavType.IntType
            })
        ){

        }
        composable(route = Screen.Search.route){

        }

    }
}