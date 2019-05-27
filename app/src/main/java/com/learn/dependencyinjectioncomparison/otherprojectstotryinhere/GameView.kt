package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.SurfaceView
import com.learn.dependencyinjectioncomparison.R
import com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.kandroiddraw.SCALE_X4
import com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.kandroiddraw.SpriteFactory
import com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.kandroiddraw.utils.RowColumnCalculator

class GameView(context: Context, size: Point) : SurfaceView(context), Runnable {

    private val gameThread = Thread(this)
    private var canvas: Canvas = Canvas()
    private val paint: Paint = Paint()

    private var simpleSheet: Bitmap = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.simple_sheet)

    private var scaledSimple: Bitmap = Bitmap.createScaledBitmap(simpleSheet, 64 * 9, 64, false)

    private val spriteFactory = SpriteFactory(context.resources)

    private val rowColumnCalc = RowColumnCalculator(64, 64, 0)
    private val body = rowColumnCalc.getRect(0, 0)
    private val pants = rowColumnCalc.getRect(2, 0)
    private val shoes = rowColumnCalc.getRect(3, 0)
    private val chest = rowColumnCalc.getRect(4, 0)
    private val body2 = rowColumnCalc.getRect(1, 0)
    private val hair = rowColumnCalc.getRect(5, 0)
    private val helmet = rowColumnCalc.getRect(6, 0)
    private val shield = rowColumnCalc.getRect(7, 0)
    private val spear = rowColumnCalc.getRect(8, 0)
    private var animatedRogue = spriteFactory.createAnimatedSprite(listOf(body, body2), scaledSimple)
    private var compositeRogue = spriteFactory.createCompositeSprite(
            listOf(body, pants, shoes, chest), scaledSimple)

    private var pastTime = 0.0f
    private var startTime = System.currentTimeMillis()

    private var isPlaying = false

    private var score = 70
    private var lives = 3
    private var waves = 10
    private var highScore = 9000

    init {
        compositeRogue.config = compositeRogue.config.apply {
            scale = SCALE_X4
        }
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

            animatedRogue.setDestination(600, 400)
            animatedRogue.draw(canvas)

            compositeRogue.setDestination(600, 600)
            compositeRogue.draw(canvas)

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