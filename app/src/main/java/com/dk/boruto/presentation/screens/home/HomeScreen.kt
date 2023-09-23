package com.dk.boruto.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val allHeroes = homeViewModel.getAllHeroes.invoke().collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = {})
        }
    ){

    }
}