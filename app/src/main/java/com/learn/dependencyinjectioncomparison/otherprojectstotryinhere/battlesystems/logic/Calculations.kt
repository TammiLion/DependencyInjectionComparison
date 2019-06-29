package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.battlesystems.logic

import kotlin.random.Random

fun doChance(chance: Float): Boolean = Random.nextFloat() <= chance
