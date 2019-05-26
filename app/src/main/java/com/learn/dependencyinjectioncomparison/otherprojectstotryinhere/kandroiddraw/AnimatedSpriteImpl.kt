package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.kandroiddraw

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect

class AnimatedSpriteImpl(frames: List<Rect>,
                         private var bitmap: Bitmap,
                         spriteFactory: SpriteFactory) : AnimatedSprite {

    override var config: AnimatedSpriteConfiguration = AnimatedSpriteConfiguration()

    private var currentFrame: Int = 0
    private var time = 0f
    private var sprites: MutableList<Sprite> = mutableListOf()

    private var x = 0
    private var y = 0
    private var width: Int? = null
    private var height: Int? = null

    init {
        frames.forEach {
            val sprite = spriteFactory.createSprite(bitmap)
            sprite.apply { setSource(it) }
            sprites.add(sprite)
        }
    }

    override fun setDestination(x: Int, y: Int, width: Int?, height: Int?) {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
    }

    override fun update(deltaTime: Long) {
        time += deltaTime * config.animationSpeed
        if (time >= config.updateTime) {
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
        sprite?.config = config
        sprite?.setDestination(x, y, width, height)
        sprite?.draw(canvas)
    }
}