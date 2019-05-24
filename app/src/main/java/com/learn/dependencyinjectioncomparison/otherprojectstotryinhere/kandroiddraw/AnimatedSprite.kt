package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.kandroiddraw

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.util.Log

const val DEFAULT_SPEED = 1f
const val SPEED_2X = 2f
const val SPEED_HALF = 0.5f

const val DEFAULT_FRAME_UPDATE = 300f
const val SLOW_UPDATE = 1000f
const val FAST_UPDATE = 60f

class AnimatedSprite(resources: Resources,
                     frames: List<Rect>,
                     private var bitmap: Bitmap? = null,
                     drawableRes: Int? = null,
                     private val adaptToScreenDensity: Boolean = true) {

    private var currentFrame: Int = 0
    private var time = 0f
    var updateTime = DEFAULT_FRAME_UPDATE
    var animationSpeed = DEFAULT_SPEED
    private var sprites: MutableList<Sprite> = mutableListOf()

    private var x = 0
    private var y = 0
    private var width: Int? = null
    private var height: Int? = null

    init {
        if (bitmap == null && drawableRes != null) {
            bitmap = BitmapFactory.decodeResource(resources, drawableRes)
        }
        frames.forEach {
            sprites.add(Sprite(resources, bitmap = bitmap).apply {
                setSrc(it, adaptToScreenDensity)
            })
        }
    }

    fun update(deltaTime: Long) {
        time += deltaTime * animationSpeed
        if (time >= updateTime) {
            time = 0f
            updateFrame()
        }
    }

    fun setDest(x: Int, y: Int, width: Int? = null, height: Int? = null) {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
    }

    private fun updateFrame() {
        currentFrame++
        if (currentFrame >= sprites.size) currentFrame = 0
    }

    fun resetTime() {
        time = 0f
    }

    fun reset() {
        resetTime()
        currentFrame = 0
    }

    fun draw(canvas: Canvas) {
        val sprite = sprites.getOrNull(currentFrame)
        sprite?.setDest(x, y, width, height, adaptToScreenDensity)
        sprite?.draw(canvas)
    }
}