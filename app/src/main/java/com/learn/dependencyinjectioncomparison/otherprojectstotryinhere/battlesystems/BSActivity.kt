package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.battlesystems

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.learn.dependencyinjectioncomparison.R
import com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.battlesystems.logic.Action
import com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.battlesystems.logic.GameLogic
import com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.battlesystems.logic.Phase
import com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.battlesystems.logic.doChance
import kotlinx.android.synthetic.main.activity_bs.*

class BSActivity : AppCompatActivity(), GameLogic.Listener {

    private lateinit var gameLogic: GameLogic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bs)
        setClickListeners()
        gameLogic = GameLogic(this)
    }



    private fun setClickListeners() {
        calcChanceBtn.setOnClickListener {calcChance() }
        phaseBtn.setOnClickListener { gameLogic.nextPhase() }
    }

    private fun calcChance() {
        val input = percentageEdit.text
        val percentage: Float? = input.toString().toFloatOrNull()
        percentage?.let {
            val isLucky = doChance(percentage)
            outcomeText.text = if(isLucky) "You're lucky!" else "Not so lucky :("
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
}