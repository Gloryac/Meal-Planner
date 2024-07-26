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
        title = "The title for this page",
        description = "Description for this page"
    )
    data object SecondPage:OnboardingModel(
        image = R.drawable.onboarding2,
        title = "The title for this page",
        description = "Description for this page"
    )
    data object ThirdPage:OnboardingModel(
        image = R.drawable.onboarding3,
        title = "The title for this page",
        description = "Description for this page"
    )
}
