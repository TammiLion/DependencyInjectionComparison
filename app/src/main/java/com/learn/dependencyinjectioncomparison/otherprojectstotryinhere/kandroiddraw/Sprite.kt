package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.kandroiddraw

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.TypedValue

const val DEFAULT_SCALE = 1f
const val SCALE_X2 = 2f
const val SCALE_X4 = 4f
const val SCALE_HALF = 0.5f

class Sprite(resources: Resources, drawableRes: Int? = null, private var bitmap: Bitmap? = null) {

    private var src = Rect(0,0,0,0)
    private var dest = Rect(0,0,0,0)
    var paint: Paint? = null
    var scale: Float = DEFAULT_SCALE
    private val pixToDp: Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            1f, resources.displayMetrics)

    init {
        if(bitmap == null && drawableRes != null) {
            bitmap = BitmapFactory.decodeResource(resources, drawableRes)
        }
    }

    fun setSrc(src: Rect, adaptToScreenDensity: Boolean = true) {
        this.src = if(adaptToScreenDensity) getRectangleAdaptedToDensity(src) else src
    }

    fun setDest(x: Int, y: Int, width: Int? = null, height: Int? = null, adaptToScreenDensity: Boolean = true) {
        dest = if(width == null || height == null) {
             Rect(x, y, (x+(src.width()*scale)).toInt(), (y+(src.height()*scale)).toInt())
        } else {
            if(adaptToScreenDensity) {
                Rect(x, y, x+(width*pixToDp*scale).toInt(), y+(height*pixToDp*scale).toInt())
            } else {
                Rect(x, y, (x+(width*scale)).toInt(), (y+(height*scale)).toInt())
            }
        }
    }

    private fun getRectangleAdaptedToDensity(rect: Rect): Rect {
        return Rect((rect.left*pixToDp).toInt(), (rect.top*pixToDp).toInt(), (rect.right*pixToDp).toInt(), (rect.bottom*pixToDp).toInt())
    }

    fun draw(canvas: Canvas) {
        bitmap?.let { canvas.drawBitmap(it, src, dest, paint) }
    }

}