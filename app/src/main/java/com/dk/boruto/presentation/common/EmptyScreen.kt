package com.dk.boruto.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.dk.boruto.R
import com.dk.boruto.domain.model.Hero
import com.dk.boruto.ui.theme.DarkGray
import com.dk.boruto.ui.theme.LightGray
import com.dk.boruto.ui.theme.NETWORK_ERROR_ICON_HEIGHT
import com.dk.boruto.ui.theme.SMALL_PADDING
import com.dk.boruto.ui.theme.isLight
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.net.ConnectException
import java.net.SocketTimeoutException

private const val TAG = "EmptyScreen"

@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    heroes: LazyPagingItems<Hero>? = null
) {

    var message by remember{
        mutableStateOf("Find your Favourite Hero!")
    }
    var icon by remember {
        mutableIntStateOf(R.drawable.ic_search_document)
    }

    if(error != null){
        message = parseErrorMessage(error)
        icon = R.drawable.ic_network_error
    }

    var startAnimation by remember{
        mutableStateOf(false)
    }

    val alphaAnim by animateFloatAsState(
        targetValue = if(startAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(
           durationMillis = 1000
       )
    )

    LaunchedEffect(key1 = true){
        startAnimation = true
    }

    EmptyContent(
        alphaAnim = alphaAnim,
        icon = icon,
        message = message,
        heroes = heroes,
        error = error
    )
}

@Composable
fun EmptyContent(
    alphaAnim: Float,
    icon: Int,
    message: String,
    heroes: LazyPagingItems<Hero>? = null,
    error: LoadState.Error? = null
) {

    var isRefreshing by remember{
        mutableStateOf(false)
    }

    Log.d(TAG, "Error: $error")
    Log.d(TAG, "swipeEnabled: ${error != null}")


    SwipeRefresh(
        swipeEnabled = error != null,
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            isRefreshing = true
            heroes?.refresh()
            isRefreshing = false
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Icon(
                modifier = Modifier
                    .size(NETWORK_ERROR_ICON_HEIGHT)
                    .alpha(alpha = alphaAnim),
                painter = painterResource(id = icon),
                contentDescription = stringResource(R.string.network_error_icon),
                tint = if(MaterialTheme.colorScheme.isLight()) DarkGray else LightGray
            )
            Text(
                modifier = Modifier
                    .padding(top = SMALL_PADDING)
                    .alpha(alpha = alphaAnim),
                text = message,
                color = if(MaterialTheme.colorScheme.isLight()) DarkGray else LightGray,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = androidx.compose.material.MaterialTheme.typography.subtitle1.fontSize
            )
        }
    }

}

fun parseErrorMessage(error: LoadState.Error): String{
    Log.d(TAG, "Parse Error Message: $error")
    return when(error.error){
         is SocketTimeoutException -> {
            "Server Unavailable"
        }
        is ConnectException->{
            "Internet Unavailable"
        }
        else -> {
            "Unknown Error"
        }

    }
}

@Preview(showBackground = true)
@Composable
fun EmptyContentPreview() {
    EmptyContent(
        alphaAnim = 1f,
        icon = R.drawable.ic_network_error,
        message = "Internet Unavailable"
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun EmptyContentDarkPreview() {
    EmptyContent(
        alphaAnim = 1f,
        icon = R.drawable.ic_network_error,
        message = "Internet Unavailable"
    )
}