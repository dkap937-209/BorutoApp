package com.dk.boruto.presentation.screens.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.dk.boruto.domain.model.OnBoardingPage
import com.dk.boruto.ui.theme.welcomeScreenBackgroundColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(navController: NavHostController) {
    val pages = listOf(
        OnBoardingPage.Greetings,
        OnBoardingPage.Explore,
        OnBoardingPage.Power
    )

    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.welcomeScreenBackgroundColor)
    ){
        HorizontalPager(
            state = pagerState,
            count = pages.size,
            verticalAlignment = Alignment.Top
        ){page ->
            PagerScreen(onBoardingPage = pages[page])
        }
    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {

}