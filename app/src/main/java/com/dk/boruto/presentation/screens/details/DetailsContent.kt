package com.dk.boruto.presentation.screens.details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.dk.boruto.R
import com.dk.boruto.domain.model.Hero
import com.dk.boruto.presentation.components.InfoBox
import com.dk.boruto.presentation.components.OrderedList
import com.dk.boruto.ui.theme.EXPANDED_RADIUS_LEVEL
import com.dk.boruto.ui.theme.EXTRA_LARGE_PADDING
import com.dk.boruto.ui.theme.INFO_ICON_SIZE
import com.dk.boruto.ui.theme.LARGE_PADDING
import com.dk.boruto.ui.theme.MEDIUM_PADDING
import com.dk.boruto.ui.theme.MIN_SHEET_HEIGHT
import com.dk.boruto.ui.theme.SMALL_PADDING
import com.dk.boruto.ui.theme.titleColor
import com.dk.boruto.util.Constants.ABOUT_TEXT_MAX_LINES
import com.dk.boruto.util.Constants.BASE_URL
import com.dk.boruto.util.Constants.MINIMUM_BACKGROUND_IMAGE

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun DetailsContent(
    navController: NavHostController,
    selectedHero: Hero?
) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction
    val radiusAnim by animateDpAsState(
        targetValue = if(currentSheetFraction == 1f) EXTRA_LARGE_PADDING
                      else EXPANDED_RADIUS_LEVEL
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedHero?.let{
                BottomSheetContent(selectedHero = it)
            }
        },
        content = {
            selectedHero?.let { it1 ->
                BackgroundContent(
                    heroImage = it1.image,
                    imageFraction = currentSheetFraction,
                    onCloseClicked = {
                        navController.popBackStack()
                    }
                )
            }
        }
    )
}

@Composable
fun BottomSheetContent(
    selectedHero: Hero,
    infoBoxIconColour: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                modifier = Modifier
                    .size(INFO_ICON_SIZE)
                    .weight(2f),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(id = R.string.app_logo),
                tint = contentColor
            )
            Text(
                modifier = Modifier
                    .weight(8f),
                text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            InfoBox(
                icon = painterResource(id = R.drawable.ic_bolt),
                iconColor = infoBoxIconColour,
                bigText = "${selectedHero.power}",
                smallText = stringResource(R.string.power),
                textColor = contentColor
            )

            InfoBox(
                icon = painterResource(id = R.drawable.ic_calendar),
                iconColor = infoBoxIconColour,
                bigText = selectedHero.month,
                smallText = stringResource(R.string.month),
                textColor = contentColor
            )

            InfoBox(
                icon = painterResource(id = R.drawable.ic_cake),
                iconColor = infoBoxIconColour,
                bigText = selectedHero.day,
                smallText = stringResource(R.string.birthday),
                textColor = contentColor
            )

        }

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(R.string.about),
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = selectedHero.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = ABOUT_TEXT_MAX_LINES
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            OrderedList(
                title = stringResource(R.string.family),
                items = selectedHero.family,
                textColor = contentColor
            )
            OrderedList(
                title = stringResource(R.string.abilities),
                items = selectedHero.abilities,
                textColor = contentColor
            )
            OrderedList(
                title = stringResource(R.string.nature_types),
                items = selectedHero.natureTypes,
                textColor = contentColor
            )
        }


    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun BackgroundContent(
    heroImage: String,
    imageFraction: Float,
    backgroundColour: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit
) {

    val imageUrl = "$BASE_URL${heroImage}"
    val painter = rememberImagePainter(imageUrl){
        error(R.drawable.ic_placeholder)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColour)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + MINIMUM_BACKGROUND_IMAGE)
                .align(TopStart),
            painter = painter,
            contentDescription = stringResource(R.string.hero_image),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier
                    .padding(all = SMALL_PADDING),
                onClick = {
                    onCloseClicked()
                }
            ) {

                Icon(
                    modifier = Modifier
                        .size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close_detail_screen),
                    tint = Color.White
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
val BottomSheetScaffoldState.currentSheetFraction: Float
    get(){
        val fraction = bottomSheetState.progress
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
//            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> MINIMUM_BACKGROUND_IMAGE
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 1f + fraction
            else -> fraction
        }
    }

@Preview
@Composable
fun BottomSheetContentPreview() {
    BottomSheetContent(
        selectedHero = Hero(
            id = 1,
            name = "Sasuke",
            image = "",
            about = "Description here",
            rating = 4.4,
            power  = 100,
            month = "5",
            day = "14",
            family = emptyList(),
            abilities = emptyList(),
            natureTypes = emptyList()
        )
    )

}

