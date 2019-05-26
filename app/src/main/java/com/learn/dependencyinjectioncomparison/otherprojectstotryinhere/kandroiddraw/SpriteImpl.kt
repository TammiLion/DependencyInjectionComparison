package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.kandroiddraw

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect

class SpriteImpl(private var bitmap: Bitmap, private val pixToDp: Float) : Sprite {

    override var config: SpriteConfiguration = SpriteConfiguration()

    private var src = Rect(0, 0, 0, 0)
    private var dest = Rect(0, 0, 0, 0)

    /*
    Set coordinates of the part of the image that should be drawn.
     */
    override fun setSource(src: Rect) {
        this.src = if (config.adaptToScreenDensity) getRectangleAdaptedToDensity(src) else src
    }

    /*
    Set coordinates of where the image should be drawn on the screen.
     */
    override fun setDestination(x: Int, y: Int, width: Int?, height: Int?) {
        dest = if (width == null || height == null) {
            Rect(x, y, (x + (src.width() * config.scale)).toInt(), (y + (src.height() * config.scale)).toInt())
        } else {
            if (config.adaptToScreenDensity) {
                Rect(x, y, x + (width * pixToDp * config.scale).toInt(), y + (height * pixToDp * config.scale).toInt())
            } else {
                Rect(x, y, (x + (width * config.scale)).toInt(), (y + (height * config.scale)).toInt())
            }
        }
    }

    private fun getRectangleAdaptedToDensity(rect: Rect): Rect {
        return Rect((rect.left * pixToDp).toInt(), (rect.top * pixToDp).toInt(), (rect.right * pixToDp).toInt(), (rect.bottom * pixToDp).toInt())
    }

    override fun draw(canvas: Canvas) {
        bitmap.let { canvas.drawBitmap(it, src, dest, config.paint) }
    }
}