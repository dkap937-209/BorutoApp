package com.dk.boruto.presentation.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.dk.boruto.domain.model.Hero
import com.dk.boruto.navigation.Screen
import com.dk.boruto.presentation.common.HeroItem
import com.dk.boruto.presentation.common.HeroItemPreview
import com.dk.boruto.presentation.common.ListContent
import com.dk.boruto.ui.theme.LARGE_PADDING
import com.dk.boruto.ui.theme.statusBarColour
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val TAG = "HomeScreen"
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColour
    )

    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClicked = {
                    navController.navigate(Screen.Search.route)
                }
            )
        }
    ){  paddingValues ->

        Box(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding().plus(20.dp))
        ){
            ListContent(
                modifier = Modifier
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    ),
                heroes = allHeroes,
                navController = navController
            )
        }
    }
}

