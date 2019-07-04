package com.learn.dependencyinjectioncomparison.otherprojectstotryinhere.battlesystems.logic

open class GameLogic(val playerListener: Listener) {

    var aiListener: Listener? = null
    set(value) {
        field = value
        nextPhase()
    }
    private val phasesInOrder = listOf(PREPARATION, BATTLE, DEFENSE, DEFEAT, WIN)
    private var currentPhase = 0

    private var playerAction: Action? = null
    private var enemyAction: Action? = null

    init {
        currentPhase = -1
    }

    fun battle(engager: Actor, engaged: Actor) {

    }

    fun onPlayerAction(action: Action) {
        playerAction = action
        processActions()
    }

    fun onEnemyAction(action: Action) {
        enemyAction = action
        processActions()
    }

    fun processActions() {
        playerAction?.let { pAction ->
            enemyAction?.let { eAction ->
                val success = doChance(pAction.baseChance)
                val eSuccess = doChance(eAction.baseChance)
                val result = if (success) "Player action ${pAction.identifier} was a success" else "Player action ${pAction.identifier} was a failure"
                val eResult = if (eSuccess) "Enemy action ${eAction.identifier} was a success" else "Enemy action ${eAction.identifier} was a failure"

                playerListener.onActionResult("$result\n$eResult")

                playerAction = null
                enemyAction = null
            }
        }
    }

    fun nextPhase() {
        currentPhase++
        if(currentPhase >= phasesInOrder.size) {
            currentPhase = 0
        }
        val phase = phasesInOrder[currentPhase]
        playerListener.onPhaseChanged(phase)
        playerListener.onActionsChanged(phase.allowedActions)
        aiListener?.onPhaseChanged(phase)
        aiListener?.onActionsChanged(phase.allowedActions)
    }

    interface Listener {
        fun onActionResult(result: String)
        fun onActionsChanged(actions: List<Action>)
        fun onPhaseChanged(phase: Phase)
    }
}