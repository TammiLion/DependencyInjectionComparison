package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.kandroiddraw

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.util.TypedValue
import androidx.annotation.DrawableRes

class SpriteFactory(private val resources: Resources) {

    fun createSprite(bitmap: Bitmap): Sprite {
        return SpriteImpl(bitmap)
    }

    fun createSprite(@DrawableRes drawableRes: Int): Sprite {
        val bitmap = BitmapFactory.decodeResource(resources, drawableRes)
        return createSprite(bitmap)
    }

    fun createCompositeSprite(frames: List<Rect>, bitmap: Bitmap, config: SpriteConfiguration = SpriteConfiguration()): Sprite {
        return CompositeSprite(frames, bitmap, this, config)
    }

    fun createCompositeSprite(frames: List<Rect>, @DrawableRes drawableRes: Int, config: SpriteConfiguration = SpriteConfiguration()): Sprite {
        val bitmap = BitmapFactory.decodeResource(resources, drawableRes)
        return createCompositeSprite(frames, bitmap, config)
    }

    fun createAnimatedSprite(frames: List<Rect>, bitmap: Bitmap): AnimatedSprite {
        return AnimatedSpriteImpl(frames, bitmap, this)
    }

    fun createAnimatedSprite(frames: List<Rect>, @DrawableRes drawableRes: Int): AnimatedSprite {
        val bitmap = BitmapFactory.decodeResource(resources, drawableRes)
        return createAnimatedSprite(frames, bitmap)
    }
}