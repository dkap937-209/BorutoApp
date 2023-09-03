package com.dk.boruto.domain.model

import androidx.annotation.DrawableRes
import com.dk.boruto.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
){
    object Greetings: OnBoardingPage(
        image = R.drawable.greetings,
        title = "Greetings",
        description = "Are you a Boruto fan? Because if you are we have great news for you!"
    )
    object Explore: OnBoardingPage(
        image = R.drawable.explore,
        title = "Greetings",
        description = "Find your favourite heroes and learn some thing that you didn't know about them"
    )
    object Power: OnBoardingPage(
        image = R.drawable.power,
        title = "Power",
        description = "Check out your hero's power and see how they stack up against other heroes."
    )
}
