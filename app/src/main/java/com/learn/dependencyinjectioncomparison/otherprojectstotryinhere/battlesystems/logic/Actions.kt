package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.battlesystems.logic

open class Action(val identifier: String, val baseChance: Float = 0.5f)

val INTIMIDATE = Action("Intimidate", 0.1f)
val BOOST_MORALE = Action("Boost morale", 0.7f)

val ATTACK = Action("Attack", 1f)

val DEFEND = Action("Defend", 1f)
val DODGE = Action("Dodge")
val COUNTER = Action("Counter", 0.25f)

val NEGOTIATE = Action("Negotiate")
val RUN = Action("Run", 0.25f)

val LOOT = Action("Loot", 0.25f)
val CONVINCE_TO_JOIN = Action("Convince to join", 0.0625f)


