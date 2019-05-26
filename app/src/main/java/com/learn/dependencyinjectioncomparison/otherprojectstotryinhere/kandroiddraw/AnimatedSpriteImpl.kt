package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.kandroiddraw

import android.content.res.Resources
import android.graphics.*

const val DEFAULT_SPEED = 1f
const val SPEED_2X = 2f
const val SPEED_HALF = 0.5f

const val DEFAULT_FRAME_UPDATE = 300f
const val SLOW_UPDATE = 1000f
const val FAST_UPDATE = 60f

class AnimatedSprite(frames: List<Rect>,
                     private var bitmap: Bitmap? = null) : Sprite {

    override var adaptToScreenDensity: Boolean = true
    override var paint: Paint? = null
    override var scale: Float = DEFAULT_SCALE

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

        SpriteFactory()
        frames.forEach {

            sprites.add(SpriteImpl(bitmap = bitmap, adaptToScreenDensity = adaptToScreenDensity).apply {
                setSource(it)
            })
        }
    }

    override fun setSource(src: Rect) {
        //already handled by constructor
    }

    override fun setDestination(x: Int, y: Int, width: Int?, height: Int?) {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
    }

    fun update(deltaTime: Long) {
        time += deltaTime * animationSpeed
        if (time >= updateTime) {
            time = 0f
            updateFrame()
        }
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

    override fun draw(canvas: Canvas) {
        val sprite = sprites.getOrNull(currentFrame)
        sprite?.setDestination(x, y, width, height)
        sprite?.draw(canvas)
    }
}