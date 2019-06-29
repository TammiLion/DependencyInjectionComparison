package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.battlesystems.logic

open class GameLogic(val listener: Listener) {

    private val phasesInOrder = listOf(PREPARATION, BATTLE, DEFENSE, DEFEAT, WIN)
    private var currentPhase = 0

    init {
        currentPhase = -1
        nextPhase()
    }

    fun onPlayerAction(action: Action) {
        //TODO wait for this plus enemy
        val success = doChance(action.baseChance)
        val result = if (success) "Action ${action.identifier} was a success" else "Action ${action.identifier} was a failure"
        listener.onActionResult(result)
    }

    fun nextPhase() {
        currentPhase++
        if(currentPhase >= phasesInOrder.size) {
            currentPhase = 0
        }
        val phase = phasesInOrder[currentPhase]
        listener.onPhaseChanged(phase)
        listener.onActionsChanged(phase.allowedActions)
    }

    interface Listener {
        fun onActionResult(result: String)
        fun onActionsChanged(actions: List<Action>)
        fun onPhaseChanged(phase: Phase)
    }
}