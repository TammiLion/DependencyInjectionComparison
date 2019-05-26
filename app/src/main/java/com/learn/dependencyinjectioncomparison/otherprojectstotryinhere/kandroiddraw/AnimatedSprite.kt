package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.kandroiddraw

import android.graphics.Canvas

const val DEFAULT_SPEED = 1f
const val SPEED_2X = 2f
const val SPEED_HALF = 0.5f

const val DEFAULT_FRAME_UPDATE = 300f
const val SLOW_UPDATE = 1000f
const val FAST_UPDATE = 60f

interface AnimatedSprite {

    var config: AnimatedSpriteConfiguration

    fun setDestination(x: Int, y: Int, width: Int? = null, height: Int? = null)
    fun update(deltaTime: Long)
    fun draw(canvas: Canvas)
}