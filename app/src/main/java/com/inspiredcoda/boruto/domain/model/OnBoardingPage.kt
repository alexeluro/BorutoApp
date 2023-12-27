package com.inspiredcoda.boruto.domain.model

import androidx.annotation.DrawableRes
import com.inspiredcoda.boruto.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String,
) {

    companion object {
        val pages = listOf<OnBoardingPage>(First, Second, Third)
    }
    object First: OnBoardingPage(
        image = R.drawable.greetings,
        title = "Greetings",
        description = "Are you a Boruto fan? If you are, we have good news for you..."
    )

    object Second: OnBoardingPage(
        image = R.drawable.explore,
        title = "Explore",
        description = "Find some of your favourite heroes and learn some of the things that you didn't know about."
    )

    object Third: OnBoardingPage(
        image = R.drawable.power,
        title = "Power",
        description = "Check out you hero's power and see how strong they are when  compared to others."
    )
}