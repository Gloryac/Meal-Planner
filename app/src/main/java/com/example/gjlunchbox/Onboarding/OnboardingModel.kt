package com.example.gjlunchbox.Onboarding

import androidx.annotation.DrawableRes
import com.example.gjlunchbox.R

sealed class OnboardingModel(
    @DrawableRes val image: Int,
    val title: String,
    val description: String,
    ){
    data object FirstPage:OnboardingModel(
        image = R.drawable.onboarding1,
        title = "You Should Know What You Eat ",
        description = "Gain insights in your nutritional habits with \n" +
                "detailed statistics"
    )
    data object SecondPage:OnboardingModel(
        image = R.drawable.onboarding2,
        title = "Track Your Diet",
        description = "We will help ypu lose weight, stay fit, or build muscle"
    )
    data object ThirdPage:OnboardingModel(
        image = R.drawable.onboarding3,
        title = "Live Healthy & Great",
        description = "Let’s start this journey and live healty\n" +
                "together!"
    )
}
