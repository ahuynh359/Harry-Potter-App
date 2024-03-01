package com.ahuynh.harrypotterapp.utils

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.ahuynh.harrypotterapp.R

enum class HouseType(@DrawableRes val image : Int , @ColorRes val color : Int) {
    Gryffindor(R.drawable.logo_gryffindor, R.color.maroon),
    Slytherin(R.drawable.logo_slytherin, R.color.deep_fir),
    Ravenclaw(R.drawable.logo_ravenclaw, R.color.midnight_blue),
    Hufflepuff(R.drawable.logo_hufflepuff, R.color.buddha_gold)
}