package com.abjt.fragmentcommunication.data

import com.abjt.fragmentcommunication.R

class DataHelper {

    companion object {
        fun getFruits(): List<Fruit> {
            return mutableListOf<Fruit>().also { list ->
                list.add(Fruit("Mango", R.drawable.mango_icon, R.drawable.mango_cover))
                list.add(Fruit("Apple", R.drawable.apple_icon, R.drawable.apple_cover))
                list.add(Fruit("Orange", R.drawable.orange_icon, R.drawable.orange_cover))
                list.add(Fruit("Strawberry", R.drawable.strawberry_icon, R.drawable.strawberry_cover))
            }
        }
    }
}