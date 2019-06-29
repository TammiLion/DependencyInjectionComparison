package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.battlesystems.logic

import androidx.annotation.DrawableRes

open class Phase(val name: String, @DrawableRes val icon: Int, val allowedActions: List<Action>)

val PREPARATION = Phase("Prepare for battle", android.R.drawable.ic_lock_idle_lock,
        listOf(INTIMIDATE, BOOST_MORALE))
val BATTLE = Phase("Battle", android.R.drawable.btn_plus, listOf(ATTACK))
val DEFENSE = Phase("Defense", android.R.drawable.btn_minus, listOf(DEFEND, DODGE, COUNTER))
val DEFEAT = Phase("Defeat", android.R.drawable.ic_delete, listOf(RUN, NEGOTIATE))
val WIN = Phase("Victory", android.R.drawable.star_on, listOf(LOOT, CONVINCE_TO_JOIN))

