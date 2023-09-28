package com.dk.boruto.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.stringResource
import com.dk.boruto.R
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dk.boruto.ui.theme.StarColor
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import com.dk.boruto.ui.theme.EXTRA_SMALL_PADDING
import com.dk.boruto.util.Constants.MAX_STARS

private const val TAG = "RatingWidget"
private const val FULL_STAR = "fullStars"
private const val PARTIAL_STAR = "partialStars"
private const val EMPTY_STAR = "emptyStars"

@Composable
fun RatingWidget(
    modifier: Modifier,
    rating: Double,
    scaleFactor: Float = 3f,
    spaceBetween: Dp = EXTRA_SMALL_PADDING
) {
    
    val result = calculateStars(rating = rating)

    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember{
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds = remember{
        starPath.getBounds()
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        result[FULL_STAR]?.let { numStars ->
            repeat(numStars.toInt()){
                Star(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor,
                    fillAmount = 1.0
                )
            }
        }
        result[PARTIAL_STAR]?.let { fillAmount ->

            if(fillAmount > 0.0){
                Star(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor,
                    fillAmount = fillAmount
                )
            }
        }
        result[EMPTY_STAR]?.let{numStars ->
            repeat(numStars.toInt()){
                Star(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor,
                    fillAmount = 0.0
                )
            }
        }

    }
}
@Composable
fun calculateStars(
    rating: Double
): Map<String, Double>  {

    val maxStars by remember{ mutableIntStateOf(MAX_STARS) }
    var fullStars by remember { mutableDoubleStateOf(0.0) }
    var partialStar by remember { mutableDoubleStateOf(0.0) }
    var emptyStars by remember { mutableDoubleStateOf(0.0) }

    if(rating <= 5.0){
        partialStar = rating.mod(1.0)
        fullStars = rating - partialStar
        emptyStars = (maxStars - rating)
    }
    else{
        Log.d(TAG, "Invalid rating value")
    }
    emptyStars = (maxStars - fullStars - partialStar)

    return mapOf(
        FULL_STAR to fullStars,
        PARTIAL_STAR to partialStar,
        EMPTY_STAR to emptyStars
    )
}

@Preview(showBackground = true)
@Composable
fun StarPreview() {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember{
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds = remember{
        starPath.getBounds()
    }
    Star(
        starPath = starPath,
        starPathBounds = starPathBounds,
        scaleFactor = 3f,
        fillAmount = 0.5
    )
}

@Preview(showBackground = true)
@Composable
fun RatingWidgetPreview() {
    RatingWidget(
        modifier = Modifier,
        rating = 4.5
    )
}


@Composable
fun Star(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float,
    fillAmount: Double = 0.0
) {
    Canvas(
        modifier = Modifier
            .size(24.dp)
    ){
        val canvasSize = this.size

        scale(scale = scaleFactor){
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height
            val left = (canvasSize.width/2f) - (pathWidth/1.7f)
            val top = (canvasSize.height/2f) - (pathHeight/1.7f)


            translate(
                left = left,
                top = top
            ) {
                drawPath(
                    path = starPath,
                    color = Color.LightGray.copy(alpha = 0.5f)
                )
                clipPath(path = starPath){
                    drawRect(
                        color = StarColor,
                        size = Size(
                            width = (starPathBounds.maxDimension * (fillAmount + 0.07)).toFloat(),
                            height = starPathBounds.maxDimension * scaleFactor
                        )
                    )
                }
            }
        }
    }
}