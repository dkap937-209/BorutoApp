package com.dk.boruto.presentation.screens.search

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun SearchScreen() {
    Scaffold(
        topBar = {
            SearchTopBar(
                text = "",
                onTextChange = {},
                onSearchClicked = {},
                onClosedClicked = {}
            )
        }
    ){}
}