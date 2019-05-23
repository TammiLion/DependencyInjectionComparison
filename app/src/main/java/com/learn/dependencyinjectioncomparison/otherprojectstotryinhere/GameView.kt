package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.SurfaceView
import com.learn.dependencyinjectioncomparison.R

class GameView(context: Context, size: Point) : SurfaceView(context), Runnable {

    private val gameThread = Thread(this)
    private var canvas: Canvas = Canvas()
    private val paint: Paint = Paint()
    private var spaceship: Bitmap = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.playership
    )

    private var invaders1: Bitmap
    private var invaders2: Bitmap
    private var currentInvadersFrame: Bitmap
    private var pastTime = 0.0f
    private val startTime = System.currentTimeMillis()

    private var isPlaying = false

    private var score = 70
    private var lives = 3
    private var waves = 10
    private var highScore = 9000


    init {
        // stretch the spaceship to a size
        // appropriate for the screen resolution
        spaceship = Bitmap.createScaledBitmap(
            spaceship,
            (size.x / 20f).toInt(),
            (size.y / 20f).toInt(),
            false
        )

        // Initialize the bitmaps
        invaders1 = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.invader1
        )

        invaders2 = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.invader2
        )

        // stretch the first spaceship to a size
        // appropriate for the screen resolution
        invaders1 = Bitmap.createScaledBitmap(
            invaders1,
            (size.x / 35f).toInt(),
            (size.y / 35f).toInt(),
            false
        )

        // stretch the second spaceship as well
        invaders2 = Bitmap.createScaledBitmap(
            invaders2,
            (size.x / 35f).toInt(),
            (size.y / 35f).toInt(),
            false
        )

        currentInvadersFrame = invaders1
    }

    override fun run() {
        while (isPlaying) {
            pastTime += System.currentTimeMillis() - startTime
            updateInvadersFrame()
            draw()
        }
    }

    private fun updateInvadersFrame() {
        currentInvadersFrame = if (Math.round(pastTime) % 2 == 0) {
            invaders2
        } else {
            invaders1
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
            // Now draw the player spaceship
            canvas.drawBitmap(spaceship, 100f, 100f, paint)

            canvas.drawBitmap(currentInvadersFrame, 200f, 100f, paint)

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