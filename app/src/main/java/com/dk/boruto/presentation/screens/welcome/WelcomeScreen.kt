package com.dk.boruto.presentation.screens.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dk.boruto.R
import com.dk.boruto.domain.model.OnBoardingPage
import com.dk.boruto.navigation.Screen
import com.dk.boruto.ui.theme.EXTRA_LARGE_PADDING
import com.dk.boruto.ui.theme.PAGING_INDICATOR_SPACING
import com.dk.boruto.ui.theme.PAGING_INDICATOR_WIDTH
import com.dk.boruto.ui.theme.SMALL_PADDING
import com.dk.boruto.ui.theme.activeIndicatorColor
import com.dk.boruto.ui.theme.buttonBackgroundColor
import com.dk.boruto.ui.theme.descriptionColor
import com.dk.boruto.ui.theme.inactiveIndicatorColor
import com.dk.boruto.ui.theme.titleColor
import com.dk.boruto.ui.theme.welcomeScreenBackgroundColor
import com.dk.boruto.util.Constants.LAST_ONBOARDING_PAGE
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val pages = listOf(
        OnBoardingPage.Greetings,
        OnBoardingPage.Explore,
        OnBoardingPage.Power
    )

    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.welcomeScreenBackgroundColor),
    ){
        HorizontalPager(
            modifier = Modifier
                .weight(10f),
            state = pagerState,
            count = pages.size,
            verticalAlignment = Alignment.Top
        ){position ->
            PagerScreen(onBoardingPage = pages[position])
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .weight(1f)
                .align(CenterHorizontally),
            pagerState = pagerState,
            activeColor = MaterialTheme.colors.activeIndicatorColor,
            inactiveColor = MaterialTheme.colors.inactiveIndicatorColor,
            indicatorWidth = PAGING_INDICATOR_WIDTH,
            spacing = PAGING_INDICATOR_SPACING
        )

        FinishButton(
            modifier = Modifier
                .weight(1f),
            pagerState = pagerState,
            onClick = {
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
                welcomeViewModel.saveOnBoardingState(completed = true)
            }
        )
    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ){
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = stringResource(R.string.on_boarding_image)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = onBoardingPage.title,
            color = MaterialTheme.colors.titleColor,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = SMALL_PADDING),
            text = onBoardingPage.description,
            color = MaterialTheme.colors.descriptionColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: ()-> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = EXTRA_LARGE_PADDING),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ){
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == LAST_ONBOARDING_PAGE
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                    contentColor = Color.White
                )
            ){
                Text(text = "Finish")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingsOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()){
        PagerScreen(onBoardingPage = OnBoardingPage.Greetings)
    }
}

@Preview(showBackground = true)
@Composable
fun ExploreOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()){
        PagerScreen(onBoardingPage = OnBoardingPage.Explore)
    }
}

@Preview(showBackground = true)
@Composable
fun PowerOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()){
        PagerScreen(onBoardingPage = OnBoardingPage.Power)
    }
}