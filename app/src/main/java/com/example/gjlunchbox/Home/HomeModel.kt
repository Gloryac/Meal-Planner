package com.example.gjlunchbox.Home
import android.graphics.Bitmap

class HomeModel {
    data class User(
        val name: String,
        val profilePicture: Int? = null
    )

    data class Inspiration(
        val imageResource: Int,
        val title: String
    )
}



