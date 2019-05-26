package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.kandroiddraw

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.TypedValue
import androidx.annotation.DrawableRes

class SpriteFactory(private val resources: Resources) {

    private val pixToDp: Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            1f, resources.displayMetrics)

    fun createSprite(bitmap: Bitmap): Sprite {
        return SpriteImpl(bitmap, pixToDp)
    }

    fun createSprite(@DrawableRes drawableRes: Int): Sprite {
        val bitmap = BitmapFactory.decodeResource(resources, drawableRes)
        return SpriteImpl(bitmap, pixToDp)
    }
}