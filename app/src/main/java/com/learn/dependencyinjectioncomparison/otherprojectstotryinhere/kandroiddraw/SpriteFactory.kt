package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.kandroiddraw

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
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
        return createSprite(bitmap)
    }

    fun createCompositeSprite(frames: List<Rect>, bitmap: Bitmap): Sprite {
        return CompositeSprite(frames, bitmap, this)
    }

    fun createCompositeSprite(frames: List<Rect>, @DrawableRes drawableRes: Int): Sprite {
        val bitmap = BitmapFactory.decodeResource(resources, drawableRes)
        return createCompositeSprite(frames, bitmap)
    }

    fun createAnimatedSprite(frames: List<Rect>, bitmap: Bitmap): AnimatedSprite {
        return AnimatedSpriteImpl(frames, bitmap, this)
    }

    fun createAnimatedSprite(frames: List<Rect>, @DrawableRes drawableRes: Int): AnimatedSprite {
        val bitmap = BitmapFactory.decodeResource(resources, drawableRes)
        return createAnimatedSprite(frames, bitmap)
    }
}