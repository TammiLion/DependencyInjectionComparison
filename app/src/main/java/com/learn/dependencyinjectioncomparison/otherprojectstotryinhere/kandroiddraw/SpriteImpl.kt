package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.kandroiddraw

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect

class SpriteImpl(override var bitmap: Bitmap) : Sprite {

    override var config: SpriteConfiguration = SpriteConfiguration()

    private var src = Rect(0, 0, 0, 0)
    private var dest = Rect(0, 0, 0, 0)

    /*
    Set coordinates of the part of the image that should be drawn.
     */
    override fun setSource(src: Rect) {
        this.src = src
    }

    /*
    Set coordinates of where the image should be drawn on the screen.
     */
    override fun setDestination(x: Int, y: Int, width: Int?, height: Int?) {
        dest = if (width == null || height == null) {
            Rect(x, y, (x + (src.width() * config.scale)).toInt(), (y + (src.height() * config.scale)).toInt())
        } else {
            Rect(x, y, (x + (width * config.scale)).toInt(), (y + (height * config.scale)).toInt())
        }
    }

    override fun draw(canvas: Canvas) {
        bitmap.let { canvas.drawBitmap(it, src, dest, config.paint) }
    }
}