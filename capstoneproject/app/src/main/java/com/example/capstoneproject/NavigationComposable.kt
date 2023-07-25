package com.example.capstoneproject

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationComposable(sharedPreferences: SharedPreferences) {
    val navController = rememberNavController()
    var startDestination = OnBoarding.route

    if (sharedPreferences.getBoolean("userRegistered", false)) {
        startDestination = Home.route
    }
    NavHost(navController = navController, startDestination = startDestination){
        composable(OnBoarding.route){
            Onboarding(sharedPreferences = sharedPreferences, navController = navController)
        }
        composable(Home.route){
            HomeScreen(navController = navController)
        }
        composable(Profile.route){
            ProfileScreen(navController = navController, sharedPreferences = sharedPreferences)
        }
    }

}