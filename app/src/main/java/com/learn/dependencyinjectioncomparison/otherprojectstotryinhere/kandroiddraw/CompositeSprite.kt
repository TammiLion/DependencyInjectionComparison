package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.kandroiddraw

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect

class CompositeSprite(frames: List<Rect>,
                      override var bitmap: Bitmap,
                      spriteFactory: SpriteFactory,
                      spriteConfiguration: SpriteConfiguration = SpriteConfiguration()) : Sprite {
    override var config: SpriteConfiguration = spriteConfiguration
    set(value) {
        sprites.forEach {
            it.config = config
        }
        field = value
    }

    private var sprites: MutableList<Sprite> = mutableListOf()

    init {
        frames.forEach {
            val sprite = spriteFactory.createSprite(bitmap)
            sprite.apply { setSource(it) }
            sprites.add(sprite)
        }
    }

    override fun setSource(src: Rect) {
        //not needed
    }

    override fun setDestination(x: Int, y: Int, width: Int?, height: Int?) {
        sprites.forEach {
            it.setDestination(x, y, width, height)
        }
    }

    override fun draw(canvas: Canvas) {
        sprites.forEach { it.draw(canvas) }
    }
}