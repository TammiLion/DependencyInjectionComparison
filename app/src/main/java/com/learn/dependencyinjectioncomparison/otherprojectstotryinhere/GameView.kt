package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere

import android.content.Context
import android.graphics.*
import android.util.TypedValue
import android.view.MotionEvent
import android.view.SurfaceView
import com.learn.dependencyinjectioncomparison.R
import com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.kandroiddraw.AnimatedSprite
import com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.kandroiddraw.SPEED_HALF
import com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.kandroiddraw.Sprite

class GameView(context: Context, size: Point) : SurfaceView(context), Runnable {

    private val gameThread = Thread(this)
    private var canvas: Canvas = Canvas()
    private val paint: Paint = Paint()

    private var roguelikeSheet: Bitmap = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.roguelike_sheet)
    private var rogueSprite = Sprite(context.resources, bitmap = roguelikeSheet)
    private var animatedRogue = AnimatedSprite(context.resources,
            listOf(Rect(0, 0, 16, 16), Rect(17, 0, 33, 16)), roguelikeSheet)

    private val dp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            16f, context.resources.displayMetrics)

    private var pastTime = 0.0f
    private var startTime = System.currentTimeMillis()

    private var isPlaying = false

    private var score = 70
    private var lives = 3
    private var waves = 10
    private var highScore = 9000


    init {
        animatedRogue.updateTime = 300f
    }

    override fun run() {
        while (isPlaying) {
            val currentTimeMillis = System.currentTimeMillis()
            val deltaTime = currentTimeMillis - startTime
            startTime = currentTimeMillis
            pastTime += deltaTime
            animatedRogue.update(deltaTime)
            draw()
        }
    }

    private fun draw() {
        // Make sure our drawing surface is valid or the game will crash
        if (holder.surface.isValid) {
            // Lock the canvas ready to draw
            canvas = holder.lockCanvas()

            // Draw the background color
            canvas.drawColor(Color.argb(255, 0, 0, 0))

            // Choose the brush color for drawing
            paint.color = Color.argb(255, 0, 255, 0)

            // Draw all the game objects here
            rogueSprite.setSrc(Rect(0, 0, 32, 64))
            //rogueSprite.setDest(700,200,32,64)
            rogueSprite.setDest(700, 200)
            rogueSprite.draw(canvas)

            val src = Rect(0, 0, (dp * 2).toInt(), (dp * 4).toInt())
            val dest = Rect(800, 200, (800 + (dp * 2)).toInt(), (200 + (dp * 4)).toInt())
            canvas.drawRect(dest, paint)
            canvas.drawBitmap(roguelikeSheet, src, dest, null)

            animatedRogue.setDest(600, 400)
            animatedRogue.draw(canvas)

            // Draw the score and remaining lives
            // Change the brush color
            paint.color = Color.argb(255, 255, 255, 255)
            paint.textSize = 70f
            canvas.drawText(
                    "Score: $score   Lives: $lives Wave: " +
                            "$waves HI: $highScore", 20f, 75f, paint
            )

            // Draw everything to the screen
            holder.unlockCanvasAndPost(canvas)
        }
    }

    // The SurfaceView class implements onTouchListener
    // So we can override this method and detect screen touches.
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {

        return true
    }

    fun pause() {
        isPlaying = false
        gameThread.join()
    }

    fun resume() {
        isPlaying = true
        gameThread.start()
    }
}