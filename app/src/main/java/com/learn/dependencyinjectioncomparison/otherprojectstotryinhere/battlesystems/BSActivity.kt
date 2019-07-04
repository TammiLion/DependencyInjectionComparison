package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.battlesystems

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.learn.dependencyinjectioncomparison.R
import com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.battlesystems.logic.*
import kotlinx.android.synthetic.main.activity_bs.*

class BSActivity : AppCompatActivity(), GameLogic.Listener {

    private lateinit var gameLogic: GameLogic
    private val player = Actor("Player", 1, 1, 1, 1)
    private val enemy = Actor("Bot", 1, 1, 1, 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bs)
        setClickListeners()

        gameLogic = GameLogic(this)
        val aiListener = SimpleAi(enemy, player, gameLogic)
        gameLogic.aiListener = aiListener
    }

    private fun setClickListeners() {
        calcChanceBtn.setOnClickListener { calcChance() }
        phaseBtn.setOnClickListener { gameLogic.nextPhase() }
        damageBtn.setOnClickListener { calcDmg() }
        statBtn.setOnClickListener { calcStat() }
    }

    private fun calcChance() {
        val input = percentageEdit.text
        val percentage: Float? = input.toString().toFloatOrNull()
        percentage?.let {
            val isLucky = doChance(percentage)
            outcomeText.text = if (isLucky) "You're lucky!" else "Not so lucky :("
        }
    }

    private fun showActions(actions: List<Action>) {
        actionContainer.removeAllViews()
        actions.forEach { action ->
            val button = Button(this)
            button.text = action.identifier
            actionContainer.addView(button)
            button.setOnClickListener { actionChosen(action) }
        }
    }

    private fun actionChosen(action: Action) {
        gameLogic.onPlayerAction(action)
    }

    override fun onActionResult(result: String) {
        actionText.text = result
    }

    override fun onActionsChanged(actions: List<Action>) {
        showActions(actions)
    }

    override fun onPhaseChanged(phase: Phase) {
        phaseText.text = phase.name
    }

    private fun calcDmg() {
        val level: Int? = levelEdit.text.toString().toIntOrNull()
        val hasRandomModifer: Boolean = modifierToggle.isChecked
        val attackingStat: Int? = attackEdit.text.toString().toIntOrNull()
        val defendingStat: Int? = defendEdit.text.toString().toIntOrNull()
        val power: Int? = powerEdit.text.toString().toIntOrNull()

        if (level != null && attackingStat != null && defendingStat != null && power != null) {
            val modifier = if (hasRandomModifer) calcModifier() else 1.00
            val damage = calcDamage(level, power, attackingStat, defendingStat, modifier)
            damageText.text = "Damage outcome is $damage"
        }
    }

    private fun calcStat() {
        val baseStat: Int? = baseStatEdit.text.toString().toIntOrNull()
        val level: Int? = levelEdit.text.toString().toIntOrNull()
        val isHp: Boolean = isHpToggle.isChecked
        if (baseStat != null && level != null) {
            val stat = simpleCalcStat(baseStat, level, isHp)
            statText.text = "Stat outcome is $stat"
        }
    }
}