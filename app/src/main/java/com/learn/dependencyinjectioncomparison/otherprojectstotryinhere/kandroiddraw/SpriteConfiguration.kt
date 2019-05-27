package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.kandroiddraw

import android.graphics.Paint

open class SpriteConfiguration(var scale: Float = DEFAULT_SCALE,
                               var paint: Paint? = null)

open class AnimatedSpriteConfiguration(var updateTime: Float = DEFAULT_FRAME_UPDATE,
                                       var animationSpeed: Float = DEFAULT_SPEED) : SpriteConfiguration()