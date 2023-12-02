package com.dk.boruto.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dk.boruto.util.Constants.BASE_URL
import com.dk.boruto.util.PaletteGenerator.convertImageUrlToBitmap
import com.dk.boruto.util.PaletteGenerator.extractColoursFromBitmap
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DetailsScreen(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {

    val selectedHero by detailsViewModel.selectedHero.collectAsState()
    val colourPalette by detailsViewModel.colourPalette

    if(colourPalette.isNotEmpty()){
        DetailsContent(
            navController = navController,
            selectedHero = selectedHero,
            colours = colourPalette
        )
    }
    else{
        detailsViewModel.generateColourPalette()
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = true){
        detailsViewModel.uiEvent.collectLatest {event ->
            when(event){
                is UiEvent.GenerateColourPalette -> {
                    val bitmap = convertImageUrlToBitmap(
                        imageUrl = "$BASE_URL${selectedHero?.image}",
                        context = context
                    )

                    if(bitmap != null){
                        detailsViewModel.setColourPalette(
                            colours = extractColoursFromBitmap(
                                bitmap = bitmap
                            )
                        )
                    }
                }
            }
        }
    }
}