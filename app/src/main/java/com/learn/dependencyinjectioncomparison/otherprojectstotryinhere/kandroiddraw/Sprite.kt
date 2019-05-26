package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.kandroiddraw

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect

const val DEFAULT_SCALE = 1f
const val SCALE_X2 = 2f
const val SCALE_X4 = 4f
const val SCALE_HALF = 0.5f

interface Sprite {
    var bitmap: Bitmap
    var config: SpriteConfiguration

    fun setSource(src: Rect)
    fun setDestination(x: Int, y: Int, width: Int? = null, height: Int? = null)
    fun draw(canvas: Canvas)
}