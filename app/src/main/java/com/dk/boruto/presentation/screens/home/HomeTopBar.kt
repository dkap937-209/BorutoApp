package com.dk.boruto.presentation.screens.home

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dk.boruto.R
import com.dk.boruto.ui.theme.topAppBarBackgroundColor
import com.dk.boruto.ui.theme.topAppBarContentColor

//TODO: Dark mode not working for home and welcome screen but on splash screen
// That is because there is an explicit if statement for if it is in dark mode

@Composable
fun HomeTopBar(
    onSearchClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Explore",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            IconButton(
                onClick = onSearchClicked
            ){
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_heroes),
                    tint = MaterialTheme.colors.topAppBarContentColor
                )
            }
        }
    )
}

@Preview
@Composable
fun HomeTopBarLightPreview() {
    HomeTopBar{}
}

