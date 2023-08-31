package com.dk.boruto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dk.boruto.navigation.SetupNavGraph
import com.dk.boruto.ui.theme.BorutoTheme
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {


        AppCenter.start(
            application, "df7e485e-ab33-46ec-95e8-abac6bcba7b4",
            Analytics::class.java, Crashes::class.java
        )
        super.onCreate(savedInstanceState)
        setContent {
            BorutoTheme {

                navController = rememberNavController()
                SetupNavGraph(navController = navController)
                
            }
        }
    }
}