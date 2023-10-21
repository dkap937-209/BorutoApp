package com.dk.boruto.presentation.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dk.boruto.ui.theme.ABOUT_PLACEHOLDER_HEIGHT
import com.dk.boruto.ui.theme.EXTRA_SMALL_PADDING
import com.dk.boruto.ui.theme.HERO_ITEM_HEIGHT
import com.dk.boruto.ui.theme.LARGE_PADDING
import com.dk.boruto.ui.theme.MEDIUM_PADDING
import com.dk.boruto.ui.theme.NAME_PLACEHOLDER_HEIGHT
import com.dk.boruto.ui.theme.RATING_PLACEHOLDER_HEIGHT
import com.dk.boruto.ui.theme.SMALL_PADDING
import com.dk.boruto.ui.theme.ShimmerDarkGray
import com.dk.boruto.ui.theme.ShimmerLightGray
import com.dk.boruto.ui.theme.ShimmerMediumGray
import com.dk.boruto.ui.theme.isLight

@Composable
fun ShimmerEffect() {

}

@Composable
fun AnimatedShimmerItem() {
    val transition = rememberInfiniteTransition()
    val alphaAnim by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    ShimmerItem(alpha = alphaAnim)
}

@Composable
fun ShimmerItem(
    alpha: Float
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(HERO_ITEM_HEIGHT),
        color = if(MaterialTheme.colorScheme.isLight())
            Color.Black else ShimmerLightGray,
        shape = RoundedCornerShape(size = LARGE_PADDING)
    ){
        Column(
            modifier = Modifier
                .padding(all = MEDIUM_PADDING),
            verticalArrangement = Arrangement.Bottom
        ){

            Surface(
                modifier = Modifier
                    .alpha(alpha = alpha)
                    .fillMaxWidth(0.5f)
                    .height(height = NAME_PLACEHOLDER_HEIGHT),
                color = if(MaterialTheme.colorScheme.isLight())
                    ShimmerDarkGray else ShimmerMediumGray,
                shape = RoundedCornerShape(size = SMALL_PADDING)
            ) {}
            Spacer(modifier = Modifier.padding(all = SMALL_PADDING))
            repeat(times = 3){
                Surface(
                    modifier = Modifier
                        .alpha(alpha = alpha)
                        .fillMaxWidth()
                        .height(height = ABOUT_PLACEHOLDER_HEIGHT),
                    color = if(MaterialTheme.colorScheme.isLight())
                        ShimmerDarkGray else ShimmerMediumGray,
                    shape = RoundedCornerShape(size = SMALL_PADDING)
                ) {}
                Spacer(modifier = Modifier.padding(all = EXTRA_SMALL_PADDING))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                repeat(times = 5){
                    Surface(
                        modifier = Modifier
                            .alpha(alpha = alpha)
                            .size(size = RATING_PLACEHOLDER_HEIGHT),
                        color = if(MaterialTheme.colorScheme.isLight())
                            ShimmerDarkGray else ShimmerMediumGray,
                        shape = RoundedCornerShape(size = SMALL_PADDING)
                    ) {}
                    Spacer(modifier = Modifier.padding(all = SMALL_PADDING))
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShimmerItemPreview() {
    ShimmerItem(alpha = 1f)
}

@Preview
@Composable
fun AnimatedShimmerItemPreview() {
    AnimatedShimmerItem()
}