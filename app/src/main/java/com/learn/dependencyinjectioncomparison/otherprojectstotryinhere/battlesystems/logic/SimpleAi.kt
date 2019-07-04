package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.battlesystems.logic

open class SimpleAi(val aiActor: Actor, val player: Actor, val gameLogic: GameLogic) : GameLogic.Listener {
    override fun onActionResult(result: String) {
    }

    override fun onActionsChanged(actions: List<Action>) {
        val action = actions[(0 until actions.size).random()]
        gameLogic.onEnemyAction(action)
    }

    override fun onPhaseChanged(phase: Phase) {
    }

}