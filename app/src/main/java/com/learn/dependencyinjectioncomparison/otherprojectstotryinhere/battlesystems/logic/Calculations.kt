package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.battlesystems.logic

import kotlin.random.Random

fun doChance(chance: Float): Boolean = Random.nextFloat() <= chance

fun simpleCalcStat(baseStat: Int, level: Int, isHp: Boolean = false): Int {
    return baseStat * level + if (isHp) level + 10 else level + 5
}

//https://bulbapedia.bulbagarden.net/wiki/Damage

fun calcModifier(): Double {
    return Math.min(Random.nextDouble() + 0.85, 1.00)
}

fun calcDamage(level: Int, power: Int, attackingStat: Int, defendingStat: Int, modifier: Double = 1.0): Int {
    val levelMod = ((2 * level) / 5) + 2
    val topPartEquation = levelMod * power * (attackingStat / defendingStat)
    return (((topPartEquation/50)+2) * modifier).toInt()
}

fun ClosedRange<Int>.random() =
        Random.nextInt((endInclusive + 1) - start) +  start